package component.interfaces;

public interface GuestInterface {

	public boolean isGuest(String passportNr);
	
	public boolean createGuest(String passportNr, String fName, String lName, String email, String phone);
	
	public boolean changeEmail(String passportNr, String email);
	
	public boolean changePhone(String passportNr, String phone);
	
	public String getName(String passportNr);
	
	public String getEmail(String passportNr);
	
	public String getPhone(String passportNr);
	
}
