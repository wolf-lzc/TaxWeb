package com.tax.service;

import java.util.List;

import com.tax.model.DO.AddedValueTax;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public interface AddedValueService {
	
	/**
	 * add by lzc     date: 2016��1��26��
	 * @param type 0->��������� n->n��
	 * @param taxCode
	 * @return
	 */
	public List<AddedValueTax> getAddedValueTaxs(int type, String taxCode, int pageNo, int pageSize );
	
	
	
	

}
