package com.tax.service;

import com.tax.model.DO.SpiderTaxTask;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */

public interface LoginService {
	
	/**
	 * add by lzc     date: 2016��1��26��
	 * @param task
	 * @return ״̬��
	 * 0->��һ�ν���
	 * 1->����
	 * 2->������
	 * 3->������
	 */
	public int login(SpiderTaxTask task);
	
	/**��ȡ�����ݵ�ԭ��
	 * add by lzc     date: 2016��1��27��
	 * @param task
	 * @return
	 */
	public String getWrongMsg(SpiderTaxTask task); 
	
	/**��ȡ��˰������
	 * add by lzc     date: 2016��1��28��
	 * @param taxCode
	 * @return
	 */
	public String getCompany(String taxCode);
	

}
