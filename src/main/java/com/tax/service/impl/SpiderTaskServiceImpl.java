package com.tax.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tax.dao.AddedValueTaxMapper;
import com.tax.dao.BalancesheetMapper;
import com.tax.dao.ProfitstatementMapper;
import com.tax.dao.SpiderClientidMapper;
import com.tax.dao.SpiderTaxTaskMapper;
import com.tax.dao.TaxOrgInfoLogMapper;
import com.tax.dao.TaxOrgInfoMapper;
import com.tax.model.DO.AddedValueTax;
import com.tax.model.DO.Balancesheet;
import com.tax.model.DO.Profitstatement;
import com.tax.model.DO.SpiderClientid;
import com.tax.model.DO.SpiderTaxTask;
import com.tax.model.DO.TaxOrgInfo;
import com.tax.service.SpiderTaskService;
import com.tax.util.Base64;
import com.tax.util.JsonUtil;
import com.tax.util.Md5;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
@Service
//@Scope(value="prototype")
public class SpiderTaskServiceImpl implements SpiderTaskService{
	
	@Resource
	private AddedValueTaxMapper addedValueTaxMapper;
	
	@Resource
	private BalancesheetMapper balancesheetMapper;
	
	@Resource
	private ProfitstatementMapper profitstatementMapper;

	@Resource
	private SpiderClientidMapper spiderClientidMapper;
	
	@Resource
	private TaxOrgInfoMapper taxOrgInfoMapper;
	
	@Resource 
	private TaxOrgInfoLogMapper taxOrgInfoLogMapper;
	
	@Resource
	private SpiderTaxTaskMapper SpiderTaxTaskMapper;
	
