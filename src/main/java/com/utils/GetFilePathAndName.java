package com.utils;

import java.io.File;

public class GetFilePathAndName {

	/**
	 * 根据文件夹名字，返回该文件夹下所有文件名字组成的一个数组
	 * @param path
	 */
    public static String[] getFileNameList(String path){   
        File file = new File(path);   
        return file.list();
    }   
    
}
