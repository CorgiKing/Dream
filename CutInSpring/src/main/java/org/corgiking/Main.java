package org.corgiking;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ck-context.xml");
		
		User u = (User) context.getBean("cktest");
		
		System.out.println(u);
	}

}
