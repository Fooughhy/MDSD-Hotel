package test;

import java.util.Date;

import component.HotelSystemComponent;

public class TestStart {

	HotelSystemComponent hc = new HotelSystemComponent();
	
	public void start(){
		hc.getAdminRoomManagement().createRoomType("single", 500);
		hc.getAdminRoomManagement().createRoomType("double", 1000);
		hc.getAdminRoomManagement().createRoomType("tripple", 1200);
		hc.getAdminRoomManagement().createRoomType("penthouse", 4000);
		hc.getAdminRoomManagement().createRoom("single", "101");
		hc.getAdminRoomManagement().createRoom("single", "102");
		hc.getAdminRoomManagement().createRoom("single", "103");
		hc.getAdminRoomManagement().createRoom("double", "201");
		hc.getAdminRoomManagement().createRoom("double", "202");
		hc.getAdminRoomManagement().createRoom("tripple", "203");
		hc.getAdminRoomManagement().createRoom("tripple", "204");
		hc.getAdminRoomManagement().createRoom("penthouse", "301");
		hc.getGuestInterface().createGuest("123456", "Tobbe", "Foughman", "tobbe@lalala.com", "0707123456");
		hc.getGuestInterface().createGuest("234567", "Rasti", "Tengman", "rasti@lalala.com", "0736332151");
		hc.getGuestInterface().createGuest("345678", "Tom", "Foughman", "tobbe@lalala.com", "0707111111");
		
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,07),new Date(2016,01,14)));
		hc.getBookingInterface().createBooking("123456", new Date(2016,01,07), new Date(2016,01,14), "single");
		hc.getBookingInterface().createBooking("234567", new Date(2016,01,07), new Date(2016,01,14), "single");
		hc.getBookingInterface().getBookings("123456");
		System.out.println(hc.getBookingInterface().getBookings("123456"));
	}
}
