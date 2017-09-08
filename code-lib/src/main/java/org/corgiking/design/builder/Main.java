package org.corgiking.design.builder;

public class Main {

	/**
	 * 建造者模式
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UserInfo userInfo = new UserInfoBuilder()
				.name("Corgi King")
				.age(25).sex("男")
				.status("程序猿")
				.description("帅呆了!")
				.build();
		
		System.out.println(userInfo);
	}
	
}
