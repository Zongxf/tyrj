package com.ty.action;
/**
 * 导出前200条数据的excel模板类
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.ty.dy.Download;
import com.ty.dy.ExportExcel;
import com.ty.framework.action.BaseAction;

@At("/export")
@InjectName()
@IocBean
@Filters
public class ExportAction extends BaseAction{

	 private Map<String, Object> resMap;
	    @Inject
	    private NutDao dao;
        private Download download = new Download();
        
        Date now = new Date(); 
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String he = dateFormat.format( now ); 
        
        
    	public String fileName ="";

	    public ExportAction() {
	        resMap = new HashMap<String, Object>();

	    }
	    
	    @At()
		@Ok("json")
		@Filters
		public Map<String, Object> exportCktj(String data,String title, String count,String dqczy,HttpServletRequest req)
		{   
	    	//初始化列表
	        HSSFWorkbook wb = new HSSFWorkbook();  
	  	    HSSFSheet sheet = wb.createSheet();  
	    	ExportExcel exportExcel = new ExportExcel(wb, sheet);
	    	//获取数据
             String [] aa;
             aa=data.split("&");
             String path = "D:/uploadFile/";
             //获取标题
             String [] tt;
             tt = title.split("&");
             //统计
             String [] tj;
             tj = count.split("&");
             
             String [] dd;
             dd = dqczy.split("&");
             //获取当前系统时间做文件名
            Date now = new Date(); 
         	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
         	String hehe = dateFormat.format( now ); 
         	
         	fileName = tt[0]+hehe+".xls";
         	
			try
			{
				

				// 计算该报表的列数  
		         int number = tt.length-1;
		         
		         // 给工作表列定义列宽(实际应用自己更改列数)  
		         for (int i = 0; i <= number; i++) {  
		             sheet.setColumnWidth(i, 5000);  
		         } 
		         if(!"".equals(tj[0])){
		        	 sheet.setColumnWidth(number+1, 5000);  
		        	 sheet.setColumnWidth(number+2, 5000);  
		         }
		         // 创建单元格样式  
		         HSSFCellStyle cellStyle = wb.createCellStyle();  
		         
		         // 指定单元格居中对齐  
		         cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		  
		         // 指定单元格垂直居中对齐  
		         cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		          
		         cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单无格的边框为粗体  
		         cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．  
		         cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		         cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);  
		         cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		         cellStyle.setRightBorderColor(HSSFColor.BLACK.index);  
		         cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		         cellStyle.setTopBorderColor(HSSFColor.BLACK.index);  
		  
		         // 指定当单元格内容显示不下时自动换行  
		         cellStyle.setWrapText(true);  
		         
		         // 设置单元格字体  
		         HSSFFont font = wb.createFont();  
		         font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		         font.setFontName("宋体");  
		         font.setFontHeight((short) 200);  
		         cellStyle.setFont(font);  
		         HSSFRow row0 = sheet.createRow(0);  
		         
		         HSSFCell row0Cell = null;  
		         int m = 1;  
		  
		         row0Cell = row0.createCell(0);  
                 row0Cell.setCellStyle(cellStyle);  
                 row0Cell.setCellValue(new HSSFRichTextString(tt[0]));  
                 
                 HSSFRow row1 = sheet.createRow(1);  
		         HSSFCell row1Cell = null;  
		         row1Cell = row1.createCell(0);  
                 row1Cell.setCellStyle(cellStyle);  
                 row1Cell.setCellValue(new HSSFRichTextString(dd[0]+dd[1])); 
                 
                 row1Cell = row1.createCell(1);  
                 row1Cell.setCellStyle(cellStyle);  
                 row1Cell.setCellValue(new HSSFRichTextString("时间：")); 
                 row1Cell = row1.createCell(2);  
                 row1Cell.setCellStyle(cellStyle);  
                 row1Cell.setCellValue(new HSSFRichTextString(he)); 
                 
                 if(!"".equals(tj[0])){
		        	 row1Cell = row1.createCell(1);  
	                 row1Cell.setCellStyle(cellStyle);  
	                 row1Cell.setCellValue(new HSSFRichTextString(tj[0])); 
	                 row1Cell = row1.createCell(2);  
	                 row1Cell.setCellStyle(cellStyle);  
	                 row1Cell.setCellValue(new HSSFRichTextString(tj[1])); 
		         }
                 
                 HSSFRow row2 = sheet.createRow(2);  
		         HSSFCell row2Cell = null;  
		         // 创建不同的列标题  
		         for (int i = 0; i < number; i = i + 1) {  
		             
		             if (i <=  number) {  
		                 row2Cell = row2.createCell(i);  
		                 row2Cell.setCellStyle(cellStyle);  
		                 row2Cell.setCellValue(new HSSFRichTextString(tt[m]  
		                         .toString()));  
		                 m++;  
		             }
		         }
		        
		         //创建内容行
		         HSSFCell rowCell = null;
	        	 HSSFRow row=null;
	        	// System.out.println(aa);
	        	 if(!"".equals(aa[0])){
	        		 for(int i=0;i<aa.length;i++){
			        	 
			        	 if(i%number==0){
			        	  row = sheet.createRow(i/number+3);
			        	 }
			        	 rowCell = row.createCell(i%number);  
		                 rowCell.setCellStyle(cellStyle);  
		                 rowCell.setCellValue(new HSSFRichTextString(aa[i]));  
			        	 
			         }
	        	 }
		         
		         
		         exportExcel.outputExcel(path+fileName);  
					resMap.put("success", true);
				

			} catch (Exception e)
			{
				e.printStackTrace();
				resMap.put("success", false);
			}
			resMap.put("fileName", fileName);
			return resMap;
		}
	    
	    
	    @At()
		@Ok("json")
		@Filters
	    public void downloadFile(HttpServletRequest request,    
	            HttpServletResponse response) throws Exception {   
	             //下载的文件名（前提：下载的文件需要存放在服务器的位置上，这里是 WebRoot/uploadFile 中）  
	    	
	    	/*String file = request.getQueryString();
	    	String name = file.substring(file.indexOf("=")+1);
	    	//System.out.println(name);
*/			download.downloadFile(request, response, fileName);  
	    }  
}
