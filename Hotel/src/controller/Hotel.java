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
	
	private List<User> loggedInUsers;
	
	public Hotel(){
		view = new View();
		
		roomMap = new TreeMap<String,Room>();
		guestList = new ArrayList<>();
		amenitiesList = new ArrayList<>();
		userList = new ArrayList<>();
		loggedInUsers = new ArrayList<>();
		
		userList.add(new User("admin", "admin", UserType.Admin));
		
	}
	public void createBooking(User user,Guest guest,int roomnr,Date startDate,Date endDate){ //TODO remember to change roomnumber!
                //TODO FIX THIS SHEEEET
                System.out.println("this is the created booking, its not saved right now");
	}

	public void createGuest(String name, String phoneNumber, String passPort){
                guestList.add(new Guest(name,phoneNumber,passPort));
	}
	public void addRoom(Room room, String number){
		roomMap.put(number, room);
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
	
	public boolean logInUser(User user, String username, String password){
		if(!loggedInUsers.contains(user)){
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
