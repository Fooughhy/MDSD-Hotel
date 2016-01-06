package model;

import java.util.Date;

public class Room {
	private String roomNumber;
	private RoomType roomType;
	private boolean clean = true;
	private Date lastcleaned;
	private Date lastcheckout;
	

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

	public Date getLastcleaned() {
		return lastcleaned;
	}
	
	/**
	 * Set room to clean, call after cleaning.
	 * @date The Date of the cleaning.
	 */
	public void setClean(Date date) {
		if (!clean && date.after(lastcheckout)) {
			clean = true;
			lastcleaned = date;
		}
	}
	
	/**
	 * Set room to dirty, call after check out.
	 * @date the Date of the checkout.
	 */
	public void setDirty(Date date) {
		clean = false;
		lastcheckout = date;
	}
}
