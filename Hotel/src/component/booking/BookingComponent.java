package component.booking;

import java.util.Date;

import component.interfaces.BookingInterface;
import component.interfaces.CheckInOut;
import component.interfaces.Discounts;
import component.interfaces.GuestInterface;

public class BookingComponent implements BookingInterface, CheckInOut, GuestInterface {

	@Override
	public boolean checkIn(int bookingNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkOut(int bookingNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createKeyCard(int bookingNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean clearKeyCards(int bookingNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int createBooking(String passportNr, Date from, Date to, String roomType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cancelBooking(int bookingNr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getBookings(String passportNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String displayBookingInfo(int bookingNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFullyBooked(String roomType, Date day) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFullyBooked(Date day) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] availableTypes(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addRoom(int bookingNr, String roomType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRoom(int bookingNr, String roomType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] checkCost(int bookingNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGuest(String passportNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createGuest(String passportNr, String fName, String lName, String email, String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeEmail(String passportNr, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePhone(String passportNr, String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName(String passportNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmail(String passportNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPhone(String passportNr) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
