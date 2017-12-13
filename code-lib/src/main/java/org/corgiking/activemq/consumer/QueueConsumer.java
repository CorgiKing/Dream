package org.corgiking.activemq.consumer;

import java.util.ArrayList;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.corgiking.activemq.common.Message;

public class QueueConsumer {

	public static void main(String[] args) throws Exception {

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
		
		MessageConsumer consumer = session.createConsumer(destination);
		
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(javax.jms.Message message) {
				try {
					Message msg = (Message) ((ObjectMessage)message).getObject();
					System.out.println(msg);
					message.acknowledge();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

}
