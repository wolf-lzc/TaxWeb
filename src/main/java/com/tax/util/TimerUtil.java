package com.tax.util;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tax.service.MissionTaskService;
import com.tax.service.SpiderTaskService;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
@Component
public class TimerUtil {

	private Logger log = Logger.getLogger(getClass());
	
	
	@Resource
	private MissionTaskService missionTaskService;
	
	@Resource
	private SpiderTaskService spiderTaskService;
	
	
	public void resetTask(){
		log.debug("ÿ�²�ѯ����ʼ " + new Date());
		missionTaskService.resetTask();
		log.debug("ÿ�²�ѯ������� " + new Date());
	}
	
	
	
	
	
}
