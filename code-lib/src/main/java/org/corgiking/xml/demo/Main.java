package org.corgiking.xml.demo;

public class Main {

	public static void main(String[] args) {
		String path ="src/main/resources/org/corgiking/xml/";
		BeanFactory factory = new BeanFactory(path+"beans.xml");
		
		Object user = factory.getBean("user");
		System.out.println(user);
		
	}

}
