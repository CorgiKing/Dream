package org.corgiking.webservice.server;

import javax.xml.ws.Endpoint;

public class Main {

  public static void main(String[] args) {
    String address = "http://localhost:5019/webservice";
    Endpoint.publish(address, new WebServiceImpl());
    System.out.println("web service publish success");
  }

}
