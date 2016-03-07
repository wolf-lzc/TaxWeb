package com.tax.service;

import java.util.List;

import com.tax.model.DO.Profitstatement;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public interface ProfitService {
	
	/**
	 * add by lzc     date: 2016��1��26��
	 * @param year 0->��������
	 * @param TaxCode ��˰��
	 * @return
	 */
	public List<Profitstatement> getProfitList(int year, String TaxCode); 
	
	
	
	public Profitstatement getProfitByTime(int date, String TaxCode);

}
