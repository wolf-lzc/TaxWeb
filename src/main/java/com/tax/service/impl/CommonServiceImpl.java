package com.tax.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tax.service.CommonService;
import com.tax.util.CommonFunction;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
@Service
public class CommonServiceImpl implements CommonService{

	// ��������
	private static String strContent = CommonFunction.getValue("sms.content");
	private static String sendUrl = CommonFunction.getValue("sms.send.url");
	private static String account = CommonFunction.getValue("sms.account");
	private static String password = CommonFunction.getValue("sms.password");
	
	
	private Logger log = Logger.getLogger(getClass());
	
	@Override
	public boolean sendCode(String phone, String code) {
		// TODO Auto-generated method stub
		// ���
		String result = "";

		String line;

		BufferedReader in = null;

		try {
			// ��������˵��
			String content = strContent.replace("%", code);

			// ���ݱ���ת��
			String strTemp = URLEncoder.encode(content, "utf-8");

			// url����
			String strUrl = sendUrl + "?account=" + account + "&password="
					+ password + "&mobile=" + phone + "&content=" + strTemp;

			URL url = new URL(strUrl);

			URLConnection conn = url.openConnection();

			conn.connect();

			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			// �������
			while ((line = in.readLine()) != null) {
				result += line;
			}

			if (!result.equals("100")) {
				log.info("<<<������֤�뷢��ʧ�ܴ���-->" + result + ">>>�ֻ�����Ϊ:"+phone);
			} else {
				log.info("<<<������֤�뷢�ͳɹ�-->" + result + ">>>�ֻ�����Ϊ:"+phone);
				return true;
			}

		} catch (HttpException e) {
			e.printStackTrace();
			log.error("httpError : " + e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			log.error("IOError : " + e.toString());
		} finally {
			// �ͷ�
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
