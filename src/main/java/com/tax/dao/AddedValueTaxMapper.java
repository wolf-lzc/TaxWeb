package com.tax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tax.model.DO.AddedValueTax;

public interface AddedValueTaxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddedValueTax record);

    int insertSelective(AddedValueTax record);

    AddedValueTax selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddedValueTax record);

    int updateByPrimaryKey(AddedValueTax record);
    
    /**��ȡ��������µ���ֵ˰��¼
     * add by lzc     date: 2016��1��26��
     * @param startDate
     * @param endDate
     * @param taxCode
     * @return
     */
    List<AddedValueTax> getAddedValueLastThreeMonth(@Param("startDate")int startDate, 
    		@Param("endDate")int endDate, @Param("taxCode")String taxCode, @Param("pageNo")int pageNo, @Param("pageSize")int pageSize);
    
    /**��ȡĳ�����ֵ˰��¼
     * add by lzc     date: 2016��1��26��
     * @param year
     * @param taxCode
     * @return
     */
    List<AddedValueTax> getAddedValueByYear(@Param("year")int year, @Param ("taxCode")String taxCode, @Param("pageNo")int pageNo,
    		@Param("pageSize")int pageSize);

    /**��ȡ��ֵ˰��¼
     * add by lzc     date: 2016��2��1��
     * @param declareDate
     * @param taxCode
     * @return
     */
	AddedValueTax getAddedValueByTaxCode(@Param("declareDate")Integer declareDate, @Param("taxCode")String taxCode);
    
    
}