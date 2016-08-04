package com.process;

import java.sql.Connection;
import java.util.concurrent.LinkedBlockingDeque;

import com.User.User;
import com.database.Database;

public class StorageThread extends Thread{
	private LinkedBlockingDeque<User> outputQueue = new LinkedBlockingDeque<User>();		// drools规则匹配后的数据
	
	public StorageThread(LinkedBlockingDeque<User> outputQueue, boolean isItself) {
		this.outputQueue = outputQueue;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Database database = new Database();
		
		Connection con = database.getOneConnection();
		while (!Main.isOutputEnd || !outputQueue.isEmpty()) {
//		while(true){
			if (!outputQueue.isEmpty()) {
				User usermessage = outputQueue.pollFirst();
				
				database.insertTempRecord(usermessage, con, true);
			}else {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		database.closeConnection(con);
		Main.isStorageEnd = true;
	}
	
}
