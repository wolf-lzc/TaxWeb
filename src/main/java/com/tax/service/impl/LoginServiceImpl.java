package com.tax.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tax.dao.SpiderTaxTaskMapper;
import com.tax.dao.TaxOrgInfoMapper;
import com.tax.dao.UserRecordInfoMapper;
import com.tax.model.DO.SpiderTaxTask;
import com.tax.service.LoginService;
import com.tax.util.DateUtil;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Resource
	private SpiderTaxTaskMapper spiderTaxTaskMapper;
	
	@Resource
	private TaxOrgInfoMapper taxOrgInfoMapper;
	
	
	@Resource
	private UserRecordInfoMapper userRecordInfoMapper;
	
	@Override
	public int login(SpiderTaxTask task) {
		// TODO Auto-generated method stub
		int re = spiderTaxTaskMapper.isExistSpiderTask(task);
		if(re == 0){
			task.setStartdate(String.valueOf(DateUtil.get2YearBeginDate()));
			task.setEnddate(String.valueOf(DateUtil.getLastMonthEndDate()));
			task.setStatus(0);
			task.setAddtime(new Date());
			
			spiderTaxTaskMapper.insertSelective(task);
			
			return 0;
		}
		else {
			//��ȡtaskid
			task.setId(spiderTaxTaskMapper.getTaskByAccount(task.getTaxcode(), task.getTaxpwd(), task.getVpdnuser(), task.getVpdnpwd()).getId());

			//��ѯ�Ƿ����
			re = spiderTaxTaskMapper.getStatus(task);
			if(re == 101)
				return 1;
			
			//��ѯ�Ƿ�������
			re = taxOrgInfoMapper.countTaxOrgByTaxId(task.getTaxcode());
			if(re == 0)
				return 3;//������
			return 2;
		}
		
	}


	@Override
	public String getWrongMsg(SpiderTaxTask task) {
		// TODO Auto-generated method stub
		String msg = spiderTaxTaskMapper.getMsg(task);
		if(msg == null || msg.isEmpty() || msg == ""){
			msg = "�����������ڻ�ȡ��,�����ĵȴ�";
		}
		return msg;
	}


	@Override
	public String getCompany(String taxCode) {
		// TODO Auto-generated method stub
		return taxOrgInfoMapper.getNameByTaxCode(taxCode);
	}
	
	

}
