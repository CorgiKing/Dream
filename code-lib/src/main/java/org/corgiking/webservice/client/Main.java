package org.corgiking.webservice.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.corgiking.webservice.client.RemoteMethod;


public class Main {

  public static void main(String[] args) throws Exception {
    
    URL url = new URL("http://localhost:5019/webservice?wsdl");
    QName qname = new QName("http://server.webservice.corgiking.org/","WebServiceImplService");
    
    Service service = Service.create(url,qname);
    
    RemoteMethod webService = service.getPort(RemoteMethod.class);
    
    String data = webService.sayHello("Corgi King ");
    People p = new People();
    p.setName("Corgi King");
    p.setSex("男");
    p.setAge(20);
    String ret = webService.putUser(p);
    System.out.println(ret);
    System.out.println(data);
    
  }

}
