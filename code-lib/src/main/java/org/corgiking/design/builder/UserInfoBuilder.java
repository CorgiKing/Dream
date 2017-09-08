package org.corgiking.design.builder;

public class UserInfoBuilder {
	
	private String name;
	private int age;
	private String sex;
	private String status;
	private String description;
	
	public UserInfo build(){
		return new UserInfo(name, age, sex, status, description);
	}
	
	public UserInfoBuilder name(String name){
		this.name = name;
		return this;
	}
	
	public UserInfoBuilder age(int age){
		this.age = age;
		return this;
	}
	
	public UserInfoBuilder sex(String sex){
		this.sex = sex;
		return this;
	}
	
	public UserInfoBuilder status(String status){
		this.status = status;
		return this;
	}
	
	public UserInfoBuilder description(String description){
		this.description = description;
		return this;
	}
}
