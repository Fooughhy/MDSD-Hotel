package view;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import access.UserType;
import component.HotelSystemComponent;
import model.Room;
import model.RoomType;
import access.User;

public class ConsoleView {

	private List<User> users = new LinkedList<User>();

	private User currentUser = null;

	private Scanner scanner;
	private HotelSystemComponent comp;

	public ConsoleView() {
		User mainAdmin = new User("admin", "admin", UserType.Admin);
		User receptionist = new User("rec", "rec", UserType.Receptionist);
		User manager = new User("man", "man", UserType.HotelManager);
		User cleaner = new User("clean", "clean", UserType.Cleaner);

		users.add(mainAdmin);
		users.add(receptionist);
		users.add(manager);
		users.add(cleaner);

		comp = new HotelSystemComponent();

		scanner = new Scanner(System.in);

		while (true) { // login loop
			login();
			while (true) { // program loop
				System.out.print(currentUser.getUsername() + ":");
				String command = scanner.next();

				if (command.equals("logout")) {
					break;
				}
				if (getAccess().equals(UserType.Admin)) {
					if ("createRoomType".equals(command)) {
						createRoomType();
					} else if ("createRoom".equals(command)) {
						createRoom();
					} else if ("changeRoomPrice".equals(command)) {
						changeRoomPrice();
					} else if ("removeRoom".equals(command)) {
						removeRoom();
					} else if ("removeRoomType".equals(command)) {
						removeRoomType();
					} else if ("createAmenity".equals(command)) {
						// CODE;
					} else if ("removeAmenity".equals(command)) {
						// CODE;
					}
				}
				if (getAccess().equals(UserType.HotelManager)) {
					if ("createDiscount".equals(command)) {
						// CODE;
					}
				}
				if (getAccess().equals(UserType.Receptionist) || getAccess().equals(UserType.HotelManager)) {
					if ("bookAmenity".equals(command)) {
						// CODE;
					} else if ("cancelAmenity".equals(command)) {
						// CODE;
					} else if ("availableAmenity".equals(command)) {
						// CODE;
					} else if ("addRoom".equals(command)) {
						// CODE;
					} else if ("cancelRoom".equals(command)) {
						// CODE;
					} else if ("createRoom".equals(command)) {
						// CODE;
					} else if ("checkCost".equals(command) || "printReceipt".equals(command)) {
						// CODE;
						// comp.getDiscounts();
					} else if ("removeRoom".equals(command)) {
						// CODE;
					} else if ("checkIn".equals(command)) {
						// CODE;
					} else if ("checkOut".equals(command)) {
						// CODE;
						// billing is used inside checkout
						// comp.getBilling()
					} else if ("createGuest".equals(command)) {
						// CODE;
					} else if ("changeEmail".equals(command)) {
						// CODE;
					} else if ("changePhone".equals(command)) {
						// CODE;
					} else if ("addCharges".equals(command)) {
						// TODO: add in components
					} else if ("viewGuest".equals(command)) {
						// CODE;
					} else if ("lastCleaned".equals(command)) {

					} else if ("checkPayment".equals(command)) {

					} else if ("checkNumberOfGuests".equals(command)) {
						// for any one day.
					}
				}
				if (getAccess().equals(UserType.Cleaner)) {
					if ("cleanRoom".equals(command)) {
						cleanRoom();
					}
				}
			}
		}
	}
	
