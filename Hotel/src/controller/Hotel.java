package controller;

import java.util.ArrayList;
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
	
	private List<User> loggedInUsers;
	
	private Map<RoomType, Map<Room, Date>> rooms;
	
	public Hotel(){
		view = new View();
		
		roomMap = new TreeMap<String,Room>();
		guestList = new ArrayList<Guest>();
		amenitiesList = new ArrayList<Amenity>();
		userList = new ArrayList<User>();
		loggedInUsers = new ArrayList<User>();
		roomTypeList = new HashSet<RoomType>();
		amenitiesBookingList = new ArrayList<AmenitiesBooking>();
		bookingList = new ArrayList<Booking>();
		
		userList.add(new User("admin", "admin", UserType.Admin));
		
		setupBookedRooms();
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
		System.out.println("this is the created booking, its not saved right now");
		// TODO: Check availability of roomtypes before creating the booking!
		Booking booking = new Booking(rooms, guest, user, startDate, endDate);
		bookingList.add(booking);
		return booking;
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
		System.out.println("test");
		for (int i = 0; i < bookedRooms.length; i++) {
			System.out.println(bookedRooms[i].getRoomNumber());
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
	
	public boolean logInUser(User user, String username, String password){
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

	public Set<RoomType> getRoomTypeList() {
		return roomTypeList;
	}
	
	public Booking getBookingById(int id) {
		for (Booking b : bookingList) {
			if (b.getBookingId() == id) {
				return b;
			}
		}
		return null;
	}
}
