package test;

import java.util.ArrayList;
import java.util.Random;

import controller.Hotel;
import model.*;

public class ProjectStartup {

	public static void main(String[] args) {
		
		Hotel hotel = new Hotel();
		
		ArrayList<RoomType> list1 = new ArrayList<>();
		ArrayList<RoomType> list2 = new ArrayList<>();
		
		RoomType r = new RoomType("Single");
		list1.add(r);
		hotel.addRoomType(r);
		r = new RoomType("Double");
		list1.add(r);
		hotel.addRoomType(r);
		
		r = new RoomType("Balcony");
		list2.add(r);
		hotel.addRoomType(r);
		r = new RoomType("Fireplace");
		list2.add(r);
		hotel.addRoomType(r);
		
		Random random = new Random();
		
		for(int i = 0; i < 30; i++){
			ArrayList<RoomType> t = new ArrayList<>();
			t.add(list1.get(random.nextInt(2)));
			t.add(list2.get(random.nextInt(2)));
			
			Room room = new Room(i, t);
			hotel.addRoom(room, i);
		}
	}

}