	private void removeRoom() {
		if (comp.getViewRoom().getRooms().length < 1) {
			System.out.println("There are no rooms, use <createRoom>.");
			return;
		}
		int roomNumber = 0;
		while (roomNumber < 1) {
			System.out.print("Input the room number: ");
			try {
				roomNumber = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format

			if (comp.getViewRoom().getRoomType(roomNumber) == null) {
				System.out.println("Room number does not exists, choose another number.");
			}
		}

		boolean result = comp.getAdminRoomManagement().removeRoom(roomNumber);
		
		System.out.println("Room number " + roomNumber + " was " + (result ? "" : "NOT") +  " removed.");
	}

	private void removeRoomType() {
		if (comp.getViewRoom().getTypeList().isEmpty()) {
			System.out.println("There are no room types, use <createRoomType>.");
			return;
		}
		String roomType = null;
		while (roomType == null) {
			System.out.print("Input the room type: ");
			roomType = scanner.next();

			if (!comp.getViewRoom().getTypeList().contains(roomType)) {
				System.out.println("Room type does not exists.");
			}
		}
		
		if (comp.getViewRoom().getRooms(roomType).length > 0) {
			System.out.println("There are rooms of this type, remove them first.");
			return;
		}
		
		if (comp.getAdminRoomManagement().removeRoomType(roomType)) {
			System.out.println("RoomType: <" + roomType + "> removed.");
		}
	}

	private void createRoomType() {
		String roomType = null;
		while (roomType == null) {
			System.out.print("Input the room type: ");
			roomType = scanner.next();

			if (comp.getViewRoom().getTypeList().contains(roomType)) {
				System.out.println("Room type already exists, choose another name.");
			}
		}
		
		int roomPrice = -1;
		while (roomPrice < 0) {
			System.out.print("Input the room number: ");
			try {
				roomPrice = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format
		}
		
		comp.getAdminRoomManagement().createRoomType(roomType, roomPrice);
	}
	
	private void changeRoomPrice() {
		if (comp.getViewRoom().getTypeList().isEmpty()) {
			System.out.println("There are no room types, use <createRoomType>.");
			return;
		}
		
		String roomType = null;
		while (roomType == null) {
			System.out.print("Input the room type: ");
			roomType = scanner.next();

			if (!comp.getViewRoom().getTypeList().contains(roomType)) {
				System.out.println("Room type does not exists, choose another type.");
			}
		}
		
		int roomPrice = -1;
		while (roomPrice < 0) {
			System.out.print("Input the room number: ");
			try {
				roomPrice = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format
		}
		
		comp.getAdminRoomManagement().changeRoomPrice(roomType, roomPrice);
	}
	
	private void createRoom() {
		if (comp.getViewRoom().getTypeList().isEmpty()) {
			System.out.println("There are no room types, use <createRoomType>.");
			return;
		}
		int roomNumber = 0;
		while (roomNumber < 1) {
			System.out.print("Input the room number: ");
			try {
				roomNumber = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format

			if (comp.getViewRoom().getRoomType(roomNumber) != null) {
				System.out.println("Room number already exists, choose another number.");
			}
		}

		System.out.print("Input the room type: ");
		String stype = scanner.next();

		if (!comp.getViewRoom().getTypeList().contains(stype)) {
			System.out.println("This type does not exist, first use <createRoomType>.");
			return;
		}
		
		comp.getAdminRoomManagement().createRoom(stype, roomNumber);
		
		System.out.println("Room number " + roomNumber + " is now created.");
	}

	private UserType getAccess() {
		return currentUser.getUserType();
	}

	private void login() {
		User temp = null;
		boolean rdyToGo = false;
		String username = "";
		while (!rdyToGo) {
			System.out.println("Please insert username: ");
			username = scanner.next();
			System.out.println("Please insert password: ");
			String pass = scanner.next();

			for (User user : users) {
				if (username.equals(user.getUsername())) {
					temp = user;
					break;
				}
			}
			if (temp == null) {
				System.out.println("cant find username");
			} else if (temp.getPassword().equals(pass)) {
				rdyToGo = true;
			}
		}
		currentUser = temp;
	}
	
	private void cleanRoom(){
		System.out.println("What is the number of the cleaned room?");
		Scanner scanner = new Scanner(System.in);
		int roomNr = Integer.parseInt(scanner.next());
		
		comp.getRoomManagement().cleanRoom(Calendar.getInstance().getTime(), roomNr);
		
		System.out.println("Room " + roomNr + " has now been set to cleaned!");
	}
}
