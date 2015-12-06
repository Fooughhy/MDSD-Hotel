package controller;

import java.util.*;

import model.Room;
import view.View;

public class Hotel {
	
	private View view;
	private Map<Integer,Room> roomMap = new TreeMap<Integer,Room>();
	
	public Hotel(){
		view = new View();
	}
	
	public boolean addRoom(int number){
		roomMap.put(number, new Room(number));
		
		return true;
	}

}
