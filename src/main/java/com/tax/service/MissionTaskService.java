package com.tax.service;
/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public interface MissionTaskService {
	
	/**��δִ�еĲ�ѯ����������뵽��ִ�������,���޸�δִ�в�ѯ��־λΪ(1)
	 * add by lzc     date: 2016��1��28��
	 *
	 */
	public void beginMission();
	
	
	/**��δִ�еĲ�ѯ�����������ô����־λ(2)
	 * add by lzc     date: 2016��1��28��
	 *
	 */
	public void failMission();

	/**���������б�
	 * add by lzc     date: 2016��2��2��
	 *
	 */
	public void resetTask();

	

}
