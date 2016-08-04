package com.BillingType;

public class Voice {
	private boolean isCallee;	// 是否是主叫
	private String callTime;	//呼叫的起始时间
	private String duration;	//通话时长
	private String type;		//通话类型（本地通话、省内、国内）
	
	public boolean isCallee() {
		return isCallee;
	}
	public void setCallee(boolean isCallee) {
		this.isCallee = isCallee;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
