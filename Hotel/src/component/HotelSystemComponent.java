package component;

import component.booking.BookingComponent;
import component.facilities.FacilitiesComponent;
import component.interfaces.AdminRoomManagement;
import component.interfaces.BookingInterface;
import component.payment.PaymentSystemComponent;

public class HotelSystemComponent {

	private BookingComponent booking;
	private FacilitiesComponent facilities;
	private PaymentSystemComponent payment;
	
	public HotelSystemComponent() {
		booking = new BookingComponent();
		facilities = new FacilitiesComponent();
		payment = new PaymentSystemComponent();
	}
	
	public AdminRoomManagement getAdminRoomManagement() {
		return facilities;
	}
	
	public BookingInterface getBookingInterface() {
		return booking;
	}
	
	// TODO: fill in all
}
