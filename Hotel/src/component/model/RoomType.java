package component.model;

public class RoomType {
	private String roomTypeName;
	
	public RoomType(String name){
		setRoomTypeName(name);
	}
	
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	
	public String getRoomTypeName() {
		return roomTypeName;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof RoomType){
			RoomType other = (RoomType) o;
			
			result = roomTypeName.equals(other.roomTypeName);
		}
		
		return result;
	}
}
