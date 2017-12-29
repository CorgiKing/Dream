package org.corgiking.webservice.client;

import javax.jws.WebService;

@WebService(targetNamespace = "http://server.webservice.corgiking.org/", name = "WebServiceImpl")
public interface RemoteMethod {
  public String sayHello(String name);

  public String sayBye(String name);

  public String putUser(People u);
}
