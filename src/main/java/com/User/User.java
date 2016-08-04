package com.User;

import java.util.HashMap;

/*
 * 主要是统计用户在各个规则内的使用情况 
 * 
 */

public class User {
	private String phoneNumber;		// 用户的电话号码
	private HashMap<String, Long> ruleHashMap = new HashMap<String, Long>();	// 用户使用情况
	private UserDataTraffic userDataTraffic;
	
	public User(String pNumber, UserDataTraffic userDataTraffic){
		this.userDataTraffic = userDataTraffic;
		this.phoneNumber = pNumber;
	}
	
//	public User(String pNumber, List<String> ruleList) {
//		phoneNumber = pNumber;
//		for (String string : ruleList) {
//			ruleHashMap.put(string, 0L);
//		}
//	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public UserDataTraffic getUserDataTraffic() {
		return userDataTraffic;
	}

	public void setUserDataTraffic(UserDataTraffic userDataTraffic) {
		this.userDataTraffic = userDataTraffic;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public HashMap<String, Long> getRuleHashMap() {
		return ruleHashMap;
	}

	public void setRuleHashMap(HashMap<String, Long> ruleHashMap) {
		this.ruleHashMap = ruleHashMap;
	}


//
//	/**
//	 * 将匹配到的指定规则下的流量项相加
//	 * @param name
//	 * @param quantity
//	 */
//	public void addTraffic(String name, String quantity) {
//		long value = ruleHashMap.get(name);
//		value += FormatConvert.getKB(quantity);
//		ruleHashMap.put(name, value);
//	}
//	
//
//	
//	/**
//	 * 利用drools匹配完规则之后，更新用户使用数据（包括流量、语音、短信）
//	 * @param key	相关计费规则的名字
//	 * @param value	该计费规则对应的计费数量
//	 */
//	public void updateMessage(String key, Long value) {
//		long v = ruleHashMap.get(key);
//		v += value;
//		ruleHashMap.put(key, v);
//	}
	
	
//	public static void main(String[] args) {
//		System.out.println(Usermessage.getKB("1G1M570KB"));
//		
//	}
	
}
