package component.model;

import java.util.Date;

import access.User;

public class Booking {

	public enum BookingStatus {
		BOOKED, IN, OUT;
	}
	private BookingStatus status = BookingStatus.BOOKED;
	
	private static long latestBookingId;
	private final long bookingId;

	private Guest bookingGuest;
	private User bookingReceptionist;
	
	private RoomType[] reservedRoomTypes;
	private Room[] bookedRooms;
	private Date startDate;
	private Date endDate;
	private int totalCost;
	
	public Booking(RoomType[] rooms, Guest guest, Date startDay, Date endDay){
		bookingId = getNewUniqueBoookingId();
		reservedRoomTypes = rooms;
		bookingGuest = guest;
		startDate = startDay;
		endDate = endDay;
	}
	
	/**
	 * Set the status of this booking. Status can only change in the order
	 * BOOKED -> IN -> OUT
	 * @param newStatus The new status
	 * @return True if status was changed.
	 */
	public boolean updateStatus(BookingStatus newStatus) {
		switch (status) {
		case BOOKED:
			if (newStatus == BookingStatus.IN) status = BookingStatus.IN;
			return true;
		case IN:
			if (newStatus == BookingStatus.OUT) status = BookingStatus.OUT;
			return true;
		default:
			return false;
		}
	}
	
	public BookingStatus getStatus() {
		return status;
	}
	
	private static synchronized long getNewUniqueBoookingId(){
		return latestBookingId++;
	}
	
	private static long daysBetween(Date one, Date two){
		long difference = (one.getTime()-two.getTime())/86400000; return Math.abs(difference); 
	}
	
	public int getBookedNights(){
		return (int) daysBetween(startDate,endDate);
	}
	
	public void addCost(int amount){
		this.totalCost += amount;
	}
	public int getTotalCost(){
		return this.totalCost;
	}
	
	public long getBookingId(){
		return bookingId;
	}
	
	public Guest getBookingGuest() {
		return bookingGuest;
	}

	public void setBookingGuest(Guest bookingGuest) {
		this.bookingGuest = bookingGuest;
	}

	public Room[] getBookedRooms() {
		return bookedRooms;
	}

	public void setReservedRoomTypes(RoomType[] reservedTypes){
		reservedRoomTypes = reservedTypes;
	}
	
	public RoomType[] getReservedRoomTypes(){
		return reservedRoomTypes;
	}
	
	public void setBookedRooms(Room[] bookedRooms) {
		this.bookedRooms = bookedRooms;
	}

	public User getBookingReceptionist() {
		return bookingReceptionist;
	}

	public void setBookingReceptionist(User bookingReceptionist) {
		this.bookingReceptionist = bookingReceptionist;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Booking){
			Booking other = (Booking) o;
			
			result = bookingGuest.equals(other.bookingGuest) && //the equals on the list fucked up.
					 startDate.equals(other.startDate) && endDate.equals(other.endDate) &&/* bookedRooms.equals(other.bookedRooms) &&*/ bookingId == other.bookingId;
		}
		
		return result;
	}
	
	private String listAllRoomTypesAsStrings(){
		String temp ="";
		for(RoomType room : reservedRoomTypes){
			temp +=(" "+room.getRoomTypeName());
		}
		return temp;
	}
	
	@Override
	public String toString(){
		return "Booking ID: "+this.bookingId+"\nGuest: "+this.bookingGuest.getGuestName()
		+"\nRoom: "+listAllRoomTypesAsStrings();
	}
}
