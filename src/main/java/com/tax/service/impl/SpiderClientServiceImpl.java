package com.tax.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tax.dao.SpiderClientidMapper;
import com.tax.model.DO.SpiderClientid;
import com.tax.service.SpiderClientService;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
@Service
public class SpiderClientServiceImpl implements SpiderClientService{
	
	private Logger log = Logger.getLogger(getClass());

	@Resource
	private SpiderClientidMapper spiderClientidMapper;
	
	@Override
	public int addClient(SpiderClientid clientid) {
		// TODO Auto-generated method stub
		try {
			spiderClientidMapper.insert(clientid);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("add fail", e);
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteClient(String clientId) {
		// TODO Auto-generated method stub
		try {
			spiderClientidMapper.deleteByPrimaryKey(clientId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("add fail", e);
			return 0;
		}
		return 1;
	}

	@Override
	public List<SpiderClientid> getSpiderClientidList() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
