package jxau.soft.pojo;
public class User {
	private final static String admin="teacher";
	private final static String student="student";
   String username;
   String password;
   String role;
  public User() {
	super();
}
public User(String username, String password, String role) {
	super();
	this.username = username;
	this.password = password;
	this.role = role;
}
public User(String name, String password) {
	// TODO Auto-generated constructor stub
	this.username = name;
	this.password = password;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

}
