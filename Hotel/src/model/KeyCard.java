package model;

public class KeyCard {
	private Room room;
	
	public KeyCard(){
		;
	}
	
	public KeyCard(Room room){
		setRoom(room);
	}
	
	public Room getRoom(){
		return room;
	}
	
	public boolean setRoom(Room room){
		this.room = room;
		return true;
	}
}
