package model;

public class AmenitiesBooking {
	private Amenity bookedAmenity;
	private Guest bookingGuest;
	private User bookingReceptionist;
	
	public AmenitiesBooking(Amenity amenity, Guest guest, User receptionist){
		setBookedAmenity(amenity);
		setBookingGuest(guest);
		setBookingReceptionist(receptionist);
	}

	public User getBookingReceptionist() {
		return bookingReceptionist;
	}

	public void setBookingReceptionist(User bookingReceptionist) {
		this.bookingReceptionist = bookingReceptionist;
	}

	public Guest getBookingGuest() {
		return bookingGuest;
	}

	public void setBookingGuest(Guest bookingGuest) {
		this.bookingGuest = bookingGuest;
	}

	public Amenity getBookedAmenity() {
		return bookedAmenity;
	}

	public void setBookedAmenity(Amenity bookedAmenity) {
		this.bookedAmenity = bookedAmenity;
	}

}
