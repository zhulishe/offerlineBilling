package com.process;

import java.util.ArrayList;
import java.util.List;

import com.BillingType.DataTraffic;
import com.User.User;
import com.User.UserDataTraffic;
import com.generateData.RuleName;
import com.utils.ReadFile;
import com.utils.RegExp;

public class GetRecord {

	/**
	 * 根据文件名得到包含流量的每一条记录List，该list的每一个元素都是一个DataTraffic实例
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<DataTraffic> getDataTrafficContent(String fileName, UserDataTraffic userDataTraffic) {
//		System.out.println(fileName);
		String phoneNum = RegExp.getFirstBracketMatch(fileName, ".*/(.*?)_.*");
		
		List<String> ruleName = RuleName.getRuleName();
		User usermessage = new User(phoneNum, userDataTraffic); // 创建该用户

		List<String> content = ReadFile.readPreLine(fileName);
		List<DataTraffic> result = new ArrayList<DataTraffic>();

		for (String records : content) {
			DataTraffic dataTraffic = generateDataTrafficObj(usermessage, records);
			result.add(dataTraffic);
		}

		return result;
	}

	/**
	 * 根据每一条记录，产生一个DataTraffic类的实例
	 * @param usermessage	用户信息实例
	 * @param record		从文件中读取到的类的实例
	 * @return				相应的DataTraffic类的实例
	 */
	private static DataTraffic generateDataTrafficObj(User usermessage, String record) {
		String[] data = record.split("    ");
		DataTraffic dataTraffic = new DataTraffic(usermessage, data[0], data[4], data[3], data[1], data[2]);
		return dataTraffic;
	}

//	public static void main(String[] args) {
//		String fileName = "/home/aby/Desktop/billingRecord/0_dataTraffic.txt";
//		GetRecord getRecord = new GetRecord();
//		List<DataTraffic> dataTrafficlist = getRecord.getDataTrafficContent(fileName);
//	}
}
