package com.tax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tax.model.DO.Profitstatement;

public interface ProfitstatementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Profitstatement record);

    int insertSelective(Profitstatement record);

    Profitstatement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profitstatement record);

    int updateByPrimaryKeyWithBLOBs(Profitstatement record);

    int updateByPrimaryKey(Profitstatement record);
    
    /**��ȡ��3�µ�����
     * add by lzc     date: 2016��1��28��
     * @param taxCode
     * @param startDate
     * @param endDate
     * @return
     */
    List<Profitstatement> getLast3MonthProfitByTaxCode(@Param("taxCode")String taxCode, @Param("startDate")int startDate,
    		@Param("endDate")int endDate);
    
    /**��ȡyear�������
     * add by lzc     date: 2016��1��28��
     * @param taxCode
     * @param year
     * @return
     */
    List<Profitstatement> getProfitByYear(@Param("taxCode")String taxCode, @Param("year")int year);
    
    
    Profitstatement getProfitstatementByTaxCode(@Param("taxCode")String taxCode, @Param("date")int date);

    
    /**��ȡprofit����
     * add by lzc     date: 2016��2��1��
     * @param taxCode
     * @param reportDate
     * @return
     */
	Profitstatement getProfitByReportDate(@Param("taxCode")String taxCode, @Param("reprotDate")Integer reportDate);
    
    
}