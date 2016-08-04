package com.process;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import com.BillingType.DataTraffic;
import com.User.User;
import com.User.UserDataTraffic;
import com.database.Database;
import com.utils.FormatConvert;
import com.utils.PropertiesUtils;
import com.utils.ReadFile;

public class Main {
	public static boolean isInputEnd = false;
	public static boolean isOutputEnd = false;
	public static boolean isStorageEnd = false;

	public static void main(String[] args) {
		LinkedBlockingDeque<List<DataTraffic>> inputDataTrafficQueue = new LinkedBlockingDeque<List<DataTraffic>>(1000);

		// 输出队列，将输入队列中的元素进行drools规则匹配，该用户的统计结果
		LinkedBlockingDeque<User> outputQueue = new LinkedBlockingDeque<User>(); // 需要处理一下这个队列的长度

		// 创建数据库
		String databaseName = "offlineBilling";
		String str = "create database If Not Exists " + databaseName
				+ " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;";
		Database database = new Database();
		database.createDatabase(databaseName);

		// 建表
		String tableName = "recommendation";
		String tableStr = "Create Table If Not Exists " + databaseName + "." + tableName
				+ "(phoneNum VarChar(20) Primary key NOT NULL, myPackageName VarChar(20), myResidueGNFreeDF bigint, myResidueSNFreeDF bigint, myResidueBDFreeDF bigint, "
				+ "myExceededGNDF bigint, myExceededSNDF bigint, myExceededBDDF bigint, myDFFee float(5,2) default 0, myResidueGNFreeVC bigint, myResidueSNFreeVC bigint, myResidueBVCreeVC bigint, myExceededGNVC bigint, myExceededSNVC bigint, myExceededBDVC bigint, myVCFee float(5,2) default 0, "
				+ " myResidueMes int(3), myExceededMes int(3), myMesFee float(5,2), myTotalFee float(5,2), ";
		String bestStr = "bestPackageName VarChar(20), bestResidueGNFreeDF bigint, bestResidueSNFreeDF bigint, bestResidueBDFreeDF bigint, "
				+ "bestExceededGNDF bigint, bestExceededSNDF bigint, bestExceededBDDF bigint, bestDFFee float(5,2) default 0, bestResidueGNFreeVC bigint, bestResidueSNFreeVC bigint, bestResidueBVCreeVC bigint, bestExceededGNVC bigint, bestExceededSNVC bigint, bestExceededBDVC bigint, bestVCFee float(5,2) default 0, "
				+ " bestResidueMes int, bestExceededMes int, bestMesFee float(5,2), bestTotalFee float(5,2));";
		tableStr += bestStr;
		database.creatTable(tableStr);

		String packageFileName = PropertiesUtils.PROJECT_PATH + PropertiesUtils.getPropertyValue("package");
		List<String> list = ReadFile.readPreLine(packageFileName);

		// 将第一种套餐作为所有用户的主套餐，计算出结果，并写入数据库
		String[] myPackage = list.get(1).split("\\|");

		GetDataThread getDataThread = new GetDataThread(inputDataTrafficQueue, myPackage);
		DroolsThread droolsThread = new DroolsThread(inputDataTrafficQueue, outputQueue);
		StorageThread storageThread = new StorageThread(outputQueue, true);

		getDataThread.start();
		droolsThread.start();
		storageThread.start();

		while (!isStorageEnd) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		// 依次计算其他各个套餐，如果找到比现在最佳套餐（包括主套餐）还省的套餐，就刷新数据库

	}
}
