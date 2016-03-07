package com.tax.service;

import java.util.List;

import com.tax.model.DO.SpiderClientid;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public interface SpiderClientService {
	
	/**添加虚拟机
	 * add by lzc     date: 2016年2月17日
	 * @param clientid
	 * @return
	 */
	public int addClient(SpiderClientid clientid);

	/**删除虚拟机
	 * add by lzc     date: 2016年2月17日
	 * @param clientId
	 * @return
	 */
	public int deleteClient(String clientId);
	
	
	
	public List<SpiderClientid> getSpiderClientidList();
}
