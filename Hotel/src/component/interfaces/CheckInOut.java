package component.interfaces;

import java.util.Map;

public interface CheckInOut {

	/**
	 * Check in a guest via the booking number.
	 * @param bookingNr The booking number.
	 * @return True if the check in was successful.
	 */
	public Map<String, Long> checkIn(long bookingNr, String passportNr);
	
	/**
	 * Check out a guest via the booking number.
	 * @param bookingNr The booking number.
	 * @return True if the check out was successful.
	 */
	public boolean checkOut(int bookingNr, String passportNr);
	
	/**
	 * Create a key card for all rooms in a booking.
	 * <P>
	 * The booking must be checked in.
	 * 
	 * @param bookingNr The booking number.
	 * @return True if a key card was created.
	 */
	public boolean createKeyCard(int bookingNr);
	
	/**
	 * Clear all key cards for all rooms in a booking.
	 * <P>
	 * The booking must be check in or checked out.
	 * 	
	 * @param bookingNr The booking number.
	 * @return True if all key cards were cleared.
	 */
	public boolean clearKeyCards(int bookingNr);
}
