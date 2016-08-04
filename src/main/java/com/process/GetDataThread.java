package com.process;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import com.BillingType.DataTraffic;
import com.User.UserDataTraffic;
import com.utils.FormatConvert;
import com.utils.GetFilePathAndName;
import com.utils.PropertiesUtils;

public class GetDataThread extends Thread{
	private LinkedBlockingDeque<List<DataTraffic>> inputDataTrafficQueue;
	private String[] myPackage;
	public GetDataThread(LinkedBlockingDeque<List<DataTraffic>> inputDataTrafficQueue, String[] myPackage) {
		this.inputDataTrafficQueue = inputDataTrafficQueue;
		this.myPackage = myPackage;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String dataTrafficFilePath = PropertiesUtils.getDatatrafficFilePath();
		String[] fileNames = GetFilePathAndName.getFileNameList(dataTrafficFilePath);
		UserDataTraffic userDataTraffic = new UserDataTraffic(myPackage[0], Float.parseFloat(myPackage[1]), FormatConvert.getKB(myPackage[2]), FormatConvert.getKB(myPackage[3]), FormatConvert.getKB(myPackage[4]), Float.parseFloat(myPackage[5]), Float.parseFloat(myPackage[6]), Float.parseFloat(myPackage[7]));
		for (String string : fileNames) {
			String fn = dataTrafficFilePath + "/" + string;
			List<DataTraffic> dataTrafficList = GetRecord.getDataTrafficContent(fn, userDataTraffic);
			while(!inputDataTrafficQueue.offer(dataTrafficList)) {	//如果没有空间插入，则等待
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Main.isInputEnd = true;
		
	}
	
}
