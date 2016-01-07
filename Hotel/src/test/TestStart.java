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
		
		/*
		 *  Test what happens if we try to book a room that is not available 
		 */
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,07),new Date(2016,01,14)));
		
		
		
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("123456", new Date(2016,01,07), new Date(2016,01,14), "double")));
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("234567", new Date(2016,01,07), new Date(2016,01,14), "double")));
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,07),new Date(2016,01,14)));
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("345678", new Date(2016,01,07), new Date(2016,01,14), "double")));
		
		/*
		 * What happens if we try to cancel a booking and then book with another guest
		 */
		
		hc.getBookingInterface().cancelBooking(hc.getBookingInterface().getBookings("123456").get(0));
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,07),new Date(2016,01,14)));
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("345678", new Date(2016,01,07), new Date(2016,01,14), "double")));
		
		/*
		 * What happens if the start date is later than end date
		 */
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("123456", new Date(2017,01,07), new Date(2016,01,14), "single")));
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("123456", new Date(2016,02,07), new Date(2016,01,14), "single")));
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("123456", new Date(2016,01,15), new Date(2016,01,14), "single")));
		
		/*
		 * test check-in same person twice without cleaning  
		 */
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,07),new Date(2016,01,14)));
		System.out.println(hc.getCheckInOut().checkIn(hc.getBookingInterface().getBookings("234567").get(0)));
		System.out.println(hc.getCheckInOut().checkOut(hc.getBookingInterface().getBookings("234567").get(0)));
		System.out.println(hc.getCheckInOut().checkIn(hc.getBookingInterface().getBookings("234567").get(0)));
		
		/*
		 * Test if rooms is available again after the stay
		 */
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,15),new Date(2016,01,20)));
		
		/*
		 * Test to check-in someone twice and checkout someone twice
		 */
		System.out.println(hc.getCheckInOut().checkIn(hc.getBookingInterface().getBookings("345678").get(0)));
		System.out.println(hc.getCheckInOut().checkIn(hc.getBookingInterface().getBookings("345678").get(0)));
		System.out.println(hc.getCheckInOut().checkOut(hc.getBookingInterface().getBookings("345678").get(0)));
		System.out.println(hc.getCheckInOut().checkOut(hc.getBookingInterface().getBookings("345678").get(0)));
		
		
	
	}
}
