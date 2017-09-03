package org.corgiking.xml.demo;

import java.util.HashMap;

public class BeanFactory {
	private HashMap<String, Object> beans;
	
	public BeanFactory(String filename) {
		beans = new HashMap<String, Object>();
		addBeansXml(filename);
	}
	
	public void addBeansXml(String filename){
		XMLProcesser processer = new XMLProcesser(filename);
		beans.putAll(processer.getAllBeans());
	}
	
	public Object getBean(String id){
		Object obj = beans.get(id);
		if (obj == null) {
			throw new RuntimeException("没有找到bean:id = "+id);
		}
		return obj;
	}
	
}
