package com.tax.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tax.common.Global;
import com.tax.model.DO.AddedValueTax;
import com.tax.model.DO.Balancesheet;
import com.tax.model.DO.Profitstatement;
import com.tax.model.DO.SpiderTaxTask;
import com.tax.model.DO.UserRecordInfo;
import com.tax.service.BalanceSheetService;
import com.tax.service.CommonService;
import com.tax.service.LoginService;
import com.tax.service.UserRecordService;
import com.tax.util.SecurityUtil;
import com.tax.view.ZipView;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
@Controller
@RequestMapping
public class HomeController {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private LoginService loginService;

	@Resource
	private CommonService commonService;
	
	@Resource
	private BalanceSheetService balanceSheetService;
	
	
	@Resource
	private UserRecordService userRecordService;
	
	
	@RequestMapping(value = {"index",""})
	public String chooseBank(HttpSession session) {
		session.invalidate();
		return "login/index";
	}
	
	
	@RequestMapping("contactinfo")
	public String contactInfo(HttpSession session, String bank){
		session.setAttribute(Global.USER_BANK, bank);
		return "login/contact";
	}
	
	@RequestMapping("addcontackinfo")
	public String addContactInfo(String company, String name, String phone, String validCode,HttpSession session){
		session.setAttribute(Global.USER_NAME, name);
		session.setAttribute(Global.USER_COMPANY, company);
		session.setAttribute(Global.USER_PHONE, phone);
		return "login/taxLogin";
	}
	
	
	@RequestMapping("tax")
	public String taxLogin(HttpSession session, String taxUser, String taxPwd,Model model){
		session.setAttribute(Global.TAX_CODE, taxUser);
		session.setAttribute(Global.TAX_PWD, taxPwd);
		model.addAttribute("vpdn", taxUser);
		
		return "login/vpdnLogin";
	}
	
	@RequestMapping("vpdn")
	public String vpdnLogin(String vpdnUser, String vpdnPwd,HttpSession session,Model model){
		session.setAttribute(Global.VPDN_USER, vpdnUser);
		session.setAttribute(Global.VPDN_PWD, vpdnPwd);
		model.addAttribute("taxUser", session.getAttribute(Global.TAX_CODE));
		model.addAttribute("vpdnUser", vpdnUser);
		return "login/checkLogin";
	}
	
	@RequestMapping("tomodify")
	public String toModify(HttpSession session){
		return "login/modifyLogin";
	}
	
	
	@RequestMapping("modify")
	public String modifyLogin(String taxUser, String taxPwd,String vpdnUser, String vpdnPwd,HttpSession session,Model model){
		session.setAttribute(Global.TAX_CODE, taxUser);
		session.setAttribute(Global.TAX_PWD, taxPwd);
		session.setAttribute(Global.VPDN_USER, vpdnUser);
		session.setAttribute(Global.VPDN_PWD, vpdnPwd);
		model.addAttribute("taxUser", taxUser);
		model.addAttribute("vpdnUser", vpdnUser);
		return "login/checkLogin";
	}
	
	
	@RequestMapping("login")
	@ResponseBody
	public String login(HttpSession session){
		JSONObject json = new JSONObject();
		try {
			SpiderTaxTask task = new SpiderTaxTask();
			task.setTaxcode((String)session.getAttribute(Global.TAX_CODE));
			task.setTaxpwd((String)session.getAttribute(Global.TAX_PWD));
			task.setVpdnuser((String)session.getAttribute(Global.VPDN_USER));
			task.setVpdnpwd((String)session.getAttribute(Global.VPDN_PWD));
			int re = loginService.login(task);
			UserRecordInfo user = new UserRecordInfo();
			
			if(re == 0){
				json.accumulate(Global.RESULT_STATUS, "first");
				json.accumulate(Global.RESULT_MESSAGE, "您好,您的申请已经提交,请24小时之后再次查询结果");
			}else if(re == 1){
				json.accumulate(Global.RESULT_STATUS, "wrong");
				String status = loginService.getWrongMsg(task);
				json.accumulate(Global.RESULT_MESSAGE, status);
				user.setMsg(status);
			}else if(re == 3) {
				json.accumulate(Global.RESULT_STATUS, "first");
				json.accumulate(Global.RESULT_MESSAGE, "您好,您的申请已经提交,稍后再试");
				user.setMsg("您好,您的申请已经提交,稍后再试");
			}
			else {
				json.accumulate(Global.RESULT_STATUS, "true");
				json.accumulate(Global.RESULT_MESSAGE, "true");
				session.setAttribute(Global.COMPANY_NAME, loginService.getCompany(task.getTaxcode()));
			}
			
			setUser(user, re, task, session);
			
			userRecordService.addUser(user);
			session.setAttribute(Global.USER_ID, user.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return json.toString();
	}
	
	private void setUser(UserRecordInfo user, int re, SpiderTaxTask task,
			HttpSession session) {
		// TODO Auto-generated method stub
		user.setBank(Integer.parseInt((String) session.getAttribute(Global.USER_BANK)));
		user.setName((String) session.getAttribute(Global.USER_NAME));
		user.setPhone((String) session.getAttribute(Global.USER_PHONE));
		user.setCompany((String) session.getAttribute(Global.USER_COMPANY));
		user.setStatus(re);
		user.setTaskId(task.getId());
		user.setAddTime(new Date());
	}


	@RequestMapping("tree")
	public String tree(){
		return "index";
	}
	
	
	@RequestMapping(value="/send/tel/code",method=RequestMethod.POST)
	public @ResponseBody String sendCode(String phone,HttpServletRequest request) {
		
		String code = SecurityUtil.randomStr(4);
		
//		boolean result = commonService.sendCode(phone,code);
		//测试用
		boolean result = true;
		code = "0000";
		
		if (result) {
			log.info("验证码发送成功---手机号："+phone+"；验证码："+code);
			request.getSession().setAttribute(Global.SESSION_CHECK_CODE, code);
			return "true";
		}else {
			log.info("验证码发送失败!!!---手机号："+phone+"；验证码："+code);
			return "false";
		}
		
	}
	
	@RequestMapping(value="/common/check/code",method=RequestMethod.POST)
	@ResponseBody
	public boolean checkCode(String phone,HttpSession session, String code){
		String sessionCode = (String) session.getAttribute(Global.SESSION_CHECK_CODE);
		if(sessionCode.endsWith(code)){
			return true;
		}
		return false;
	}
	
	
	
	
	
	@RequestMapping("download/zip")
	public ModelAndView downloadZip(int[] years, HttpSession session ){
		
		ZipView view = new ZipView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("years", years);
		String taxCode = (String) session.getAttribute(Global.TAX_CODE);
		
		for (int i = 0; i < years.length; i++) {
			List<Balancesheet> balancesheets = balanceSheetService.getBalancesheetListWithReportContent(years[i], taxCode);
			map.put("balance" + years[i], balancesheets);
		}
		
		
//		List<Profitstatement> profitstatements = new LinkedList<Profitstatement>();
//		List<AddedValueTax> addedValueTaxs = new LinkedList<AddedValueTax>();
		
		
		return new ModelAndView(view, map);
	}
	
	
	
	
	
	
	
}
