package org.corgiking;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CKNamespaceHandler extends NamespaceHandlerSupport{
	
	static{
		System.out.println("加载NamespaceHandlerSupport类！");
	}
	
	public void init() {
		
		System.out.println("Corgi King 切入spring成功！ ");
		registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
    }

}
