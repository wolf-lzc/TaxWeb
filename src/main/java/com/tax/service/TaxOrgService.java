package com.tax.service;

import com.tax.model.DO.TaxOrgInfo;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public interface TaxOrgService {
	/**��ȡ��ҵ������Ϣ
	 * add by lzc     date: 2016��1��26��
	 * @param taxCode
	 * @return
	 */
	TaxOrgInfo getOrgInfoByTaxId(String taxCode);
	
	

}
