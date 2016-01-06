package component.booking;

import component.model.*;

import java.util.Date;
import java.util.List;

import component.interfaces.BookingInterface;
import component.interfaces.CheckInOut;
import component.interfaces.GuestInterface;

public class BookingComponent implements BookingInterface, CheckInOut, GuestInterface {
	
	Hotel hotel = new Hotel();
	
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
		Booking booking = hotel.getBookingById(bookingNr);
		Room[] rooms = booking.getBookedRooms();
		for(Room room : rooms){
			hotel.addKeyCard(new KeyCard(room));
		}
		return true;
	}

	@Override
	public boolean clearKeyCards(int bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		
		for(Room room : booking.getBookedRooms()){
			List<KeyCard> cards = KeyCard.cardForRoom(hotel.getKeyCards(), room.getRoomNumber(), hotel);
			for(KeyCard card : cards){
				card.setRoom(null);
				hotel.getUnassignedKeyCards().add(card);
			}
		}
		
		return true;
	}

	@Override
	public int createBooking(String passportNr, Date from, Date to, String roomType) {
		Guest guest = hotel.getGuest(passportNr);
		RoomType rt = hotel.getRoomType(roomType);
		// TODO: get logged in user
		//hotel.createBooking(user, guest, rt, from, to);
		return 0;
	}

	@Override
	public void cancelBooking(int bookingNr) {
		hotel.removeBooking(hotel.getBookingById(bookingNr));
	}

	@Override
	public int[] getBookings(String passportNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String displayBookingInfo(int bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		return booking.toString();
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
	public int[] checkCost(int bookingNr) {
		int cost =hotel.getBookingById(bookingNr).getTotalCost();
		//cost-discount.
		int[] temp={cost,0}; // where the 0 is the discount
		return temp;
	}

	@Override
	public boolean isGuest(String passportNr) {
		Guest guest = hotel.getGuest(passportNr);
		if(guest == null)
			return false;
		return true;
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
