package com.tax.model.BO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public class HSSFWorkbookWrapper {
	
	private String name;//Ãû×Ö
	private HSSFWorkbook excel;//excel
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HSSFWorkbook getExcel() {
		return excel;
	}
	public void setExcel(HSSFWorkbook excel) {
		this.excel = excel;
	}
	
	
	
	

}
