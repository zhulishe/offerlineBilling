package com.generateData;

import java.util.ArrayList;
import java.util.List;
import com.utils.*;

public class RuleName {
	private static List<String> ruleName;
	
	static{
		ruleName = new ArrayList<String>();
		ruleName = getRuleNameList();
	}
	
	/**
	 * 获取规则文件中规则的名字，用来初始化ruleName
	 * @return
	 */
	private static List<String> getRuleNameList() {
		String path = PropertiesUtils.getPropertyValue("ruleNamePath");
		String file = PropertiesUtils.PROJECT_PATH +  path + "/dataTraffic.txt";
		List<String> ruleNameList = ReadFile.readPreLine(file);
		return ruleNameList;
	}

	/**
	 * 获取规则文件中所有规则的名字的一个集合
	 * @return 
	 */
	public static List<String> getRuleName() {
		return ruleName;
	}
}