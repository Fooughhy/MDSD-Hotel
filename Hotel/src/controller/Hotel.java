package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.AmenitiesBooking;
import model.Amenity;
import model.Booking;
import model.Guest;
import model.HotelFullException;
import model.Room;
import model.RoomType;
import model.User;
import model.UserType;
import view.View;

public class Hotel {
	
	private View view;
	private Map<String,Room> roomMap;
	private List<Guest> guestList;
	private List<Amenity> amenitiesList;
	private List<User> userList;
	private Set<RoomType> roomTypeList;
	
	private List<AmenitiesBooking> amenitiesBookingList;
	private List<Booking> bookingList;
	
	private User loggedInUsers;
	
	// The last check out date of all rooms
	private Map<RoomType, Map<Room, Date>> rooms;
	
	// The availability of rooms for any given day.
	// The number of bookings for a RoomType for any day
	// If Date is <null> then there is no booked room.
	private Map<Date, Map<RoomType, Integer>> bookedRooms;
	
	public Hotel(){
		view = new View();
		
		roomMap = new TreeMap<String,Room>();
		guestList = new ArrayList<Guest>();
		amenitiesList = new ArrayList<Amenity>();
		userList = new ArrayList<User>();
		//loggedInUsers = new ArrayList<User>();
		roomTypeList = new HashSet<RoomType>();
		amenitiesBookingList = new ArrayList<AmenitiesBooking>();
		bookingList = new ArrayList<Booking>();
		bookedRooms = new HashMap<Date, Map<RoomType, Integer>>();
		
		userList.add(new User("admin", "admin", UserType.Admin));
		
		setupBookedRooms();
	}
	
	/**
	 * Returns the guest with the given passport number.
	 * @param ppn The guest's passport number.
	 * @return The Guest object
	 */
	public Guest findGuestByPassport(String ppn) {
		for (Guest guest : guestList) {
			if (guest.getGuestPassPortNumber().equals(ppn)) {
				return guest;
			}
		}
		return null;
	}
	
	/**
	 * Adds the guest to the Hotel system.
	 * @param guest The Guest to add.
	 */
	public void addGuest(Guest guest) {
		guestList.add(guest);
	}
	
	/**
	 * Provides a list with all of one Guest's Bookings.
	 * @return
	 */
	public List<Booking> findBookingsByGuest(Guest guest) {
		List<Booking> byGuest = new LinkedList<Booking>();
				
		for (Booking booking : bookingList) {
			if (booking.getBookingGuest().equals(guest)) {
				byGuest.add(booking);
			}
		}
		
		return byGuest;
	}
	
	/**
	 * Sets up a map between RoomTypes and the rooms of the type.
	 * Each room has stored the last check out date of the room.
	 * This is used to only check in rooms that has been checked out.
	 * The booking process guarantees that any booked room can be checked in.
	 */
	private void setupBookedRooms() {
		// Run at startup of program or when adding a new room
		// not when booking a Room, as this only books a RoomType.
		rooms = new HashMap<RoomType, Map<Room, Date>>();
		
		for (RoomType rt : roomTypeList) {
			rooms.put(rt, new HashMap<Room, Date>());
		}
		
		Iterator<Room> it = roomMap.values().iterator();
		
		Date before = new Date();
		before.setTime(0);
		
		while(it.hasNext()) {
			Room room = it.next();
			rooms.get(room.getRoomType()).put(room, before);
		}		
		
		for (Booking booking : bookingList) {
			
			Room[] bookedrooms = booking.getBookedRooms();
			Date endDate = booking.getEndDate();
			
			for (Room room : bookedrooms) {
				Date currentEnd = rooms.get(room.getRoomType()).get(room.getRoomNumber());
				if (endDate.after(currentEnd)) {
					rooms.get(room.getRoomType()).put(room, endDate);
				}
			}
		}
	}
	
	public Booking createBooking(User user, Guest guest, RoomType[] rooms, Date startDate, Date endDate){
		Booking booking = new Booking(rooms, guest, user, startDate, endDate);
		bookingList.add(booking);
		
		setRoomTypeBooked(rooms, startDate, endDate);
		
		return booking;
	}

