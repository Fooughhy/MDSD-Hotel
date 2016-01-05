package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import controller.Hotel;
import model.Booking;
import model.Guest;
import model.Room;
import model.RoomType;
import model.User;
import model.UserType;

public class ConsolView {

	public static void main(String[] args) {
		new ConsolView();
	}

	private Hotel hotel;
	private User user;
	Scanner s;

	public ConsolView() {
		s = new Scanner(System.in);
		System.out.println("Creating a Hotel");
		this.hotel = new Hotel();
		System.out.println("Hotel created");
		this.user = loggin();
		System.out.println("User logged in");
		String command;
		while (true) {
			System.out.print(user.getUsername() + ":");
			command = s.next();

			if (command.equals("addRoom"))
				addRoom();
			else if (command.equals("bookRoom"))
				bookRoom();
			else if (command.equals("bookRoomTest"))
				bookRoomTest();
			else if (command.equals("addGuest"))
				addGuest();
			else if (command.equals("checkin"))
				checkIn();
		}
	}

	private void checkIn() {
		System.out.print("Provide Booking Id: ");
		String id = s.next();
		Booking booking = hotel.getBookingById(Integer.parseInt(id));
		
		hotel.specifyRoomForBooking(booking);
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");
		
		System.out.println("The following rooms were assigned for booking ID: " + booking.getBookingId() + " between "
				+ ft.format(booking.getStartDate()) + " and " + ft.format(booking.getEndDate()) + ".");
		for (int i = 0; i < booking.getBookedRooms().length; i++) {
			System.out.println("Room number: " + booking.getBookedRooms()[i].getRoomNumber());
		}
	}

	private Guest addGuest() {
		// TODO add security clearance
		System.out.print("Guest name: ");
		String name = s.next();
		System.out.print("Phone number: ");
		String phoneNumber = s.next();
		System.out.print("passport number: ");
		String passPortNumber = s.next();
		System.out.println("creating guest");
		return new Guest(name, phoneNumber, passPortNumber);
	}

	private void bookRoomTest() {
		System.out.print("enter start date (yyyy-mm-dd):");
		String sDate = s.next();
		System.out.print("enter end date (yyyy-mm-dd):");
		String eDate = s.next();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = new Date();
		Date endDate = new Date();

		try {
			startDate = format.parse(sDate);
			endDate = format.parse(eDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("Select a room type, the available types are: ");
		for (RoomType type : hotel.getRoomTypeList()) {
			System.out.println(type.getRoomTypeName());
		}

		RoomType validRoomType = null;
		while (validRoomType == null) {
			System.out.println("Enter your RoomType: ");
			String rType = s.next();
			for (RoomType type : hotel.getRoomTypeList()) {
				if (type.getRoomTypeName().equals(rType)) {
					validRoomType = type;
					break;
				}
			}
			if (validRoomType == null) {
				System.err.println("RoomType not valid!");
			}
		}

		Guest guest = addGuest();
		System.out.println("guest created BUT NOT SAVED!!");

		RoomType[] types = new RoomType[] { validRoomType };
		Booking book = hotel.createBooking(this.user, guest, types, startDate, endDate);
		System.out.println("Booking process completed for booking with ID:" + book.getBookingId());
	}

	private void bookRoom() {
		/*
		 * //everyone permitted? still the admin thing is really strange!!!
		 * System.out.println("enter start-date: "+
		 * "(this is a bit complicated so for now we asume u enter today and only today)"
		 * ); //TODO add startDate and endDate System.out.println(
		 * "Searching for free rooms"+
		 * "some algortihm here. for now ALL rooms are available");
		 * 
		 * for(int i = 0;
		 * i<hotel.getAvailableRooms(startDate,endDate).size(),i++){
		 * println("room:" hotel.); }
		 */
	}

	private void addRoom() {
		if (user.getUserType() != UserType.Admin) {
			System.out.println("nope!");
			return;
		}
		System.out.print("Input the room number: ");
		String roomNumber = s.next();

		RoomType type = null;
		while (type == null) {
			System.out.print("Input the room type: ");
			String stype = s.next();
			Set<RoomType> types = hotel.getRoomTypeList();
			RoomType typ = new RoomType(stype);

			boolean e = false;
			for (RoomType roomType : types) {
				if (roomType.equals(typ)) {
					type = roomType;
					e = true;
				}
			}

			if (!e) {
				hotel.addRoomType(typ);
				type = typ;
			}
		}

		hotel.addRoom(new Room(roomNumber, type));
		System.out.println("Room number " + roomNumber + " is now created.");
	}

	private User loggin() {
		User temp = null;
		boolean rdyToGo = false;
		String username = "";
		while (!rdyToGo) {
			System.out.println("Please insert username: ");
			username = s.next();
			System.out.println("Please insert password: ");
			String pass = s.next();
			temp = hotel.getUserByUsername(username);
			if (temp == null)
				System.out.println("cant find username");
			else if (temp.getPassword().equals(pass))
				rdyToGo = true;
		}
		System.out.println("logged in yeeey");

		return temp;
	}
}
