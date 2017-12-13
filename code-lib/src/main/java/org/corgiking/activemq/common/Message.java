package org.corgiking.activemq.common;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4397486891937593807L;

	private String sender;
	
	private String destination;
	
	private String msgBody;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "Message [sender=" + sender + ", destination=" + destination + ", msgBody=" + msgBody + "]";
	}
	
}
