package component.interfaces;

import java.util.Date;

public interface RoomManagement {

	/**
	 * Register a key card to a room.
	 * @param cardId The Id of the key card.
	 * @param roomNr The number of the room.
	 * @return True if successfully registered.
	 */
	public boolean registerKeyCard(int cardId, String roomNr);
	
	/**
	 * Unregister a key card from its registered room.
	 * @param cardId The Id of the key card.
	 * @return
	 */
	public boolean unregisterKeyCard(int cardId);
	
	/**
	 * Set the flag that the room has been cleaned.
	 * @param when Date when the room was cleaned.
	 * @param roomNr What room is to be set to clean.
	 * @return True if successful.
	 */
	public boolean cleanRoom(Date when, String roomNr);
	
	/**
	 * Set a flag that the room requires cleaning.
	 * @param when Date when the flag was set (or when the room was checked out).
	 * @param roomNr What room is to be set to unclean.
	 * @return True if successful.
	 */
	public boolean setRoomUnclean(Date when, String roomNr);
	
	/**
	 * Check if the room is clean and checked out.
	 * @param roomNr What room is to be checked.
	 * @return True if the room is available for check in.
	 */
	public boolean checkRoomStatus(String roomNr);
	
}