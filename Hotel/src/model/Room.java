package model;

import java.util.*;

public class Room {
	private int roomNumber; // should this not be a String?
	private List<RoomType> roomTypesList;

	public Room(int number){
		roomNumber = number;
		roomTypesList = new ArrayList<RoomType>();
	}
	
	public Room(int number, List<RoomType> initialRoomTypes){
		roomNumber = number;
		roomTypesList = initialRoomTypes;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<RoomType> getRoomTypesList() {
		return roomTypesList;
	}

	public void setRoomTypesList(List<RoomType> roomTypesList) {
		this.roomTypesList = roomTypesList;
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
