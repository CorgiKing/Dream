package org.corgiking.design.builder;

public class UserInfo {
	
	private String name;
	private int age;
	private String sex;
	private String status;
	private String description;
	
	public UserInfo(String name, int age, String sex, String status, String description) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.status = status;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", age=" + age + ", sex=" + sex + ", status=" + status + ", description="
				+ description + "]";
	}
	
	
}
