package component.facilities;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import component.interfaces.AdminRoomManagement;
import component.interfaces.AmenitiesBooking;
import component.interfaces.AmenitiesManagement;
import component.interfaces.RoomManagement;
import component.interfaces.ViewFacilities;
import component.model.Hotel;
import component.model.RoomType;
import model.Room;

public class FacilitiesComponent implements ViewFacilities, AmenitiesManagement, AmenitiesBooking, RoomManagement, AdminRoomManagement {
	
	private Hotel hotel;
	
	public FacilitiesComponent(Hotel hotel){
		this.hotel = hotel;
	}
	
	@Override
	public boolean createRoomType(String type, int basePrice) {
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
	public boolean createRoom(String type, String number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRoom(int number) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean cleanRoom(Date when, String roomNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setRoomUnclean(Date when, String roomNr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkRoomStatus(String roomNr) {
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
	public String getRoomType(String roomNr) {
		return hotel.getRoomByNumber(roomNr).getRoomType().getRoomTypeName();
	}

	@Override
	public Set<String> getRooms() {
		Set<String> temp = new HashSet<String>();
		for(component.model.Room room : hotel.getRooms()){
			temp.add(room.getRoomNumber());
		}
		return temp;
	}

	@Override
	public Set<String> getRooms(String roomType) {
		Set<String> temp = new HashSet<String>();
		for(component.model.Room room : hotel.getRooms()){
			if(room.getRoomType().equals(roomType))
				temp.add(room.getRoomNumber());
		}
		return temp;
	}

	@Override
	public Set<String> getTypeList() {
		Set<String> temp=new HashSet<String>();
		for(RoomType roomType : hotel.getRoomTypeList()){
			temp.add(roomType.getRoomTypeName());
		}
		return temp;
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
