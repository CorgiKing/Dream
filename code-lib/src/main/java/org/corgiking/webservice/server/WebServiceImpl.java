package org.corgiking.webservice.server;

import javax.jws.WebService;

@WebService
public class WebServiceImpl {

  public String sayHello(String name) {
    return "hello " + name;
  }

  public String sayBye(String name) {
    return "bye " + name;
  }

  public String putUser(User u) {
    System.out.println(u);
    return u == null ? "No" : "Yes";
  }

}
