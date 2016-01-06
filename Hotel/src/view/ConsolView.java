package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import controller.Hotel;
import model.Booking;
import model.Guest;
import model.HotelFullException;
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
		while (true) { // to make sure this is whereyou end up if you logg out.
			this.user = loggin();
			System.out.println("User logged in");
			String command;
			while (true) {
				System.out.print(user.getUsername() + ":");
				command = s.next();

				if (command.equals("addRoom")) { // admin
					if (user.getUserType() == UserType.Admin) {
						addRoom();
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("bookRoom")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						bookRoom();
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("addGuest")) { // MH & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						addGuest();
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("checkIn")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						checkIn();
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("logout")) { // all users.
					break;
				} else if (command.equals("checkOut")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}

				} else if (command.equals("bookAmenities")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("printReceipt")) { // MH & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("addChargesToBooking")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}

				} else if (command.equals("getAvailableRoomTypes")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("addingStayInformation")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("viewGuestInformation")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("lastCleanedDate")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("cancelBooking")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("assignExtraKeyCard")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						assignKeyCard(null);
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("checkPaymentStatus")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("checkNumberOfGuests")) { // HM & R
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Receptionist) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("viewKeyCard")) { // HM & admin
					if (user.getUserType() == UserType.HotelManager || user.getUserType() == UserType.Admin) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("createMasterKeyCard")) { // admin
					if (user.getUserType() == UserType.Admin) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("editRoom")) { // admin
					if (user.getUserType() == UserType.Admin) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("createUser")) { // admin
					if (user.getUserType() == UserType.Admin) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				} else if (command.equals("markRoomAsCleaned")) { // C
					if (user.getUserType() == UserType.Cleaner) {
						
					} else {
						System.out.println("This account (" + user.getUserType()
								+ ") has not the right clearance to run this command.");
					}
				}
			}
		}
	}

	private void assignKeyCard(String roomNr) {
		// TODO: Implement
		if (roomNr == null) {
			// ask for input
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

		// TODO: add key cards for room
		assignKeyCard(booking.getBookedRooms()[0].getRoomNumber());
	}

	private Guest addGuest() {
		System.out.print("Passport Number: ");
		String passPortNumber = s.next();
		
		Guest found = hotel.findGuestByPassport(passPortNumber);
		
		if (found != null) {
			return found;
		} else {
			System.out.print("Guest Name: ");
			String name = s.next();
			System.out.print("Phone Number: ");
			String phoneNumber = s.next();
			Guest toAdd = new Guest(name, phoneNumber, passPortNumber);
			hotel.addGuest(toAdd);
			return toAdd;
		}
	}

	private void bookRoom() {
		Date startDate = new Date();
		Date endDate = new Date();

		Set<RoomType> available = new HashSet<RoomType>();

		boolean notOk = true;
		while (notOk) {
			System.out.print("enter start date (yyyy-mm-dd):");
			String sDate = s.next();
			System.out.print("enter end date (yyyy-mm-dd):");
			String eDate = s.next();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			try {
				startDate = format.parse(sDate);
				endDate = format.parse(eDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Start must be at least 1 day before end date.
			notOk = startDate.getTime() >= endDate.getTime();

			try {
				available.addAll(hotel.getAvailableRoomTypes(startDate, endDate));
			} catch (HotelFullException e) {
				System.out.println("Hotel full for the following dates");
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
				for (Date d : e.getFullDates()) {
					System.out.println(ft.format(d));
				}
				notOk = true;
			}

		}

		// Set check in and out hours
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		startDate = cal.getTime();

		cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		endDate = cal.getTime();

		System.out.println("Select a room type, the available types are: ");
		for (RoomType type : available) {
			System.out.println(type.getRoomTypeName());
		}

		RoomType validRoomType = null;
		while (validRoomType == null) {
			System.out.println("Enter your RoomType: ");
			String rType = s.next();
			for (RoomType type : available) {
				if (type.getRoomTypeName().equals(rType)) {
					validRoomType = type;
					break;
				}
			}
			if (validRoomType == null) {
				System.out.println("RoomType not valid!");
			}
		}
		RoomType[] types = new RoomType[] { validRoomType };
		
		Guest guest = addGuest();
		
		Booking book = hotel.createBooking(this.user, guest, types, startDate, endDate);
		System.out.println("Booking process completed for booking with ID:" + book.getBookingId());
	}

	private void addRoom() {
		boolean nrNotOk = true;
		String roomNumber = null;
		while (nrNotOk) {
			System.out.print("Input the room number: ");
			roomNumber = s.next();

			if (hotel.getRoomByNumber(roomNumber) == null) {
				nrNotOk = false;
			} else {
				System.out.println("Room number already exists, remove the old room or choose another number.");
			}
		}

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
		hotel.setLoggedInUser(temp);
		return temp;
	}
}
