package application;

import java.util.List;

public class Admin extends User{
	
	private String username;
	
	private String password;
	
	private List<User> users;
	
	public Admin(String username, String password, List<User> users){
		super(username, password);
		this.users = users;
	}
	
	public void addUser(User user){
		users.add(user);
	}
	
	public void removeUser(User user){
		users.remove(user);
	}

}
