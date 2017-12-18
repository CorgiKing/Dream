package org.corgiking.obj2obj.orika;

import org.corgiking.obj2obj.UserA;
import org.corgiking.obj2obj.UserB;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaTest {

	public static void main(String[] args) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserA.class, UserB.class)
                .field("name", "realname")
                .field("account", "nickname")
                .byDefault()
                .register();

        UserA userA = new UserA();
        userA.setAccount("111");
        userA.setName("Corgi King");
        userA.setSex("男");
        userA.setAge(20);
        
        MapperFacade mapper = mapperFactory.getMapperFacade();

        UserB userB = mapper.map(userA, UserB.class);
        System.out.println(userB.toString());

	}

}
