package com.BillingType;
import com.User.*;
public class DataTraffic {
	private User user;
	private String startTime;	// 呼叫的起始时间
	private String traffic;		// 流量数目
	private String type;		// 通话类型（本地通话、省内、国内）
	private String channel;		// 2G、3G、4G
	private String duration;	// 持续时间有多长
	
	public DataTraffic(User user, String startTime, String traffic, String type, String channel,
			String duration) {
		super();
		this.user = user;
		this.startTime = startTime;
		this.traffic = traffic;
		this.type = type;
		this.channel = channel;
		this.duration = duration;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String isDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
}