	private Logger log = Logger.getLogger(getClass());
	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,rollbackFor=Exception.class)
	public String begin(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String errMsg = "";
        String result = "";
        int code = 0;

        try{
            code = Integer.parseInt(request.getParameter("code"));
        }
        catch (Exception e){
            errMsg = "���������벻����";
            return errMsg;
        }
        log.debug("code = " + code);
        switch (code){
            case 1:{//��ȡ��֤��
                result = requestCode(request);
                break;
            }
            case 2:{//��ȡ������Ϣ
                if (!authe(request))
                    throw new Exception("�ͻ�����֤ʧ��");
                String clientId = request.getSession().getAttribute("clientId").toString();
                if (clientId == null || clientId.equals(""))
                    throw  new Exception("�ͻ���id������");
                try {
					result = getTask(clientId);
				} catch (Exception e) {
					// TODO: handle exception
					throw new Exception("��������ʧ��");
				}
                break;
            }
            case 3: {//������ҵ������Ϣ
            	boolean re =false;
                if (!authe(request))
                    throw new Exception("�ͻ�����֤ʧ��");

//                boolean re = TaxOrgInfo.save(request);
                String jsonData = request.getParameter("jsondata");
                jsonData = Base64.getFromBase64(jsonData);
                
                TaxOrgInfo tInfo = JsonUtil.getTaxOrgInfo(jsonData);
                String tax_code = tInfo.getTaxCode();
                if (tax_code==null||tax_code.equals(""))
                    throw new Exception("��˰��ʶ��Ŵ���");
                TaxOrgInfo oInfo = taxOrgInfoMapper.getTaxOrgByTaxId(tax_code);
                if (oInfo == null) {
                	try {
                		tInfo.setUpdatetime(new Date());
                		taxOrgInfoMapper.insertSelective(tInfo);
                		re = true;
					} catch (Exception e) {
						// TODO: handle exception
						log.error("insert wrong", e);
						throw e;
					}
				}else {
					if(tInfo.comp(oInfo)){
						re = true;
					}else {
						try {
							re = saveBaseInfo(tInfo);
						} catch (Exception e) {
							// TODO: handle exception
							log.error("���������Ϣ����", e);
							throw e;
						}
							
					}
				}
                
                
                if (re)
                    result = "[ok]";
                else
                    result = "[err]";
                break;
            }
            case 4:{//������ֵ˰�걨��Ϣ
                if (!authe(request))
                    throw new Exception("�ͻ�����֤ʧ��");

                result = saveVat(request);
                break;
            }
            case 5:{//�����ʲ���ծ��
                if (!authe(request))
                    throw new Exception("�ͻ�����֤ʧ��");

                Balancesheet bSheet = JsonUtil.getBalanceSheet(request);
                if (bSheet.getTaxCode()==null || bSheet.getTaxCode().equals(""))
                    throw new Exception("��˰��ʶ���Ϊ��");

                if (bSheet.getReportDate()<=0)
                    throw new Exception("�ʲ���ծ�����ڴ���");

                if (bSheet.getReportContent()==null || bSheet.getReportContent().equals(""))
                    throw new Exception("�ʲ���ծ������Ϊ��");
                try {
                     new JSONObject(bSheet.getReportContent());
                }
                catch (JSONException e){
                    throw new Exception("�����ʲ���ծ�����ݴ���");
                }
                saveBalance(bSheet);
                result = "[ok]";
                break;
            }
            case 6:{//���������
                if (!authe(request))
                    throw new Exception("�ͻ�����֤ʧ��");
                

                Profitstatement pSheet = JsonUtil.getProfit(request);
                if (pSheet.getTaxCode()==null || pSheet.getTaxCode().equals(""))
                    throw new Exception("��˰��ʶ���Ϊ��");

                if (pSheet.getReportDate()<=0)
                    throw new Exception("�ʲ���ծ�����ڴ���");

                if (pSheet.getReportContent()==null || pSheet.getReportContent().equals(""))
                    throw new Exception("�ʲ���ծ������Ϊ��");

                try {
                    new JSONObject(pSheet.getReportContent());
                }
                catch (JSONException e){
                    throw new Exception("�����ʲ���ծ�����ݴ���");
                }
                saveProfit(pSheet);
                result = "[ok]";
                break;
            }
//            case 7:{//���淢Ʊ��Ϣ
//                if (!authe(request))
//                    throw new Exception("�ͻ�����֤ʧ��");
//
//                InvoiceInfo ivInfo = new InvoiceInfo(request);
//                ivInfo.save();
//                result = "[ok]";
//                break;
//            }
            case 101:{//����ͻ��˴�����Ϣ
                String msg = request.getParameter("msg");
                msg = Base64.getFromBase64(msg);
                if (msg!=null&&!msg.equals("")){
                    throw new Exception(msg);
                }
                break;
            }
            case 100:{
                break;
            }
            default:
            {
                errMsg = "�������������";
                return errMsg;
            }
        }

        return result;
	}
	
	@Transactional
	private void saveProfit(Profitstatement pSheet) {
		// TODO Auto-generated method stub
		Profitstatement profitstatement = profitstatementMapper.getProfitByReportDate(pSheet.getTaxCode(), pSheet.getReportDate());
		if(profitstatement == null){
			profitstatementMapper.insertSelective(pSheet);
		}
	}

	@Transactional
	private void saveBalance(Balancesheet bSheet) {
		// TODO Auto-generated method stub
		Balancesheet balancesheet = balancesheetMapper.getBalanceSheetByReprotDate(bSheet.getTaxCode(), bSheet.getReportDate());
		if (balancesheet == null) {
			balancesheetMapper.insertSelective(bSheet);
		}
	}


	private  String saveVat(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String jsonData = request.getParameter("jsondata");
        jsonData = Base64.getFromBase64(jsonData);
        AddedValueTax[] vatList = JsonUtil.parseByJson(jsonData);
        for (AddedValueTax addedValueTax : vatList) {
			if (addedValueTax.getTaxCode() == null || addedValueTax.getTaxCode().isEmpty()) {
				 throw new Exception("��˰��ʶ���Ϊ��");
			}
			AddedValueTax tax = addedValueTaxMapper
					.getAddedValueByTaxCode(addedValueTax.getDeclaredate(),addedValueTax.getTaxCode());
			if (tax == null) {
				addedValueTaxMapper.insertSelective(addedValueTax);
			}
			
		}
        return "[ok]";
	}


	@Transactional(rollbackFor=Exception.class)
	private boolean  saveBaseInfo(TaxOrgInfo tInfo) {
		// TODO Auto-generated method stub
		try {
			taxOrgInfoLogMapper.insertTaxOrgInfo(tInfo.getTaxCode());
			tInfo.setUpdatetime(new Date());
			taxOrgInfoMapper.updateByPrimaryKeySelective(tInfo);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("������Ϣʧ��", e);
			throw e;
		}
		return true;
	}


	/**
     * ������֤��
     * @param request **
     * @return **
     */
    private static String requestCode(HttpServletRequest request){
        String clientId = request.getParameter("clientid");

        if (clientId == null || clientId.equals(""))
            return "�����ͻ���idΪ��";

        Calendar calendar = Calendar.getInstance();
        long aCode = calendar.getTime().getTime();
        String autheCode = String.valueOf(aCode);
        HttpSession session = request.getSession();
        session.setAttribute("autheCode",autheCode);
        session.setAttribute("clientId",clientId);
        session.setMaxInactiveInterval(60);
        return "[ok]"+autheCode;
    }
    
    /**
     * ��֤�ͻ���
     * @param request **
     * @return
     */
    private boolean authe(HttpServletRequest request){
        String code = request.getParameter("code");
        String autheKey = request.getParameter("authekey");
        HttpSession session = request.getSession();
        String clientId = session.getAttribute("clientId").toString();
        String autheCode = session.getAttribute("autheCode").toString();
        String client_pwd = "";

        try {
        	if (clientId == null || clientId.equals(""))
        		return false;
        	SpiderClientid client = spiderClientidMapper.selectByPrimaryKey(clientId);
        	if(client == null)
        		return false;
            client_pwd = client.getPasswordClient();
        }
        catch (Exception e)
        {
            return false;
        }

        String key = code + clientId + autheCode + client_pwd;
        key = Md5.strToMD5(key);
        return  key.equals(autheKey);
    }


	@Override
	public void updateTask(HttpServletRequest request, String msg) {
		// TODO Auto-generated method stub
		 	int code  = 0;
	        try {
	            code = Integer.parseInt(request.getParameter("code"));
	        }
	        catch (Exception e){
	            code = 0;
	        }
	        int id = 0;
	        try {
	            id = Integer.parseInt(request.getParameter("taskid"));
	        }
	        catch (Exception e){
	            id = 0;
	        }
	        if (msg == null)
	            msg = "";
	
	        if (code<3||id==0||(code!=100&&code!=101))
	            return;
	        SpiderTaxTask task = new SpiderTaxTask();
	        task.setId(id);
	        task.setMsg(msg);
	        task.setStatus(code);
	        task.setUpdatetime(new Date());
	        
	        SpiderTaxTaskMapper.updateByPrimaryKeySelective(task);
	}
	
	
	@Transactional
	private  String  getTask(String clientId){
		log.debug("getTask");
		synchronized (this) {
			SpiderTaxTask task = SpiderTaxTaskMapper.getReadyTask();
			if(task == null){
				log.debug("task == null");
				return "[ok]";
				
			}
				
			
			SpiderTaxTaskMapper.updateTask(clientId, task.getId());
			return  "[ok]" +  task.toJson();
		}
		
	}


	
	
	
	
	
	

}
