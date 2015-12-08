package model;

public class Room {
	private int roomNumber;
	private RoomType[] roomTypesList;
	
	public Room(int number){
		roomNumber = number;
	}
	
	public Room(int number, RoomType[] initialRoomTypes){
		roomNumber = number;
		roomTypesList = initialRoomTypes;
	}
	
	
}
