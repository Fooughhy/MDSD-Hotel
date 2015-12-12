package model;

import java.util.Date;

public class Booking {

	private static long latestBookingId;
	private final long bookingId;

	private Guest bookingGuest;
	private User bookingReceptionist;
	
	private Room[] bookedRooms;
	private Date[] bookedDays;
	
	public Booking(Room[] rooms, Guest guest, User receptionist, Date[] days){
		bookingId = getNewUniqueBoookingId();
		bookedRooms = rooms;
		bookingGuest = guest;
		bookingReceptionist = receptionist;
		bookedDays = days;
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
					 bookedDays.equals(other.bookedDays) && bookedRooms.equals(other.bookedRooms) && bookingId == other.bookingId;
		}
		
		return result;
	}
}
