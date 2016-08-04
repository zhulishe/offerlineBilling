package com.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	private static String filePath = "/conf/BILLING.properties";
	public static String PROJECT_PATH = System.getProperty("user.dir");
	
	/**
	 * 从properties文件中读取key对应的value
	 * @param filePath
	 * @param key
	 * @return key对应的返回值
	 */
	public static String getPropertyValue(String filePath, String key){
		Properties property = new Properties();
		try {
			property.load(new FileInputStream(System.getProperty("user.dir")+filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = property.getProperty(key);
		return value;
	}
	
	public static String getPropertyValue(String key){
		Properties property = new Properties();
		try {
			property.load(new FileInputStream(System.getProperty("user.dir")+filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = property.getProperty(key);
		return value;
	}
	
	
	/**
	 * 从配置文件中获得配置文件的filepath
	 * @return
	 */
	public static String getLocationFilePath(){
		return PROJECT_PATH + getPropertyValue(filePath, "locationProfilePath");
	}
	
	/*
	 * 从配置文件获取话单文件的路径
	 * @return 
	 */
	public static String getBillingRecordPath(){
		return PROJECT_PATH + getPropertyValue(filePath, "billingRecordPath");
	}
	
	/**
	 * 获取数据流量话单所在位置
	 * @return
	 */
	public static String getDatatrafficFilePath(){
		return PROJECT_PATH + getPropertyValue(filePath, "dataTrafficPath");
	}
	
	/**
	 * 获取语音话单所在位置
	 * @return
	 */
	public static String getMessageFilePath(){
		return PROJECT_PATH + getPropertyValue(filePath, "voicePath");
	}
	
	/**
	 * 获取短信话单所在位置
	 * @return
	 */
	public static String getVoiceFilePath(){
		return PROJECT_PATH + getPropertyValue(filePath, "messagePath");
	}
}