package in.jdsoft.studentmanagement.model;

public class Login {
	
	public static String username=null;
	public String password=null;
	public static int userStatus=0;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		Login.username = username;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		Login.userStatus = userStatus;
	}
}
