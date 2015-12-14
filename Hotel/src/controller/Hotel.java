package controller;

import java.util.*;

import model.*;
import view.*;

public class Hotel {
	
	private View view;
	private Map<Integer,Room> roomMap;
	private List<Guest> guestList;
	private List<Amenity> amenitiesList;
	private List<User> userList;
	private List<RoomType> roomTypeList;
	
	private List<User> loggedInUsers;
	
	public Hotel(){
		view = new View();
		
		roomMap = new TreeMap<Integer,Room>();
		guestList = new ArrayList<>();
		amenitiesList = new ArrayList<>();
		userList = new ArrayList<>();
		loggedInUsers = new ArrayList<>();
		
		userList.add(new User("admin", "admin", UserType.Admin));
		
	}
	
	public Room addRoom(Room room, int number){
		return roomMap.put(number, room);
	}
	
	public boolean removeRoom(Room room){
		return roomMap.remove(room.getRoomNumber(), room);
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
