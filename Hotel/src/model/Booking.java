package model;

import java.util.Date;

public class Booking {

	private static long latestBookingId;
	private final long bookingId;

	private Guest bookingGuest;
	private User bookingReceptionist;
	
	private RoomType[] reservedRoomTypes;
	private Room[] bookedRooms;
	private Date startDate;
	private Date endDate;
	
	public Booking(RoomType[] rooms, Guest guest, User receptionist, Date startDay, Date endDay){
		bookingId = getNewUniqueBoookingId();
		reservedRoomTypes = rooms;
		bookingGuest = guest;
		bookingReceptionist = receptionist;
		startDate = startDay;
		endDate = endDay;
	}
	
	private static synchronized long getNewUniqueBoookingId(){
		return latestBookingId++;
	}
	
	public long getBookingId(){
		return bookingId;
	}
	
	public Guest getBookingGuest() {
		return bookingGuest;
	}

	public void setBookingGuest(Guest bookingGuest) {
		this.bookingGuest = bookingGuest;
	}

	public Room[] getBookedRooms() {
		return bookedRooms;
	}

	public void setReservedRoomTypes(RoomType[] reservedTypes){
		reservedRoomTypes = reservedTypes;
	}
	
	public RoomType[] getReservedRoomTypes(){
		return reservedRoomTypes;
	}
	
	public void setBookedRooms(Room[] bookedRooms) {
		this.bookedRooms = bookedRooms;
	}

	public User getBookingReceptionist() {
		return bookingReceptionist;
	}

	public void setBookingReceptionist(User bookingReceptionist) {
		this.bookingReceptionist = bookingReceptionist;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Booking){
			Booking other = (Booking) o;
			
			result = bookingGuest.equals(other.bookingGuest) && bookingReceptionist.equals(other.bookingReceptionist) && 
					 startDate.equals(other.startDate) && endDate.equals(other.endDate) && bookedRooms.equals(other.bookedRooms) && bookingId == other.bookingId;
		}
		
		return result;
	}
}
