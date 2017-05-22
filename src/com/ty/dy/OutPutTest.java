package com.ty.dy;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 导出excel测试
 * */
public class OutPutTest {
	 private static HSSFWorkbook wb = new HSSFWorkbook();  
	  
     private static HSSFSheet sheet = wb.createSheet();  
     public static void main(String[] args) { 
    	 ExportExcel exportExcel = new ExportExcel(wb, sheet);
    	 
    	  // 创建列标头LIST  
         List fialList = new ArrayList();  
         fialList.add("姓名");
         fialList.add("年龄");
         fialList.add("性别");
         fialList.add("电话");
         
         // 计算该报表的列数  
         int number = fialList.size()-1;
         
         // 给工作表列定义列宽(实际应用自己更改列数)  
         for (int i = 0; i <= number; i++) {  
             sheet.setColumnWidth(i, 3000);  
         }  
         
         // 创建单元格样式  
         HSSFCellStyle cellStyle = wb.createCellStyle();  
         
         // 指定单元格居中对齐  
         cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
  
         // 指定单元格垂直居中对齐  
         cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
  
         // 指定当单元格内容显示不下时自动换行  
         cellStyle.setWrapText(true);  
         
         // 设置单元格字体  
         HSSFFont font = wb.createFont();  
         font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
         font.setFontName("宋体");  
         font.setFontHeight((short) 200);  
         cellStyle.setFont(font);  
         
         // 创建报表头部  
         exportExcel.createNormalHead("测试数据", number);  
         
         HSSFRow row3 = sheet.createRow(1);  
         
         HSSFCell row3Cell = null;  
         int m = 0;  
         int n = 0;  
  
         // 创建不同的LIST的列标题  
         for (int i = 0; i <= number; i = i + 1) {  
  
             if (i <=  fialList.size()) {  
                 row3Cell = row3.createCell(i);  
                 row3Cell.setCellStyle(cellStyle);  
                 row3Cell.setCellValue(new HSSFRichTextString(fialList.get(m)  
                         .toString()));  
                 m++;  
             }
         }
//         // 设置行高  
//         row3.setHeight((short) 800); 
         exportExcel.outputExcel("c:\\test.xls");  
         
     }
}
     
