package component.facilities;

import java.util.Date;
import java.util.Set;

import component.interfaces.AdminRoomManagement;
import component.interfaces.AmenitiesBooking;
import component.interfaces.AmenitiesManagement;
import component.interfaces.RoomManagement;
import component.interfaces.ViewRoom;
import component.model.Room;

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
	public boolean cleanRoom(Date when, int roomNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setRoomUnclean(Date when, int roomNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkRoomStatus(int roomNr) {
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

	@Override
	public Room getRoomByNumber(int roomNr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRooms(String roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getTypeList() {
		// TODO Auto-generated method stub
		return null;
	}


}
