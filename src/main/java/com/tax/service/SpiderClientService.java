package com.tax.service;

import java.util.List;

import com.tax.model.DO.SpiderClientid;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public interface SpiderClientService {
	
	/**��������
	 * add by lzc     date: 2016��2��17��
	 * @param clientid
	 * @return
	 */
	public int addClient(SpiderClientid clientid);

	/**ɾ�������
	 * add by lzc     date: 2016��2��17��
	 * @param clientId
	 * @return
	 */
	public int deleteClient(String clientId);
	
	
	
	public List<SpiderClientid> getSpiderClientidList();
}
