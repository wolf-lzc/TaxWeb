package com.tax.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.tax.common.Global;
import com.tax.model.BO.BalanceSheetContent;
import com.tax.model.BO.BalanceSheetContent.BalanceSheetContentRows;
import com.tax.model.DO.Balancesheet;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public class BalancePdfView extends AbstractPdfView{
	
	
	

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		document.open();
		
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese =  new  Font(baseFontChinese , 12 , Font.NORMAL);
		List<Balancesheet> list =  (List<Balancesheet>) model.get("balance");
		for (Balancesheet balancesheet : list) {
			Paragraph p = new Paragraph(balancesheet.getReportDate() / 100 + "���ʲ�ծ��" ,fontChinese);
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        p.add(dottedline);
	        document.add(p);
			PdfPTable table = new PdfPTable(8);
			
			table.setSpacingBefore(6f);
			addTitle(table, fontChinese);
			addCell(table, balancesheet, fontChinese);
			document.add(table);
			
		}
		document.close();
		
	}

	
	private void addCell(PdfPTable table, Balancesheet balancesheet, Font fontChinese) {
		// TODO Auto-generated method stub
		BalanceSheetContent content = new BalanceSheetContent(balancesheet.getReportContent());
		List<BalanceSheetContentRows> rows = content.getList();
		for (BalanceSheetContentRows balanceSheetContentRows : rows) {
			table.addCell(new Paragraph(balanceSheetContentRows.getAsset(), fontChinese));
			table.addCell(balanceSheetContentRows.getIndex1());
			table.addCell(balanceSheetContentRows.getBalance_begin1());
			table.addCell(balanceSheetContentRows.getBalance_end1());
			table.addCell(new Paragraph(balanceSheetContentRows.getLiabilities(), fontChinese));
			table.addCell(balanceSheetContentRows.getIndex2());
			table.addCell(balanceSheetContentRows.getBalance_begin2());
			table.addCell(balanceSheetContentRows.getBalance_end2());
		}
		
		
	}

	private void addTitle(PdfPTable table, Font fontChinese) {
		// TODO Auto-generated method stub
		table.addCell(new Paragraph(Global.DOWNLOAD_BALANCE_SHEET_ASSET, fontChinese));
		table.addCell(new Paragraph(Global.DOWNLOAD_BALANCE_SHEET_INDEX, fontChinese));
		table.addCell(new Paragraph(Global.DOWNLOAD_BALANCE_SHEET_START, fontChinese));
		table.addCell(new Paragraph(Global.DOWNLOAD_BALANCE_SHEET_END, fontChinese));
		table.addCell(new Paragraph(Global.DOWNLOAD_BALANCE_SHEET_DEBT, fontChinese));
		table.addCell(new Paragraph(Global.DOWNLOAD_BALANCE_SHEET_INDEX, fontChinese));
		table.addCell(new Paragraph(Global.DOWNLOAD_BALANCE_SHEET_START, fontChinese));
		table.addCell(new Paragraph(Global.DOWNLOAD_BALANCE_SHEET_END, fontChinese));
	}

}
