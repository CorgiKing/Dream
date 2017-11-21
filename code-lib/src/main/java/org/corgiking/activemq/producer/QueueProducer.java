package org.corgiking.activemq.producer;

import java.util.ArrayList;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.corgiking.activemq.common.Message;

public class QueueProducer {

	public static void main(String[] args) throws JMSException {
		
		ActiveMQConnectionFactory confactory = new ActiveMQConnectionFactory("tcp://192.168.1.130:61616");
		//设置能够发送的object所在的包（ActiveMQ强制）
		ArrayList<String> whitelist = new ArrayList<>();
		whitelist.add("org.corgiking.activemq.common");
		confactory.setTrustedPackages(whitelist);
		Connection connection = confactory.createConnection();
		connection.start();
		
		//第一个参数为是否事务型消息（为true时，第二个参数无效）
		//第二个参数为消息类型
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		
		Destination destination = session.createQueue("test_amq");
		
		MessageProducer producer = session.createProducer(destination);
		//非持久化类型
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		Message msg = new Message();
		msg.setSender("yy");
		msg.setDestination("corgi king");
		msg.setMsgBody("Hello Corgi King!");
		
		producer.send(session.createObjectMessage(msg));
		System.out.println("发送完成！");
		
		producer.close();
		
	}

}
