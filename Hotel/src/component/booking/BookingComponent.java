package component.booking;

import component.model.*;
import component.model.Booking.BookingStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import component.interfaces.BookingInterface;
import component.interfaces.CheckInOut;
import component.interfaces.GuestInterface;

public class BookingComponent implements BookingInterface, CheckInOut, GuestInterface {
	
	private Hotel hotel;
	
	public BookingComponent(Hotel hotel){
		this.hotel = hotel;
	}
	
	@Override
	public Map<String, Long> checkIn(long bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		hotel.specifyRoomForBooking(booking);
		Map<String, Long> map = new TreeMap<String, Long>();
		
		for(Room room : booking.getBookedRooms()){
			map.put(room.getRoomNumber(), hotel.addKeyCardToRoom(room));
		}
		
		booking.updateStatus(BookingStatus.IN);
		
		return map;
	}

	@Override
	public boolean checkOut(int bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		
		for(Room room : booking.getBookedRooms()){
			hotel.removeAllKeyCardsFromRoom(room.getRoomNumber());
			room.setDirty(Calendar.getInstance().getTime());
		}
		
		booking.updateStatus(BookingStatus.OUT);
		
		return true;
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
		hotel.createBooking(guest, rt, from, to);
		return 0;
	}

	@Override
	public void cancelBooking(int bookingNr) {
		hotel.removeBooking(hotel.getBookingById(bookingNr));
	}

	@Override
	public List<Long> getBookings(String passportNr) {
		Guest guest = hotel.findGuestByPassport(passportNr);
		List<Long> temp = new ArrayList<>();
		for(Booking booking : hotel.findBookingsByGuest(guest)){
			temp.add(booking.getBookingId());
		}
		return temp;
		
		
	}

	@Override
	public String displayBookingInfo(long bookingNr) {
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
	public Set<String> availableTypes(Date from, Date to) {
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
		hotel.addGuest(new Guest(fName, phone, passportNr,lName,email));
		return true;
	}

	@Override
	public boolean changeEmail(String passportNr, String email) {
		try{
			hotel.getGuest(passportNr).setEmail(email);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean changePhone(String passportNr, String phone) {
		try{
			hotel.getGuest(passportNr).setGuestPhoneNumber(phone);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public String getName(String passportNr) {
		try{
			return hotel.getGuest(passportNr).getGuestName();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public String getEmail(String passportNr) {
		try{
			return hotel.getGuest(passportNr).getEmail();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public String getPhone(String passportNr) {
		try{
			return hotel.getGuest(passportNr).getGuestPhoneNumber();
		}catch(Exception e){
			return null;
		}
	}
}
