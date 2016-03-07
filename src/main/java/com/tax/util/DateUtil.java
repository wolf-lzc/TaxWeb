package com.tax.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public class DateUtil {

	/**��ȡ��ǰʱ��ǰ�����µ��³�����
	 * add by lzc     date: 2016��1��26��
	 * ex:
	 * now:2016��1��26��11:31:46
	 * @return 20151001
	 */
	public static int getLatst3MonthStartDate(){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-3);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		
		
		return Integer.parseInt(df.format(calendar.getTime()).toString());
	}
	
	/**��ȡ�ϸ�����ĩ����
	 * add by lzc     date: 2016��1��26��
	 * ex:
	 * now:2016��1��26��11:33:14
	 * @return 20151231
	 */
	public static int getLastMonthEndDate(){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DATE));
		return Integer.parseInt(df.format(calendar.getTime()).toString());
		
	}
	
	/**��ȡ�ϸ����³�����
	 * add by lzc     date: 2016��2��2��
	 * @return
	 */
	public static int getLastMonthStartDate(){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return Integer.parseInt(df.format(calendar.getTime()).toString());
		
	}
	
	
	
	
	
	/**��ȡ����ǰ��һ��
	 * add by lzc     date: 2016��1��29��
	 * @param year
	 * @return
	 */
	public static int get2YearBeginDate(){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-2);
		calendar.set(Calendar.MONTH,0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return Integer.parseInt(df.format(calendar.getTime()).toString());
	}
	
	
	/**��ȡX���һ��
	 * add by lzc     date: 2016��1��30��
	 * @param year
	 * @return
	 */
	public static int getYearBeginDate(int year){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH,0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return Integer.parseInt(df.format(calendar.getTime()).toString());
	}
	
	/**��ȡX�����һ��
	 * add by lzc     date: 2016��1��30��
	 * @param year
	 * @return
	 */
	public static int getYearEndDate(int year){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH,11);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getMaximum(Calendar.DAY_OF_MONTH));
		return Integer.parseInt(df.format(calendar.getTime()).toString());
	}
	
	
	
	public static void main(String[] args) {
		getLastMonthEndDate();
		getLastMonthStartDate();
	}
	
}
