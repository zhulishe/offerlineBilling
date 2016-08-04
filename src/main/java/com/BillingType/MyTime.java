package com.BillingType;

public class MyTime {
	
	/**
	 * 判断第一个时间是不是在后面两个时间组成的区间内，是就返回true，否则返回false
	 * @param time
	 * @param timeStart
	 * @param timeEnd
	 * @return
	 */
	public static boolean isInSection(String time, String timeStart, String timeEnd){
		int callTime = time2sec(time);
		int sectionStartTime = time2sec(timeStart);
		int sectionEndTime = time2sec(timeEnd);
		if (callTime < sectionStartTime || callTime > sectionEndTime) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 将string类型的时间（如02：30：58）转换为秒，00：00：00为第0秒
	 * @param strTime
	 */
	public static int time2sec(String strTime){
		String[] tmp = strTime.split(":");
		if (tmp.length != 3) {
			return -1;
		}else {
			return Integer.parseInt(tmp[2].trim()) + 60 * Integer.parseInt(tmp[1].trim()) + 3600 * Integer.parseInt(tmp[0].trim()); 
		}
	}
	
	/**
	 * 将带中文字的时间（如01时02分30秒）转换为秒，00时00分00秒表示0秒
	 * @param time
	 * @return
	 */
	public static int chineseTime2sec(String time) {
		String t = time.split("秒")[0];
		String[] tmp = t.split("分");
		if (tmp.length == 1) {		//如果不存在“分”，则表示只有秒，所以split结果只有长度1
			return Integer.parseInt(t);
		}else {
			int sec = Integer.parseInt(tmp[1]);
			String[] tmp2 = tmp[0].split("时");
			if (tmp2.length == 1) {
				return sec + 60 * Integer.parseInt(tmp2[0]);
			}else {
				int min = Integer.parseInt(tmp2[1]);
				int hour = Integer.parseInt(tmp2[0]);
				return sec + 60 * min + 3600 * hour;
			}
		}
	}
	
	public static void main(String[] args) {
		int i = MyTime.time2sec("23:01:05");
		System.out.println(i);
		
		int j = MyTime.chineseTime2sec("1时01分05秒");
		System.out.println(j);
	}
}
