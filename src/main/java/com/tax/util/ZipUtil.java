package com.tax.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.tax.model.BO.HSSFWorkbookWrapper;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
public class ZipUtil {
	
	
	public static void zipExcel(File targetFile, List<HSSFWorkbookWrapper> list){
		if(list.isEmpty()){
			return;
		}
		
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(targetFile));
			for (HSSFWorkbookWrapper hssfWorkbookWrapper : list) {
				ZipEntry entry = new ZipEntry(hssfWorkbookWrapper.getName() + ".xls");
				// 设置压缩包的入口
				zos.putNextEntry(entry);
				hssfWorkbookWrapper.getExcel().write(zos);
				zos.flush();
			}
			zos.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	
	

}
