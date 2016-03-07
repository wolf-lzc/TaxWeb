package com.tax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tax.model.DO.Balancesheet;

public interface BalancesheetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Balancesheet record);

    int insertSelective(Balancesheet record);

    Balancesheet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Balancesheet record);

    int updateByPrimaryKeyWithBLOBs(Balancesheet record);

    int updateByPrimaryKey(Balancesheet record);
    
    /**��ȡ�������µ��ʲ���ծ����Ϣ
     * add by lzc     date: 2016��1��26��
     * @param taxCode
     * @param startDate
     * @param endDate
     * @return
     */
    List<Balancesheet> getLast3MonthBalancesheets(@Param("taxCode")String taxCode, @Param("startDate")int startDate, 
    		@Param("endDate")int endDate);
    
    /**��ȡ**����ʲ���ծ����Ϣ
     * add by lzc     date: 2016��1��26��
     * @param taxCode
     * @param year
     * @return
     */
    List<Balancesheet> getBalancesheetsByYear(@Param("taxCode")String taxCode, @Param("year")int year);
    
    
    
    /**��ȡ�������µ��ʲ���ծ����Ϣ
     * add by lzc     date: 2016��1��26��
     * @param taxCode
     * @param startDate
     * @param endDate
     * @return
     */
    List<Balancesheet> getLast3MonthBalancesheetsWithReportContent(@Param("taxCode")String taxCode, @Param("startDate")int startDate, 
    		@Param("endDate")int endDate);
    
    /**��ȡ**����ʲ���ծ����Ϣ
     * add by lzc     date: 2016��1��26��
     * @param taxCode
     * @param year
     * @return
     */
    List<Balancesheet> getBalancesheetsByYearWithReportContent(@Param("taxCode")String taxCode, @Param("year")int year);
    
    
    
    Balancesheet getBalancesheetByTaxCode(@Param("taxCode")String taxCode, @Param("reportDate")String ReportDate);
    
    
    /**���ݱ������ڻ�ȡ�ʲ���ծ��
     * add by lzc     date: 2016��2��1��
     * @param taxCode
     * @param ReportDate
     * @return
     */
    Balancesheet getBalanceSheetByReprotDate(@Param("taxCode")String taxCode, @Param("reportDate")Integer ReportDate);
    
    
}