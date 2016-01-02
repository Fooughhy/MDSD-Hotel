package view;

import model.*;
import controller.*;
import java.util.Scanner;
import java.util.Date;
import java.text.*;
import java.util.ArrayList;
import java.io.*;

public class ConsolView{

        public static void main(String[] args){
                new ConsolView();
        }

        private Hotel hotel;
        private User user;
        Scanner s;
        
	public ConsolView(){
	        s=new Scanner(System.in);
                System.out.println("Creating a Hotel");
                this.hotel=new Hotel();
                System.out.println("Hotel created");
                this.user=loggin();
                System.out.println("User logged in");
                String command;
                while(true){
                        System.out.print(user.getUserName()+":");
                        command = s.next();

                        if(command.equals("addRoom")) addRoom();
                        else if(command.equals("bookRoom")) bookRoom();
                        else if(command.equals("bookRoomTest")) bookRoomTest();
                        else if(command.equals("addGuest")) addGuest();
                }
	}
	private Guest addGuest(){
                //TODO add security clearnece
                System.out.print("Guest name: ");
                String name= s.next();
                System.out.print("Phone number: ");
                String phoneNumber = s.next();
                System.out.print("passport number: ");
                String passPortNumber = s.next();
                System.out.println("creating guest");
                return new Guest(name,phoneNumber,passPortNumber);
	}

        private void bookRoomTest(){
                System.out.println("this is just a test for the 15/12 meeting");
                System.out.println("Room 1 will be booked. If Room 1 dose not exist the program will chrash");
                
                System.out.print("enter start date: (ex 11-January-15)");
                String sDate = s.next();
                System.out.print("enter end date: ");
                String eDate = s.next();
                System.out.println("createing dates form inputed strings");
                DateFormat format = new SimpleDateFormat("dd-MM-yy");

                Date startDate = new Date();
                Date endDate= new Date();

                try{
                        startDate = format.parse(sDate);
                        endDate = format.parse(eDate);
                }catch(ParseException e){e.printStackTrace();}
                System.out.println("here a room type is selected. (not now obviusly)");

                Guest guest = addGuest();
                System.out.println("guest created BUT NOT SAVED!!");

                
                hotel.createBooking(this.user,guest,null,startDate,endDate);
                System.out.println("booking is created.");
                
        }

	private void bookRoom(){
                /*
	        //everyone permitted? still the admin thing is rly strange!!!
	        System.out.println("enter start-date: "+
	        "(this is a bit complicated so for now we asume u enter today and only today)");
                //TODO add startDate and endDate
	        System.out.println("Searching for free rooms"+
	        "some algortihm here. for now ALL rooms are available");

	        for(int i = 0; i<hotel.getAvailableRooms(startDate,endDate).size(),i++){
                        println("room:" hotel.);
	        }*/
	}

        private void addRoom(){
                if(user.getUserType() != UserType.Admin){
                        System.out.println("nope!");
                        return;
                }
                System.out.print("add number on room: ");
                String roomNumber = s.next();
                String temp="Nothing right now";
                ArrayList<RoomType> roomTraitList = new ArrayList<>();
                while(!temp.equals("0")){
                        System.out.print("add a traits to the room. end with a zero");
                        temp = s.next();
                        roomTraitList.add(new RoomType(temp));
                }
                hotel.addRoom(new Room(roomNumber,roomTraitList));
                System.out.println("Room number " + roomNumber + " is now created.");
        }

	private User loggin(){
                user temp=null;
                boolean rdyToGo=false
	        while(!readtToGo){
                        System.out.println("Please insert username: ");
                        String username = s.next();
                        System.out.println("Please insert password: ");
                        String pass = s.next();
                        temp = getUserByUsername(username);
                        if(temp==null) System.out.println("cant find username");
                        else if(temp.getPassword.equals(pass)) rdyToGo=true;
                }
                System.out.println("logged in yeeey")
                


                
                
                return new User(username,"admin",UserType.Admin);
	}
}
