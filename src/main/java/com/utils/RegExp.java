package com.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {

	/**
	 * 正则表达式匹配，匹配你的第一个括号里面的内容，返回匹配结果
	 * 
	 * @param source
	 *            原始待匹配的数据
	 * @param regexp
	 *            正则表达式
	 * @return 匹配的内容，匹配不到则返回null
	 */
	public static String getFirstBracketMatch(String source, String regexp) {
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(source);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}
}
