package component.model;

import java.util.LinkedList;
import java.util.List;

import component.model.Hotel;

public class KeyCard {
	private Room room;
	
	public KeyCard(){
		;
	}
	
	public KeyCard(Room room){
		setRoom(room);
	}
	
	public Room getRoom(){
		return room;
	}
	
	public boolean setRoom(Room room){
		this.room = room;
		return true;
	}
	
	/**
	 * Returns a list for all cards that are registered to a room number, given the list of cards
	 * and a room number.
	 * @param allCards A List of all Key Cards.
	 * @param roomNr The room Number to check for Cards.
	 * @return The Key Cards that were registered to the Room.
	 */
	public static List<KeyCard> cardForRoom(List<KeyCard> allCards, String roomNr, Hotel hotel) {
		Room room = hotel.getRoomByNumber(roomNr);
		
		List<KeyCard> cardsForRoom = new LinkedList<KeyCard>();
		
		for (KeyCard keyCard : allCards) {
			if (keyCard.room.equals(room)) {
				cardsForRoom.add(keyCard);
			}
		}
		
		return cardsForRoom;
	}
}
