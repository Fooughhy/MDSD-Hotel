package model;

import java.util.*;

public class Room {
	private String roomNumber;
	private List<RoomType> roomTypesList;

	public Room(String number){
		roomNumber = number;
		roomTypesList = new ArrayList<RoomType>();
	}
	
	public Room(String number, List<RoomType> initialRoomTypes){
		roomNumber = number;
		roomTypesList = initialRoomTypes;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<RoomType> getRoomTypesList() {
		return roomTypesList;
	}

	public void setRoomTypesList(List<RoomType> roomTypesList) {
		this.roomTypesList = roomTypesList;
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
