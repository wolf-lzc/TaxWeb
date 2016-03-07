/*
 * ����Ħ�������豸���޹�˾
 * MOKO-Commercial Device Co.,Ltd
 * 
 * ��Խ��
 * 
 * �����ˣ�é
 * 
 * ���ڣ�2015/04/07
 * 
 * �汾v1.0.0
 * 
 * bug�޸�:
 * 
 * 
 */
package com.tax.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * modify ywh 2015-11-30 downLoadFile
 */
public class CommonFunction {

	private static Properties props = new Properties();
	
	
	Logger log = Logger.getLogger(CommonFunction.class);

	/**
	 * �����Դ�ļ�
	 */
	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return props.getProperty(key);
	}

	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}

	/**
	 * �ϴ��ļ�
	 * 
	 * @param uploadPath
	 *            ָ��·��
	 * @param fileMap
	 *            �ļ�
	 * @return
	 */
	public boolean fileUpload(String uploadPath,
			HashMap<CommonsMultipartFile, String> fileMap) {

		String realPath = uploadPath;

		try {
			File file = new File(realPath);
			if (!file.exists()) {
				file.mkdirs();
			}
//			System.out.println(file.getAbsolutePath());
			// ����ļ���:
			for (Iterator<CommonsMultipartFile> it = fileMap.keySet()
					.iterator(); it.hasNext();) {
				CommonsMultipartFile key = (CommonsMultipartFile) it.next();
				String value = fileMap.get(key);

				String realFileName = key.getOriginalFilename();
				if (!value.equals("")) {
					realFileName = value;
				}

				File uploadFile = new File(realPath + "\\" + realFileName);
				if (key.getSize() != 0) {
					FileCopyUtils.copy(key.getBytes(), uploadFile);
				}

			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

	

	


	
	
	
	
}
