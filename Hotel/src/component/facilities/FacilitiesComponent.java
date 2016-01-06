package component.facilities;

import java.util.Date;

import component.interfaces.AdminRoomManagement;
import component.interfaces.AmenitiesBooking;
import component.interfaces.AmenitiesManagement;
import component.interfaces.RoomManagement;
import component.interfaces.ViewRoom;

public class FacilitiesComponent implements ViewRoom, AmenitiesManagement, AmenitiesBooking, RoomManagement, AdminRoomManagement {

	@Override
	public boolean createRoomType(String type, int basePrice) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean changeRoomPrice(String type, int newPrice) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRoomType(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createRoom(String type, int number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRoom(int number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerKeyCard(int cardId, int roomNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unregisterKeyCard(int cardId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cleanRoom(Date when) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setRoomUnclean(Date when) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkRoomStatus() {
		// TODO Auto-generated method stub
		return false;
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
	public String getRoomType(int roomNr) {
		// TODO Auto-generated method stub
		return null;
	}


}
