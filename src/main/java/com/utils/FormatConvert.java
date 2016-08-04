package com.utils;

public class FormatConvert {
	/**
	 * 将02G230M50kb这种类型的流量表示成以KB为单位的流量数值
	 * @param quantity
	 * @return
	 */
	public static long getKB(String quantity) {
		String t = quantity.split("KB")[0];
		String[] tmp = t.split("M");
		if (tmp.length == 1) {		//如果不存在“M”，则表示只有kb，所以split结果只有长度1
			return Long.parseLong(t);
		}else {
			long kb = Long.parseLong(tmp[1]);
			String[] tmp2 = tmp[0].split("G");
			if (tmp2.length == 1) {
				return kb + 1024 * Long.parseLong(tmp2[0]);
			}else {
				long mb = Long.parseLong(tmp2[1]);
				long gb = Long.parseLong(tmp2[0]);
				return kb + 1024 * mb + 1024*1024 * gb;
			}
		}
	}
}
