package org.corgiking.xml;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unchecked")
public class XmlTest {

	public static void main(String[] args) throws Exception {
		String path ="src/main/resources/org/corgiking/xml/";
		File file = new File(path+"people.xml");

		// dom4j方式遍历子结点
		dom4j(file);

		// XPath方式遍历
		xpath(file);

	}

	/**
	 * Dom4j结合Xpath方式解析XML文件
	 * 
	 * @param document
	 * @throws DocumentException
	 */
	private static void xpath(File file) throws DocumentException {
		// 读取xml文件
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		// System.out.println("document:"+document);
		// 获取一个结点
		Node node = document.selectSingleNode("//name");
		System.out.println("name:"+node.getText().trim());
		// 获取多个结点
		List<Node> selectNodes = document.selectNodes("//phone");
		System.out.println("phones:");
		for (Node n : selectNodes) {
			System.out.println(n.getText());
		}
		// 获取指定属性的结点
		Node node2 = document.selectSingleNode("//people[@id='xiaoming']");
		System.out.println("node2:"+node2.selectSingleNode("//name").getText());
		
		//获取属性
		Element e = (Element)node2;
		System.out.println("id:"+e.attributeValue("id")+",status:"+e.attributeValue("status"));
		
		//获取当前节点子节点
		Node node3 = document.selectSingleNode("/*");//获取根节点
		List<Node> nodeList = node3.selectNodes("*");//获取所有子节点
		System.out.println(node3.getName()+"的子节点:");
		for (Node n : nodeList) {
			System.out.println(n.getName());
		}
	}

	/**
	 * Dom4j方式解析XML文件
	 * 
	 * @param root
	 * @throws DocumentException
	 */
	private static void dom4j(File file) throws DocumentException {
		// 读取xml文件
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		// System.out.println("document:"+document);
		// 获取根结点
		Element root = document.getRootElement();
		// System.out.println("root:"+root);
		List<Element> elements = root.elements();
		// System.out.println(elements);
		for (Element element : elements) {
			System.out.println(element.elementText("name"));
			System.out.println(element.elementText("age"));
			System.out.println(element.elementText("sex"));
			System.out.println(element.elementText("phone"));
			System.out.println("");
		}
	}

}
