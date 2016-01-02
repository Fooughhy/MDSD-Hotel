package controller;

import java.util.*;

import model.*;
import view.*;
import java.util.Date;

public class Hotel {
	
	private View view;
	private Map<String,Room> roomMap;
	private List<Guest> guestList;
	private List<Amenity> amenitiesList;
	private List<User> userList;
	private List<RoomType> roomTypeList;
	
	private List<AmenitiesBooking> amenitiesBookingList;
	private List<Booking> bookingList;
	
	private List<User> loggedInUsers;
	
	public Hotel(){
		view = new View();
		
		roomMap = new TreeMap<String,Room>();
		guestList = new ArrayList<>();
		amenitiesList = new ArrayList<>();
		userList = new ArrayList<>();
		loggedInUsers = new ArrayList<>();
		
		amenitiesBookingList = new ArrayList<>();
		bookingList = new ArrayList<>();
		
		userList.add(new User("admin", "admin", UserType.Admin));
		
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
	
	public void specifyRoomForBooking(Booking booking){
		// TODO: Book a specific room that minimizes unbooked time for rooms (?)
	}

	public void createGuest(String name, String phoneNumber, String passPort){
		guestList.add(new Guest(name,phoneNumber,passPort));
	}
	
	public void addRoom(Room room){
		roomMap.put(room.getRoomNumber(),room);
	}
	
	public void removeRoom(Room room){
		roomMap.remove(room);
	}
	
	public boolean addRoomTypeToRoom(Room room, RoomType roomtype){
		return room.addRoomType(roomtype);
	}
	
	public boolean removeRoomTypeFromRoom(Room room, RoomType roomtype){
		return room.removeRoomType(roomtype);
	}
	
	public boolean addRoomType(RoomType roomtype){
		return roomTypeList.add(roomtype);
	}
	
	public boolean removeRoomType(RoomType roomtype){
		return roomTypeList.remove(roomtype);
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
		User user;
		Iterator<User> i = userList.iterator();
		
		for(user = i.next(); i.hasNext(); i.next()){
			if(user.getUsername().equals(username)){
				return user;
			}
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
}
