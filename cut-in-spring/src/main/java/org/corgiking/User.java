package org.corgiking;

public class User {
	
	private String realname;
	private String email;
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [realname=" + realname + ", email=" + email + "]";
	}
}
