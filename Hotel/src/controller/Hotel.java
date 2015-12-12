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
	
	public Hotel(){
		view = new View();
		
		roomMap = new TreeMap<Integer,Room>();
		guestList = new ArrayList<>();
		amenitiesList = new ArrayList<>();
		userList = new ArrayList<>();
		
		userList.add(new User("admin", "admin", UserType.Admin));
	}
	
	public Room addRoom(Room room, int number){
		return roomMap.put(number, room);
	}
	
	public boolean addRoomType(RoomType roomtype){
		return roomTypeList.add(roomtype);
	}
}
