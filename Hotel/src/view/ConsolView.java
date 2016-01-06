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
		while (true) { // to make sure this is where you end up if you logout.
			this.user = loggin();
			System.out.println("User logged in");
			UserType userType = user.getUserType();
			String command;
			while (true) {
				System.out.print(user.getUsername() + ":");
				command = s.next();

				if (command.equals("logout")) {
					break;
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "bookRoom")) {
					bookRoom();
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "addGuest")) {
					addGuest();
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "checkIn")) {
					checkIn();
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "checkOut")) {
					checkOut();
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "bookAmenities")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "printReceipt")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "addChargesToBooking")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "getAvailableRoomTypes")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "addingStayInformation")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "viewGuestInformation")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "lastCleanedDate")) {
					lastCleaned();
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "cancelBooking")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "assignExtraKeyCard")) {
					assignKeyCard(null);
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "checkPaymentStatus")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Receptionist, UserType.HotelManager}, command, "checkNumberOfGuests")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Admin, UserType.HotelManager}, command, "viewKeyCard")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Admin}, command, "addRoom")) {
					addRoom();
				} else if (checkCommand(userType, new UserType[]{UserType.Admin}, command, "createMasterKeyCard")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Admin}, command, "editRoom")) {
					// TODO: Add functionality
				} else if (checkCommand(userType, new UserType[]{UserType.Admin}, command, "createUser")) {
					createUser();
				} else if (checkCommand(userType, new UserType[]{UserType.Cleaner}, command, "markRoomAsCleaned")) {
					markRoomAsClean();
				}
			}
		}
	}
	
	private boolean checkCommand(UserType loggedIn, UserType[] access, String commandIn, String commandReq) {
		boolean accessOk = false;
		if (commandIn.equals(commandReq)) {
			for (int i = 0; i < access.length; i++) {
				accessOk = accessOk || loggedIn == access[i];
			}
			if (true || accessOk) { // TODO: now all users have access to everything
				return true;
			} else {
				System.out.println("Account type <" + loggedIn + "> does not have permissions for this command.");
				return false;
			}
		}
		return false;
	}

	private void lastCleaned() {
		Room room = null;
		while (room == null) {
			System.out.print("Provide a Room Number: ");
			String nr = s.next();
			room = hotel.getRoomByNumber(nr);
			if (room == null) {
				System.out.print("Room does not exist.");
			}
		}
		
		Date lastCleaned = room.getLastcleaned();
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");
		System.out.println("Room " + room.getRoomNumber() + " was last cleaned at " + ft.format(lastCleaned));
	}
	
	private void markRoomAsClean() {
		Room room = null;
		while (room == null) {
			System.out.print("Provide a Room Number: ");
			String nr = s.next();
			room = hotel.getRoomByNumber(nr);
			if (room == null) {
				System.out.print("Room does not exist.");
			}
		}
		
		Date now = Calendar.getInstance().getTime();
		boolean res = room.setClean(now);
		
		if (res) {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");
			System.out.println("Room " + room.getRoomNumber() + " set to cleaned at " + ft.format(now));
		} else {
			System.out.println("Room was already clean or not checked out.");
		}	
	}

	private void createUser(){
		System.out.println("insert username: ");
		String un = s.next();
		System.out.println("insert password: ");
		String pass = s.next();
		boolean correctNumber = false;
		int number = 0;
		while(number==0){
			System.out.println("please choose clearence level\n"+
			"System Admin(1) HotelManager(2) Recpetionsist(3) Cleaner(4)");
			String nr = s.next();
			try{
				number=Integer.parseInt(nr);
			}catch(NumberFormatException e){
				System.out.println("wrong number inserted.");
			}
			if(number>4 || number<1){
				number=0;
				System.out.println("wrong number.");
			}
		}
		UserType ut=UserType.Admin; // added just so it would be init
		if(number==1)
			ut = UserType.Admin;
		else if(number == 2)
			ut = UserType.HotelManager;
		else if(number == 3)
			ut = UserType.Receptionist;
		else if(number == 4)
			ut = UserType.Cleaner;
		
		hotel.addUser(new User(un,pass,ut));
		System.out.println("user created and saved.");
	}
	private void assignKeyCard(String roomNr) {
		// TODO: Implement
		if (roomNr == null) {
			while(true){
				System.out.println("What room would you like to assign a keycard to?");
				String input = s.next();
				
				try{
					int nr = Integer.parseInt(input);
					if(nr >= 1){
						Room r = hotel.getRoomByNumber(input);
						if(r != null){
							if(hotel.addKeyCardToRoom(r) != null){
								System.out.println("A new keycard was assigned to room " + nr);
								return;
							}
							else{
								System.out.println("Unable to add a keycard to that room, please try again!");
							}
						}
						else{
							System.out.println("Unable to find a room with that roomnumber, please try again!");
						}
					}
					else{
						System.out.println("Invalid roomnumber, please input a roomnumber greater than 1!");
					}
				}
				catch(NumberFormatException e){
					System.out.println("Invalid input-type! Please use only numbers!");
				}
			}
		}
	}

	private void checkOut() {
		Booking booking = null;
		while (booking == null) {
			System.out.print("Provide Booking Id: ");
			String id = s.next();
			booking = hotel.getBookingById(Integer.parseInt(id));
			if (booking == null) {
				System.out.println("No booking exists with this ID");
			}
		}
		
		switch (booking.getStatus()) {
		case BOOKED:
			System.out.println("Booking is not yet checked in.");
			return;
		case OUT:
			System.out.println("Booking is already checked out.");
			return;
		case IN:
			break;
		}
		
		System.out.println("Checked out the following rooms: ");
		
		Room[] rooms = booking.getBookedRooms();
		
		int totalCost = 0;
		
		for (int i = 0; i < rooms.length; i++) {
			// remove keys
			hotel.removeAllKeyCardsFromRoom(rooms[i].getRoomNumber());
			// set not cleaned
			rooms[i].setDirty(booking.getEndDate());
			
			totalCost += rooms[i].getCostOfRoom();
			
			System.out.println("Room " + rooms[i].getRoomNumber() + " for cost " + rooms[i].getCostOfRoom() + " KR.");
		}
		
		booking.updateStatus(Booking.BookingStatus.OUT);
		
		// handle payment
		//TODO: Discounts!
		
		System.out.println("For total cost: " + totalCost + " KR.");
	}
	
	private void checkIn() {
		Booking booking = null;
		while (booking == null) {
			System.out.print("Provide Booking Id: ");
			String id = s.next();
			booking = hotel.getBookingById(Integer.parseInt(id));
			if (booking == null) {
				System.out.println("No booking exists with this ID");
			}
		}

		switch (booking.getStatus()) {
		case BOOKED:
			break;
		case OUT:
			System.out.println("Booking is already checked out.");
			return;
		case IN:
			System.out.println("Booking is already checked in.");
			return;
		}
		
		hotel.specifyRoomForBooking(booking);

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");

		System.out.println("The following rooms were assigned for booking ID: " + booking.getBookingId() + " between "
				+ ft.format(booking.getStartDate()) + " and " + ft.format(booking.getEndDate()) + ".");
		for (int i = 0; i < booking.getBookedRooms().length; i++) {
			System.out.println("Room number: " + booking.getBookedRooms()[i].getRoomNumber());
		}

		assignKeyCard(booking.getBookedRooms()[0].getRoomNumber());
		int stayCost =0; 
		for(int i = 0;i<booking.getBookedRooms().length;i++){
			stayCost += (booking.getBookedNights()+1) * booking.getBookedRooms()[0].getCostOfRoom();
		}
		booking.addCost(stayCost);
		booking.updateStatus(Booking.BookingStatus.IN);
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
		System.out.println("please insert the cost of the room per night (Kr): ");
		String cost = s.next();
		int c=1000;
		try{
			c=Integer.parseInt(cost);
		}catch(Exception e){
			System.out.println("something went wrong setting cost to 1000kr");
		}
		hotel.addRoom(new Room(roomNumber, type,c));
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
