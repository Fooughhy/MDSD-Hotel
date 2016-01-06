package component.interfaces;

import java.util.Set;

public interface ViewRoom {

	/**
	 * Returns the room numbers of all rooms.
	 * @return An array of all room numbers.
	 */
	public int[] getRooms();
	
	/**
	 * Returns the room numbers of all rooms of one type.
	 * @param roomType The type of room.
	 * @return An array of all room numbers of type roomType.
	 */
	public int[] getRooms(String roomType);
	
	/**
	 * Return the name of the room type for a room.
	 * @param roomNr The room number.
	 * @return The name of the type, null if the room does not exist.
	 */
	public String getRoomType(int roomNr);

	/**
	 * Return the set of types specified for the hotel.
	 * @return A set of names.
	 */
	public Set<String> getTypeList();
	
}
