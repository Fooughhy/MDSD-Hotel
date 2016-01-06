package model;


public class Room {
	private String roomNumber;
	private RoomType roomType;
	private boolean clean = true;

	public Room(String number, RoomType RoomType){
		roomNumber = number;
		roomType = RoomType;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Room){
			Room other = (Room) o;
			
			result = roomNumber == other.roomNumber && roomType.equals(other.roomType);
		}
		
		return result;
	}

	/**
	 * Set room to clean, call after cleaning.
	 */
	public void setClean() {
		clean = true;
	}
	
	/**
	 * Set room to dirty, call after check out.
	 */
	public void setDirty() {
		clean = false;
	}
}
