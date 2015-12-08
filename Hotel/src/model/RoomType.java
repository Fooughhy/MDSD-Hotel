package model;

public class RoomType {
	private String roomTypeName;
	
	public RoomType(String name){
		setRoomTypeName(name);
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
}
