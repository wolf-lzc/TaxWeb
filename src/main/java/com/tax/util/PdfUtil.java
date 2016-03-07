package com.tax.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public class PdfUtil {
	
	
	public static final String DEST = "D://test.pdf";
	
	/**����PDF�ܷ�����
	 * add by lzc     date: 2016��2��22��
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void test() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		
		PdfWriter.getInstance(document, new FileOutputStream("D://test.pdf"));
		
		document.open();
		
		document.add(new Paragraph("helloworld"));
		
		document.close();
		
		
	}
	
	
	public static void test2() throws FileNotFoundException, DocumentException {
		//ҳ���С  
		Rectangle rect = new Rectangle(PageSize.B5.rotate());  
		//ҳ�汳��ɫ  
		rect.setBackgroundColor(BaseColor.ORANGE);  
		  
		Document doc = new Document(rect);  
		  
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D://test.pdf"));  
		  
		//PDF�汾(Ĭ��1.4)  
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
		
		
//		// ��������Ϊ��"World"  
//		writer.setEncryption("Hello".getBytes(), "World".getBytes(),  
//		        PdfWriter.ALLOW_SCREENREADERS,  
//		        PdfWriter.STANDARD_ENCRYPTION_128); 
		  
		//�ĵ�����  
		doc.addTitle("Title@sample");  
		doc.addAuthor("Author@rensanning");  
		doc.addSubject("Subject@iText sample");  
		doc.addKeywords("Keywords@iText");  
		doc.addCreator("Creator@iText");  
		  
		//ҳ�߿հ�  
		doc.setMargins(10, 20, 30, 40);  
		  
		doc.open();  
		doc.add(new Paragraph("Hello World")); 
		doc.close();
	}
	
	
	
	
	 public static void createPdf(String dest) throws IOException, DocumentException {
		 Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(dest));
	        document.open();
	        Paragraph p = new Paragraph("This line will be underlined with a dotted line.");
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        p.add(dottedline);
	        document.add(p);
	        
	        
	        
	        
	        Paragraph p2 = new Paragraph("This line will be underlined with a dotted22222 line.");
	        document.add(p2);
	        document.close();
	    }
	
	
	
	public static void table() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document,  new FileOutputStream("D://test.pdf"));
		document.open();
		 Paragraph p1 = new Paragraph("This line will be underlined with a dotted line.");
	        DottedLineSeparator dottedline1 = new DottedLineSeparator();
	        dottedline1.setOffset(-2);
	        dottedline1.setGap(2f);
	        p1.add(dottedline1);
	        document.add(p1);
		
		PdfPTable table = new PdfPTable(8);
		for (int  i = 0; i < 160; i++) {
			table.addCell("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
			
		}
		table.setSpacingBefore(6f);
		document.add(table);
		
		 Paragraph p = new Paragraph("This line will be underlined with a dotted line.");
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        p.add(dottedline);
	        document.add(p);
		PdfPTable table2 = new PdfPTable(7);
		table2.setSpacingBefore(6f);
		for (int  i = 0; i < 14; i++) {
			table2.addCell("111111111111");
			
		}
		document.add(table2);
		document.close();
		
		
	}
	
	
	
	
	
	public static void main(String[] args) throws DocumentException, IOException {
		File file = new File("D://test.pdf");
		file.getParentFile().mkdirs();
		table();
//		File file = new File(DEST);
//        file.getParentFile().mkdirs();
//        createPdf(DEST);
		System.out.println(":)");
	}

}
