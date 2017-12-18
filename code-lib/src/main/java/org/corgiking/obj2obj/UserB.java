package org.corgiking.obj2obj;

public class UserB {

	private String realname;
	private String nickname;
	private int age;
	private String sex;

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	@Override
	public String toString() {
		return "UserB [realname=" + realname + ", nickname=" + nickname + ", age=" + age + ", sex=" + sex + "]";
	}

}
