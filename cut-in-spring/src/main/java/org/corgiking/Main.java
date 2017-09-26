package org.corgiking;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * xsd文件是XML结构定义文件( XML Schemas Definition )
 * 
 * spring会加载META-INF目录下的spring.handlers,spring.schemas文件
 *     spring.schemas文件定义了XML中的标签
 *     spring.handlers文件制定了该命名空间的解析类
 *
 */
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ck-context.xml");
		
		User u = (User) context.getBean("cktest");
		
		System.out.println(u);
	}

}