	private void setRoomTypeBooked(RoomType[] rooms, Date start, Date end) {
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(start);
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		endCal.setTime(end);
		endCal.set(Calendar.HOUR_OF_DAY, 0);
		
		while (startCal.before(endCal)) {
			Date d = startCal.getTime();
			
		    if (bookedRooms.get(d) == null) {
		    	bookedRooms.put(d, new HashMap<RoomType, Integer>());
		    }
		    
		    for (int i = 0; i < rooms.length; i++) {
				if (bookedRooms.get(d).get(rooms[i]) == null) {
					bookedRooms.get(d).put(rooms[i], 1);
				} else {
					bookedRooms.get(d).put(rooms[i], bookedRooms.get(d).get(rooms[i]) + 1);
				}
			}
			
			startCal.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
	
	public AmenitiesBooking createAmenitiesBooking(User user, Guest guest, Amenity amenity, Date time){
		// TODO: Check availability of amenity for specified time before creating the booking
		AmenitiesBooking booking = new AmenitiesBooking(amenity, guest, user, time);
		amenitiesBookingList.add(booking);
		return booking;
	}
	
	/**
	 * Checks in a booking to a set of room numbers.
	 * 
	 * Sets the bookedRooms of the booking using the Hotels
	 * data of booked rooms, and the RoomTypes booked in the
	 * booking.
	 * 
	 * @param booking The booking with no rooms given.
	 */
	public void specifyRoomForBooking(Booking booking){
		if (booking.getBookedRooms() != null) {
			return;
		}
		
		RoomType[] types = booking.getReservedRoomTypes();
		Room[] bookedRooms = new Room[types.length];
		
		for (int i = 0; i < bookedRooms.length; i++) {
			Map<Room, Date> roomsOfType = rooms.get(types[i]);
			Set<Room> roomNrs = roomsOfType.keySet();
			for (Room nr : roomNrs) {
				if  (roomsOfType.get(nr).before(booking.getStartDate())) { // room is checked out or was never checked in before.
					bookedRooms[i] = nr;
					rooms.get(nr.getRoomType()).put(nr, booking.getEndDate()); // set booked room to have later end date.
					break;
				}
			}
			// if no room was found then bookedRooms[i] is null. throw error as booking should not allow this?
			if (bookedRooms[i] == null) {
				throw new RuntimeException("There is no available room for the booked period.");
			}
		}
		booking.setBookedRooms(bookedRooms);
	}

	public void createGuest(String name, String phoneNumber, String passPort){
		guestList.add(new Guest(name,phoneNumber,passPort));
	}
	
	public void addRoom(Room room){
		roomMap.put(room.getRoomNumber(),room);
		setupBookedRooms();
	}
	
	public void removeRoom(Room room){
		roomMap.remove(room);
		setupBookedRooms();
	}
	
	public boolean addRoomType(RoomType roomtype){
		return getRoomTypeList().add(roomtype);
	}
	
	public boolean removeRoomType(RoomType roomtype){
		// TODO: Edit all rooms of this type, if we allow removal
		// otherwise only remove types with no rooms.
		return getRoomTypeList().remove(roomtype);
	}
	
	public boolean addAmenity(Amenity amenity){
		return amenitiesList.add(amenity);
	}
	
	public boolean removeAmenity(Amenity amenity){
		return amenitiesList.remove(amenity);
	}
	
	public boolean addUser(User user){
		return userList.add(user);
	}
	
	public boolean removeUser(User user){
		return userList.remove(user);
	}
	
	public User getUserByUsername(String username){
		System.out.println(userList.size());
		System.out.println(userList.get(0).getUsername());
		
		for(User temp : userList){
			if(temp.getUsername().equals(username))
				return temp;
		}
		
		return null;
	}
	
	// Tom removed this.
	/*public boolean logInUser(User user, String username, String password){
		if(!loggedInUsers.contains(user) && userList.contains(user)){
			if(user.logIn(username, password)){
				return loggedInUsers.add(user);
			}
		}
		
		return false;
	}
	
	public boolean logOutUser(User user){
		if(loggedInUsers.contains(user)){
			user.logOut();
			return loggedInUsers.remove(user);
		}
		
		return false;
	}
*/
	public Set<RoomType> getRoomTypeList() {
		return roomTypeList;
	}
	
	
	/**
	 * Returns the room types that are available for a given set of days.
	 * 
	 * @param start The check in time as a Date with Hours=00, Minutes=00
	 * @param end The check out time as a Date with Hours=00, Minutes=00
	 * @return The types of rooms in the hotel that are available from
	 *  startDate to endDate
	 */
	public Set<RoomType> getAvailableRoomTypes(Date start, Date end) throws HotelFullException {
		Set<RoomType> available = new HashSet<RoomType>();
		
		List<Date> fullDates = new LinkedList<Date>();
		
		available.addAll(getRoomTypeList());
		
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(start);
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		endCal.setTime(end);
		endCal.set(Calendar.HOUR_OF_DAY, 0);
		
		while (startCal.before(endCal)) {
			Date d = startCal.getTime();
			
			int types = getRoomTypeList().size();
			
			if (bookedRooms.get(d) != null) {
				for (RoomType roomType : getRoomTypeList()) {
					if (bookedRooms.get(d).containsKey(roomType)) { // There is some booking for this type
						int bookedNr = bookedRooms.get(d).get(roomType);
						
						// If fully booked then remove the room type from options.
						if (bookedNr == rooms.get(roomType).size()) {
							available.remove(roomType);
							types--;
						}
					}
				}
			}
			
			if (types == 0) {
				// all types full this day
				fullDates.add(d);
			}
			
			startCal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		if (!fullDates.isEmpty()) {
			throw new HotelFullException(fullDates);
		}
		
		return available;
	}
	
	public Booking getBookingById(int id) {
		for (Booking b : bookingList) {
			if (b.getBookingId() == id) {
				return b;
			}
		}
		return null;
	}

	public Room getRoomByNumber(String roomNumber) {
		return roomMap.get(roomNumber);
	}
}
