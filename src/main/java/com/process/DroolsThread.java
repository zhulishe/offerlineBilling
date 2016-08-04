package com.process;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import javax.activation.MailcapCommandMap;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.BillingType.DataTraffic;
import com.User.User;


public class DroolsThread extends Thread{
	private LinkedBlockingDeque<List<DataTraffic>> inputDataTrafficQueue = new LinkedBlockingDeque<List<DataTraffic>>(1000);
	private LinkedBlockingDeque<User> outputQueue = new LinkedBlockingDeque<User>();		// 需要处理一下这个队列的长度
	
	public DroolsThread(LinkedBlockingDeque<List<DataTraffic>> inputDataTrafficQueue, LinkedBlockingDeque<User> outputQueue){
		this.inputDataTrafficQueue = inputDataTrafficQueue;
		this.outputQueue = outputQueue;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		
		int count = 0;
		while (!inputDataTrafficQueue.isEmpty() || !Main.isInputEnd) {
			if (!inputDataTrafficQueue.isEmpty()) {
				List<DataTraffic> dt = inputDataTrafficQueue.pollFirst();
				User usermessage = dt.get(0).getUser();
				for (DataTraffic dataTraffic : dt) {
					kSession.insert(dataTraffic);
				}
				kSession.fireAllRules();
				outputQueue.add(usermessage);
				System.out.println(++count);
			}
		}
		Main.isOutputEnd = true;
	}
}
