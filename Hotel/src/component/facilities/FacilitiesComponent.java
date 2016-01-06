package component.facilities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import component.interfaces.AdminRoomManagement;
import component.interfaces.AmenitiesBooking;
import component.interfaces.AmenitiesManagement;
import component.interfaces.RoomManagement;
import component.interfaces.ViewFacilities;
import component.model.Booking;
import component.model.Hotel;
import component.model.Room;
import component.model.RoomType;

public class FacilitiesComponent implements ViewFacilities, AmenitiesManagement, AmenitiesBooking, RoomManagement, AdminRoomManagement {
	
	private Hotel hotel;
	
	public FacilitiesComponent(Hotel hotel){
		this.hotel = hotel;
	}
	
	@Override
	public boolean createRoomType(String type, int basePrice) {
		return hotel.addRoomType(new RoomType(type, basePrice));
	}
	
	@Override
	public boolean changeRoomPrice(String type, int newPrice) {
		RoomType room = hotel.getRoomType(type);
		return room.setRoomTypePrice(newPrice);
	}

	@Override
	public boolean removeRoomType(String type) {
		return hotel.removeRoomType(hotel.getRoomType(type));
	}

	@Override
	public boolean createRoom(String type, String number) {
		return hotel.addRoom(new Room(number, hotel.getRoomType(type)));
	}

	@Override
	public boolean removeRoom(String number) {
		hotel.removeRoom(hotel.getRoomByNumber(number));
		return true;
	}


	@Override
	public boolean cleanRoom(Date when, String roomNr) {
		return hotel.getRoomByNumber(roomNr).setClean(when);
	}

	@Override
	public boolean setRoomUnclean(Date when, String roomNr) {
		return hotel.getRoomByNumber(roomNr).setDirty(when);
	}

	@Override
	public boolean checkRoomStatus(String roomNr) {
		List<Booking> list = hotel.getBookingsList();
		Date date = Calendar.getInstance().getTime();
		
		for(Booking booking : list){
			if(booking.getStartDate().before(date) && booking.getEndDate().after(date)){
				return false;
			}
		}
		
		return hotel.getRoomByNumber(roomNr).getStatus();
	}

	@Override
	public boolean bookAmenity(String name, String passportNr, Date from, Date to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelAmenityBooking(int Id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getBookingIdByGuest(String passportNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getBookingIdByAmenity(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String displayBookingInfo(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkAvailability(String name, Date from, Date to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean createAmenity(String name, int maxCapacity, String description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAmenity(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getRoomType(String roomNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> getRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> getRooms(String roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getAmenities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAmenityCapacity(String amenityName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAmenityDescription(String amenityName) {
		// TODO Auto-generated method stub
		return null;
	}
}
