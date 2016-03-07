package com.tax.util;

import java.util.Random;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public class SecurityUtil {

	/**
	 * ���������
	 * @param bits λ��
	 * @return �����
	 */
	public static String randomStr(int bits) {
		
		String result = "";
		try {
			result = "";
			
			Random random = new Random();
			
			char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz").toCharArray();
			
			char[] randBuffer = new char[bits];
			
			for (int i = 0; i < randBuffer.length; i++) {
				randBuffer[i] = numbersAndLetters[random.nextInt(36)];
			}
			result = new String(randBuffer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
