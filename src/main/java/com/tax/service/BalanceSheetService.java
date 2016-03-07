package com.tax.service;

import java.util.List;

import com.tax.model.DO.Balancesheet;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public interface BalanceSheetService {
	
	/**
	 * add by lzc     date: 2016��1��26��
	 * @param year 0->���������
	 * @param taxCode
	 * @return
	 */
	public List<Balancesheet> getBalancesheetList(int year, String taxCode);
	
	
	
	/**list with reportContent
	 * add by lzc     date: 2016��2��25��
	 * @param year
	 * @param taxCode
	 * @return
	 */
	public List<Balancesheet> getBalancesheetListWithReportContent(int year, String taxCode);
	
	
	/**
	 * add by lzc     date: 2016��1��26��
	 * @param taxCode
	 * @param date
	 * @return
	 */
	public Balancesheet getBalancesheetByDate(String taxCode, int date);

}
