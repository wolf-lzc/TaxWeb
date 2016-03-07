package com.tax.dao;

import org.apache.ibatis.annotations.Param;

import com.tax.model.DO.TaxOrgInfo;

public interface TaxOrgInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaxOrgInfo record);

    int insertSelective(TaxOrgInfo record);

    TaxOrgInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaxOrgInfo record);

    int updateByPrimaryKey(TaxOrgInfo record);
    
    /**����˰�Ż�ȡ��Ϣ
     * add by lzc     date: 2016��1��26��
     * @param taxId
     * @return
     */
    TaxOrgInfo getTaxOrgByTaxId(@Param("id")String taxId);
    
    
    int countTaxOrgByTaxId(@Param("id")String taxId);

    /**��ȡ��˰������
     * add by lzc     date: 2016��1��28��
     * @param taxCode
     * @return
     */
	String getNameByTaxCode(String taxCode);
}