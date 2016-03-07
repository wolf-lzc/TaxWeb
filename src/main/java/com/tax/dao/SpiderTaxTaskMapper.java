package com.tax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tax.model.BO.MissionTaxTask;
import com.tax.model.DO.SpiderTaxTask;

public interface SpiderTaxTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpiderTaxTask record);

    int insertSelective(SpiderTaxTask record);

    SpiderTaxTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpiderTaxTask record);

    int updateByPrimaryKey(SpiderTaxTask record);
    
    /**�Ƿ���ڲ�ѯ��ҵ��Ϣ
     * add by lzc     date: 2016��1��25��
     * @param task
     * @return 
     */
    int isExistSpiderTask(SpiderTaxTask task);
    
    
    SpiderTaxTask getTaskByTaxCode(SpiderTaxTask task);
    
    /**����task��ȡ��ҵ��Ϣ
     * add by lzc     date: 2016��1��27��
     * @param task
     * @return
     */
    String getMsg(SpiderTaxTask task);
    
    
    /**����task��ȡ��ҵ��Ϣ
     * add by lzc     date: 2016��1��27��
     * @param task
     * @return
     */
	int getStatus(SpiderTaxTask task);

	
	/**��ȡ��ִ������unimpl
	 * add by lzc     date: 2016��1��29��
	 * @return
	 */
	List<MissionTaxTask> getMisstionList();

	
	/**��ȡ����
	 * add by lzc     date: 2016��2��1��
	 * @return
	 */
	SpiderTaxTask getReadyTask();
	
	/**
	 * add by lzc     date: 2016��2��1��
	 * @param clientId
	 * @param id
	 * @return
	 */
	int updateTask(@Param("clientId")String clientId, @Param("id")int id);
	
	
	/**����״ֵ̬100->0�뿪ʼ��������
	 * add by lzc     date: 2016��2��2��
	 * @param lastMonthStartDate
	 * @param lastMonthEndDate
	 */
	void resetTask(@Param("startDate")int lastMonthStartDate, @Param("endDate")int lastMonthEndDate);
	
	
	/**�����˻������ȡtask
	 * add by lzc     date: 2016��2��3��
	 * @param taxcode
	 * @param taxpwd
	 * @param vpdnuser
	 * @param vpdnpwd
	 * @return
	 */
	SpiderTaxTask getTaskByAccount(@Param("taxCode")String taxCode, @Param("taxPwd")String taxPwd, @Param("vpdnUser")String vpdnUser, 
			@Param("vpdnPwd")String vpdnpwd);
    
    
}