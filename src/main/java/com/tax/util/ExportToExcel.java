package com.tax.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tax.model.BO.SheetContent;
/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public class ExportToExcel {
	
	/**
	 * ��sheet����
	 * @param response
	 * @param fileName
	 * @param sheetName
	 * @param header
	 * @param dataList
	 * @throws Exception
	 */
	public void export(HttpServletResponse response, String fileName,
			String sheetName, String[] header, List<String[]> dataList)
			throws Exception {

		//�ļ������Ļ�
		byte[] fileNameByte = fileName.getBytes("gb2312");
		String filename = new String(fileNameByte, "iso8859-1");

		// ��������
		byte[] bytes = this.formatData(dataList, sheetName, header);

		// ����������ز���
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		response.setContentType("application/vnd.ms-excel");  
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
	}
	
	/**
	 * ��sheet����
	 * @param response
	 * @param fileName �ļ���
	 * @param header ��һ��
	 * @param sheetNames sheet��
	 * @param dataList ����
	 */
	public void allExport(HttpServletResponse response, String fileName,String[] header, String[] sheetNames,List<SheetContent> dataList) throws Exception {
		
		//�ļ������Ļ�
		byte[] fileNameByte = fileName.getBytes("gb2312");
		String filename = new String(fileNameByte, "iso8859-1");
		
		// ��������
		byte[] bytes = this.formatDataAll(dataList, sheetNames, header);
		
		// ����������ز���
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename);
		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
	}

	/**
	 * excel��������
	 * 
	 * @param dataList
	 * @param sheetName
	 * @param header
	 * @return
	 * @throws IOException
	 */
	private byte[] formatData(List<String[]> dataList, String sheetName,
			String[] header) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// ����һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		
		// ��webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// ��sheet����ӱ�ͷ��1��
		HSSFRow row = sheet.createRow(0);

		// ������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// ���ñ�ͷ
		List<String> excelHead = getExcelHead(header);

		HSSFCell cell = null;

		// excelͷ
		for (int i = 0; i < excelHead.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(excelHead.get(i));
			cell.setCellStyle(style);
			// �����п�
			sheet.setColumnWidth(i, excelHead.get(i).getBytes().length * 256);
		}

		// д������
		for (int i = 0; i < dataList.size(); i++) {
			row = sheet.createRow(i + 1);
			String[] temp = dataList.get(i);

			// ������Ԫ�񣬲�����ֵ
			int j = 0;

			for (int k = 0; k < temp.length; k++) {
				insertCell(row, j++, temp[k]);
			}
		}

		wb.write(out);

		return out.toByteArray();
	}
	
	/**
	 * excel��������all
	 * 
	 * @param dataList
	 * @param sheetName
	 * @param header
	 * @return
	 * @throws IOException
	 */
	private byte[] formatDataAll(List<SheetContent> dataAll, String[] sheetNames,
			String[] header) throws IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		// ����һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		
		for (int l = 0; l < sheetNames.length; l++) {
//		for (int l = 0; l < 1; l++) {
			
			// ��webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
			HSSFSheet sheet = wb.createSheet(sheetNames[l]);
			
			// ��sheet����ӱ�ͷ��1��
			HSSFRow row = sheet.createRow(0);
			
			// ������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			// ���ñ�ͷ
			List<String> excelHead = getExcelHead(header);
			
			HSSFCell cell = null;
			
			// excelͷ
			for (int i = 0; i < excelHead.size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(excelHead.get(i));
				cell.setCellStyle(style);
				// �����п�
				sheet.setColumnWidth(i, excelHead.get(i).getBytes().length * 256);
			}
			
			// д������
			List<String[]> dataList = dataAll.get(l).getContents();
			for (int i = 0; i < dataList.size(); i++) {
				row = sheet.createRow(i + 1);
				String[] temp = dataList.get(i);
				
				// ������Ԫ�񣬲�����ֵ
				int j = 0;
				
				for (int k = 0; k < temp.length; k++) {
					insertCell(row, j++, temp[k]);
				}
			}
		}
		
		
		wb.write(out);
		
		return out.toByteArray();
	}
	
	

	/**
	 * ��Ԫ����������
	 * 
	 * @param row
	 * @param i
	 * @param obj
	 */
	private static void insertCell(HSSFRow row, int i, Object obj) {
		if (obj == null) {
			row.createCell(i).setCellValue("");
		} else {
			row.createCell(i).setCellValue(obj.toString());
		}

	}

	/**
	 * ͷ��������Ϣ����
	 * 
	 * @return
	 */
	private static List<String> getExcelHead(String[] header) {
		List<String> result = new ArrayList<String>();

		for (int i = 0; i < header.length; i++) {
			result.add(header[i]);
		}

		return result;
	}
	
	
	
	public static HSSFWorkbook getHSSFWorkbook(List<SheetContent> dataAll, String[] sheetNames,
			String[] header) throws IOException {
		// ����һ��webbook����Ӧһ��Excel�ļ�
			HSSFWorkbook wb = new HSSFWorkbook();
			
			for (int l = 0; l < sheetNames.length; l++) {
//				for (int l = 0; l < 1; l++) {
				
				// ��webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
				HSSFSheet sheet = wb.createSheet(sheetNames[l]);
				
				// ��sheet����ӱ�ͷ��1��
				HSSFRow row = sheet.createRow(0);
				
				// ������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				
				// ���ñ�ͷ
				List<String> excelHead = getExcelHead(header);
				
				HSSFCell cell = null;
				
				// excelͷ
				for (int i = 0; i < excelHead.size(); i++) {
					cell = row.createCell(i);
					cell.setCellValue(excelHead.get(i));
					cell.setCellStyle(style);
					// �����п�
					sheet.setColumnWidth(i, excelHead.get(i).getBytes().length * 256);
				}
				
				// д������
				List<String[]> dataList = dataAll.get(l).getContents();
				for (int i = 0; i < dataList.size(); i++) {
					row = sheet.createRow(i + 1);
					String[] temp = dataList.get(i);
					
					// ������Ԫ�񣬲�����ֵ
					int j = 0;
					
					for (int k = 0; k < temp.length; k++) {
						insertCell(row, j++, temp[k]);
					}
				}
			}
			return wb;
	}
	

}
