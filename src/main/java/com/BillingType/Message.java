package com.BillingType;

public class Message {
	private String sentTime;	//呼叫的起始时间
	private String channel;	//通话时长
	private String type;		//通话类型（本地通话、省内、国内）
	
	public Message(String sentTime, String channel, String type) {
		super();
		this.sentTime = sentTime;
		this.channel = channel;
		this.type = type;
	}
	
	public String getSentTime() {
		return sentTime;
	}
	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
