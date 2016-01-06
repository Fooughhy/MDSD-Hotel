package component.model;

public class Guest {
	private String guestName, guestPhoneNumber, guestPassPortNumber;
	
	public Guest(String name, String phoneNumber, String passportNumber){
		guestName = name;
		guestPhoneNumber = phoneNumber;
		guestPassPortNumber = passportNumber;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestPhoneNumber() {
		return guestPhoneNumber;
	}

	public void setGuestPhoneNumber(String guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
	}

	public String getGuestPassPortNumber() {
		return guestPassPortNumber;
	}

	public void setGuestPassPortNumber(String guestPassPortNumber) {
		this.guestPassPortNumber = guestPassPortNumber;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Guest){
			Guest other = (Guest) o;
			
			result = guestName.equals(other.guestName) && guestPassPortNumber.equals(other.guestPassPortNumber) && guestPhoneNumber.equals(other.guestPhoneNumber);
		}
		
		return result;
	}
}