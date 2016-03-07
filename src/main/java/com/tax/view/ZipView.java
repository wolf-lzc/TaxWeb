package com.tax.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.tax.common.Global;
import com.tax.model.BO.BalanceSheetContent;
import com.tax.model.BO.HSSFWorkbookWrapper;
import com.tax.model.BO.SheetContent;
import com.tax.model.DO.Balancesheet;
import com.tax.util.ExportToExcel;
import com.tax.util.ZipUtil;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public class ZipView extends AbstractView{

	private Logger log = Logger.getLogger(getClass());
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		File file = File.createTempFile("nashuichaxun", ".zip");
		setReturnType(response);
		
		List<HSSFWorkbookWrapper> list = new LinkedList<HSSFWorkbookWrapper>();
		
		
		int[] years = (int[]) model.get("years");
		for (int i = 0; i < years.length; i++) {
			List<Balancesheet> balancesheets = (List<Balancesheet>) model.get("balance" + years[i]);
			if(balancesheets != null && !balancesheets.isEmpty()){
				HSSFWorkbookWrapper wrapper = excelBalance(years[i], balancesheets);
				list.add(wrapper);
			}
		}
		ZipUtil.zipExcel(file, list);
		
		OutputStream os = response.getOutputStream();
		IOUtils.copy(new FileInputStream(file), os);
		os.flush();
		os.close();
		file.delete();
		
	}
	
	
	private HSSFWorkbookWrapper excelBalance(int years,
			List<Balancesheet> balancesheets) throws IOException {
		// TODO Auto-generated method stub
		HSSFWorkbookWrapper wrapper = new HSSFWorkbookWrapper();
		String string = String.valueOf(years);
		if(years == 0)
			string = "last3month";
		wrapper.setName("资产负责表" + string);
		String[] header = new String[] { Global.DOWNLOAD_BALANCE_SHEET_ASSET,
				Global.DOWNLOAD_BALANCE_SHEET_INDEX,
				Global.DOWNLOAD_BALANCE_SHEET_START,
				Global.DOWNLOAD_BALANCE_SHEET_END,
				Global.DOWNLOAD_BALANCE_SHEET_DEBT,
				Global.DOWNLOAD_BALANCE_SHEET_INDEX,
				Global.DOWNLOAD_BALANCE_SHEET_START,
				Global.DOWNLOAD_BALANCE_SHEET_END };
		//数据整理-sheet
		String[] sheetNames = new String[balancesheets.size()];
		
		//数据整理-内容
		List<SheetContent> sheets = new ArrayList<>();
		for (int i = 0; i < balancesheets.size(); i++) {
			Balancesheet balancesheet = balancesheets.get(i);
			BalanceSheetContent content = new BalanceSheetContent(balancesheet.getReportContent());
			//sheet名
			sheetNames[i] = content.getRepost_date().substring(0,6);
			
			//one sheet's data
			SheetContent sc = new SheetContent(content.getList().size());
			List<String[]> contentlist = new ArrayList<>();
			for (int j = 0; j < content.getList().size(); j++) {
				String[] tempS = new String[]{
						content.getList().get(j).getAsset(),
						content.getList().get(j).getIndex1(),
						content.getList().get(j).getBalance_begin1(),
						content.getList().get(j).getBalance_end1(),
						content.getList().get(j).getLiabilities(),
						content.getList().get(j).getIndex2(),
						content.getList().get(j).getBalance_begin2(),
						content.getList().get(j).getBalance_end2()
				};
				contentlist.add(tempS);
				
			}
			sc.setContents(contentlist);
			sheets.add(sc);
		}
		
		wrapper.setExcel(ExportToExcel.getHSSFWorkbook(sheets, sheetNames, header));
		return wrapper;
	}


	/** return type is download type 
	 * add by lzc     date: 2016年2月26日
	 * @param response
	 */
	private void setReturnType(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String name;
		try {
			response.setContentType("application/octet-stream");
			name = URLEncoder.encode("压缩.zip", "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename="
					+ name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("URLencode error", e);
		}
	
		
	}

}
