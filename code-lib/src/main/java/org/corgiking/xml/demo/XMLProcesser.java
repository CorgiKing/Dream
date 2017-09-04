package org.corgiking.xml.demo;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

@SuppressWarnings({"unchecked","rawtypes"})
public class XMLProcesser {
	
	private File xmlfile;
	
	private HashMap<String, Object> beans = new HashMap<String, Object>();
	
	public XMLProcesser(File file){
		xmlfile = file;
	}
	
	public XMLProcesser(String pathname){
		xmlfile = new File(pathname);
	}
	
	/**
	 * 获取xml文件中全部bean
	 * @return
	 */
	public HashMap<String, Object> getAllBeans(){
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(xmlfile);
			return getBeans(doc);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}

	/**
	 * 获取文件中全部bean
	 * @param doc
	 * @return
	 */
	private HashMap<String, Object> getBeans(Document doc) {
		List<Node> beanNodes = doc.selectNodes("//bean");
		for(Node node:beanNodes){
			CreateBean(node);
		}
		return beans;
	}

	/**
	 * 根据bean标签中信息创建对象
	 * @param node
	 */
	private void CreateBean(Node node) {
		Element element = (Element)node;
		String id = element.attributeValue("id");
		String clazzName = element.attributeValue("class");
//		List<Element> fields = node.selectNodes("*");
		
		try {
			//创建对象
			Class clazz = Class.forName(clazzName);
			Object obj = clazz.newInstance();
//			for(Element e:fields){
//				//给obj设置属性值
//				Field field = clazz.getDeclaredField(e.attributeValue("id"));
//				Object fobj = Class.forName(e.attributeValue("type")).newInstance();
//				
//				field.set(obj, fobj);
//			}
			if (id == null) {
				id = clazzName.substring(clazzName.lastIndexOf("."));
			}
			beans.put(id, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
