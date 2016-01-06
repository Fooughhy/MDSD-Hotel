package component;

import component.booking.BookingComponent;
import component.facilities.FacilitiesComponent;
import component.interfaces.AdminRoomManagement;
import component.interfaces.AmenitiesBooking;
import component.interfaces.AmenitiesManagement;
import component.interfaces.Billing;
import component.interfaces.BookingInterface;
import component.interfaces.CheckInOut;
import component.interfaces.DiscountManagement;
import component.interfaces.Discounts;
import component.interfaces.GuestInterface;
import component.interfaces.RoomManagement;
import component.interfaces.ViewRoom;
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
	
	public AmenitiesBooking getAmenitiesBooking() {
		return facilities;
	}
	
	public AmenitiesManagement getAmenitiesManagement() {
		return facilities;
	}
	
	public Billing getBilling() {
		return payment;
	}
	
	public BookingInterface getBookingInterface() {
		return booking;
	}
	
	public CheckInOut getCheckInOut() {
		return booking;
	}
	
	public DiscountManagement getDiscountManagement() {
		return payment;
	}
	
	public Discounts getDiscounts() {
		return payment;
	}
	
	public GuestInterface getGuestInterface() {
		return booking;
	}
	
	public RoomManagement getRoomManagement() {
		return facilities;
	}
	
	public ViewRoom getViewRoom() {
		return facilities;
	}
	
	// TODO: fill in all
}
