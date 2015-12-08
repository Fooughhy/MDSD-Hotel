package model;

import java.util.Date;

public class Booking {

	private static long latestBookingId;
	private final long bookingId;

	private Guest bookingGuest;
	private Room[] bookedRooms;
	private User bookingReceptionist;
	private Date[] bookedDays;
	
	public Booking(Room[] rooms, Guest guest, User receptionist, Date[] days){
		bookingId = getUniqueBoookingId();
		bookedRooms = rooms;
		bookingGuest = guest;
		bookingReceptionist = receptionist;
		bookedDays = days;
	}
	
	private static synchronized long getUniqueBoookingId(){
		return latestBookingId++;
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
}
