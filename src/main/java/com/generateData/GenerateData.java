package com.generateData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

import com.utils.PropertiesUtils;

public class GenerateData {
	public static void main(String[] args) throws IOException {
		GenerateData generateData = new GenerateData();
		generateData.createBillingFiles();
	}
	
	/**
	 * 随即产生话单文件，一个用户产生三个文件，分别是短信、流量、语音
	 * @throws IOException
	 */
	public void createBillingFiles() throws IOException{
		String path = PropertiesUtils.getBillingRecordPath();
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		for (int i = 0; i < 1000; i++) {
			String voiceFile = i + "_voice.txt";
			String messageFile = i + "_message.txt";
			String dataTrafficFile = i + "_dataTraffic.txt";
			createFile(path, voiceFile);
			createFile(path, messageFile);
			createFile(path, dataTrafficFile);
			
			generateVoice(path + voiceFile);
			generateMessage(path + messageFile);
			generateDataTraffic(path + dataTrafficFile);
		}
	}
	
	public void generateVoice(String fileName) throws IOException{
		String[] records = new String[1000];
		
		for(int i = 0; i < 1000; i++){
			Random random = new Random();
			int t1 = random.nextInt(24);
			int t2 = random.nextInt(60);
			int t3 = random.nextInt(60);
			String time = t1 + ":" + t2 + ":" + t3;
			String type;
			if (random.nextInt(2) == 0) {
				type = "主叫";
			}else {
				type = "被叫";
			}
			
			int duration1 = random.nextInt(20);
			int duration2 = random.nextInt(60);
			String duration = duration1 + "分" + duration2 + "秒";
			
			int loc = random.nextInt(3);
			String location; 
			if (loc == 1) {
				location = "本地";
			}else if (loc == 2) {
				location = "省内";
			}else {
				location = "国内";
			}
			
			String record = time + "    " + type + "    " +  duration + "    " + location;
			records[i] = record;
		}
		
		FileOutputStream fos=new FileOutputStream(new File(fileName));
	    OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
	    BufferedWriter  bw=new BufferedWriter(osw);
	     
	    for(String record:records){
	           bw.write(record+"\t\n");
	    }
		
	    bw.close();
	    osw.close();
	    fos.close();
	}
	
	public void generateMessage(String fileName) throws IOException{
		Random random = new Random();
		int count = random.nextInt(300);
		String[] records = new String[count];
		
		for(int i = 0; i < count; i++){
			int t1 = random.nextInt(24);
			int t2 = random.nextInt(60);
			int t3 = random.nextInt(60);
			String time = t1 + ":" + t2 + ":" + t3;
			
			int loc = random.nextInt(2);
			String location; 
			if (loc == 1) {
				location = "国内";
			}else {
				location = "国外";
			}
			
			int typeInt = random.nextInt(10);
			String type; 
			if (typeInt < 9) {
				type = "短信";
			}else {
				type = "彩信";
			}
			
			String record = time + "    " + type +  "    " + location;
			records[i] = record;
		}
		
		FileOutputStream fos=new FileOutputStream(new File(fileName));
	    OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
	    BufferedWriter  bw=new BufferedWriter(osw);
	     
	    for(String record:records){
	           bw.write(record+"\t\n");
	    }
		
	    bw.close();
	    osw.close();
	    fos.close();
	}
	
	public void generateDataTraffic(String fileName) throws IOException{
		String[] records = new String[1000];
		
		for(int i = 0; i < 1000; i++){
			Random random = new Random();
			int t1 = random.nextInt(24);
			int t2 = random.nextInt(60);
			int t3 = random.nextInt(60);
			String time = t1 + ":" + t2 + ":" + t3;
			
			String type;
			int t = random.nextInt(6);
			if (t == 0) {
				type = "2G";
			}else if(t == 1){
				type = "3G";
			}else {
				type = "4G";
			}
			
			int duration1 = random.nextInt(20);
			int duration2 = random.nextInt(60);
			String duration = duration1 + "分" + duration2 + "秒";
			
			int loc = random.nextInt(3);
			String location; 
			if (loc == 1) {
				location = "本地";
			}else if (loc == 2) {
				location = "省内";
			}else {
				location = "国内";
			}
			
			int v1 = random.nextInt(5);
			int v2 = random.nextInt(1024);
			String traffic = v1 + "M" + v2 + "KB";
			
			String record = time + "    " + type + "    " +  duration + "    " + location + "    " + traffic;
			records[i] = record;
		}
		
		FileOutputStream fos=new FileOutputStream(new File(fileName));
	    OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
	    BufferedWriter  bw=new BufferedWriter(osw);
	     
	    for(String record:records){
	           bw.write(record+"\t\n");
	    }
		
	    bw.close();
	    osw.close();
	    fos.close();
	}
	
	
	public void createFile(String path, String fileName) throws IOException {
		File file = new File(path, fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		
	}
}
