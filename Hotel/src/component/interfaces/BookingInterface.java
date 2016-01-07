package component.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BookingInterface {

	/**
	 * Create a new booking.
	 * @param passportNr The guests passport number.
	 * @param from The start date of the booking.
	 * @param to The end date of the booking.
	 * @param roomType The type of the initial room to book.
	 * @return The Id of the booked room.
	 */
	public long createBooking(String passportNr, Date from, Date to, String roomType);
	
	/**
	 * Cancels an existing booking.
	 * @param bookingNr The Id of the booking to cancel.
	 */
	public void cancelBooking(int bookingNr);
	
	/**
	 * Get all bookings for a guest.
	 * @param passportNr The passport number of the guest.
	 * @return All the booking Id:s of the guest.
	 */
	public List<Long> getBookings(String passportNr);
	
	/**
	 * Displays a booking as a String.
	 * @param bookingNr The Id of the booking.
	 * @return A human readable string.
	 */
	public String displayBookingInfo(long bookingNr);
	
	/**
	 * Checks if a day is fully booked for a room type.
	 * @param roomType The type to check.
	 * @param day The day to check.
	 * @return True if the hotel is full.
	 */
	public boolean isFullyBooked(String roomType, Date day);
	
	/**
	 * Checks if a day is fully booked.
	 * @param day The day to check.
	 * @return True if the hotel is full.
	 */
	public boolean isFullyBooked(Date day);
	
	/**
	 * Returns a list of the names of the available room types for a range of days.
	 * @param from The start date.
	 * @param to The end date.
	 * @return A list of names for the room types.
	 */
	public Set<String> availableTypes(Date from, Date to);
	
	/**
	 * Adds an extra room to a booking.
	 * @param bookingNr The booking to add the room to.
	 * @param roomType The room type to add.
	 * @return True if the room was added. False if it was not.
	 */
	//public boolean addRoom(int bookingNr, String roomType);
	
	/**
	 * Removes an extra room from a booking.
	 * @param bookingNr The booking to remove the room from.
	 * @param roomType The room type to remove.
	 * @return True if the room was removed. False if it was not.
	 */
	//public boolean removeRoom(int bookingNr, String roomType);
	
	/**
	 * Checks the cost of the booking.
	 * <P>
	 * 2 fields: Cost, Discount
	 * 
	 * @param bookingNr The booking to check.
	 * @return An array with 2 fields, the total costs and the total discounts.
	 */
	public int[] checkCost(int bookingNr);
	
}
