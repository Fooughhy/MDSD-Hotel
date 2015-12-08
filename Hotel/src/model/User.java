package model;

public class User {
	private String Username;
	private String Password;
	private boolean loggedIn;
	
	private UserType userType;
	
	public User(String username, String password, UserType userType){
		Username = username;
		Password = password;
		this.userType = userType;
	}
}
