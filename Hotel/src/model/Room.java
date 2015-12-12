package model;

import java.util.*;

public class Room {
	private int roomNumber;
	private List<RoomType> roomTypesList;
	
	public Room(int number){
		roomNumber = number;
		roomTypesList = new ArrayList<RoomType>();
	}
	
	public Room(int number, List<RoomType> initialRoomTypes){
		roomNumber = number;
		roomTypesList = initialRoomTypes;
	}
	
	public void modifyRoomNumber(int number){
		roomNumber = number;
	}
	
	public boolean addRoomType(RoomType roomtype){
		if(!roomTypesList.contains(roomtype)){
			return roomTypesList.add(roomtype);
		}
		
		return false;
	}
	
	public boolean removeRoomType(RoomType roomtype){
		return roomTypesList.remove(roomtype);
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Room){
			Room other = (Room) o;
			
			result = roomNumber == other.roomNumber && roomTypesList.equals(other.roomTypesList);
		}
		
		return result;
	}
}
