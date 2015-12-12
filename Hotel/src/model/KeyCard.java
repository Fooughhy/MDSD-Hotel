package model;

public class KeyCard {
	private Room room;
	
	public KeyCard(){
		;
	}
	
	public KeyCard(Room room){
		connectToRoom(room);
	}
	
	public void connectToRoom(Room room){
		this.room = room;
	}
	
	public void disconnectFromRoom(){
		connectToRoom(null);
	}
}
