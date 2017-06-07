package com.ty.action;
/**
 * 存款明细导出详细excel模板
 * 
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.ty.bean.Chrybb;
import com.ty.bean.Ckmx;
import com.ty.bean.Cktj;
import com.ty.bean.Gsbkrybb;
import com.ty.bean.Jcrybb;
import com.ty.bean.Jcxfmx;
import com.ty.bean.Khrybb;
import com.ty.bean.Qkmx;
import com.ty.bean.Xfmx;
import com.ty.bean.Xzyytj;
import com.ty.dy.Download;
import com.ty.dy.ExportExcel;
import com.ty.framework.action.BaseAction;

@At("/export_new")
@InjectName()
@IocBean
@Filters
public class ExportAction_new extends BaseAction{

	 private Map<String, Object> resMap;
	    @Inject
	    private NutDao dao;
        private Download download = new Download();
        @Inject
        QueryExcelAction queryExcelAction;
        
    	public String fileName ="";
    	public List<Ckmx> list1=new ArrayList<Ckmx>();
    	public List<Qkmx> list2=new ArrayList<Qkmx>();
    	public List<Xfmx> list3=new ArrayList<Xfmx>();
    	public List<Jcxfmx> list4=new ArrayList<Jcxfmx>();
    	public List<Cktj> list5=new ArrayList<Cktj>();
    	public List<Xzyytj> list6=new ArrayList<Xzyytj>();
    	public List<Khrybb> list7=new ArrayList<Khrybb>();
    	public List<Chrybb> list8=new ArrayList<Chrybb>();
    	public List<Gsbkrybb> list9=new ArrayList<Gsbkrybb>();
    	public List<Jcrybb> list10=new ArrayList<Jcrybb>();
        public String [][] aa=new String[2][2];
	    public ExportAction_new() {
	        resMap = new HashMap<String, Object>();

	    }
	    
	    @At()
		@Ok("json")
		@Filters
		public Map<String, Object> exportCkmx(String data,String title, String count,HttpServletRequest req) throws Exception
		{   
	    	//初始化列表
	        HSSFWorkbook wb = new HSSFWorkbook();  
	  	    HSSFSheet sheet = wb.createSheet();  
	    	ExportExcel exportExcel = new ExportExcel(wb, sheet);
	    	//获取数据
             String [] a;
             a=data.split("&");
             String [] key = new String[a.length];
             String [] value = new String[a.length];
             
             for(int i = 0;i<a.length;i++){
            	  key[i] = a[i].substring(0,a[i].indexOf(":"));
            	  value[i]  = a[i].substring(a[i].indexOf(":")+1);
             }
             Map<String,String> map = new HashMap<String,String>();
             for(int i = 0;i<key.length;i++){
            	 map.put(key[i], value[i]);
             }
            
             String path = "D:/uploadFile/";
             //获取标题
             String [] tt;
             tt = title.split("&");
             
             if(tt[0].equals("存款明细")){
            	 list1 = queryExcelAction.queryCkmxJson(map);
                 aa = new String[list1.size()][tt.length];
                 for(int i = 0;i<list1.size();i++){
                	 String t = list1.get(i).getBh();
                	 String b = list1.get(i).getBm();
                	 String x = list1.get(i).getXm();
                	 String c = list1.get(i).getCkje();
                	 String s = list1.get(i).getSyje();
                	 String ck = list1.get(i).getCkrq();
                	 String lx = list1.get(i).getLx();
                		 aa[i][0] = t;
                		 aa[i][1] = x;
                		 aa[i][2] = b;
                		 aa[i][3] = c;
                		 aa[i][4] = s;
                		 aa[i][5] = ck;
                		 if(lx.equals("1")){
                    		 lx="现金存款";
                    	 }else if(lx.equals("2")){
                    		 lx="补助存款";
                    	 }
                		 aa[i][6] = lx;
                 }
             }else if(tt[0].equals("取款明细")){
            	 list2 = queryExcelAction.queryQkmxJson(map);
                 aa = new String[list2.size()][tt.length];
                 for(int i = 0;i<list2.size();i++){
                	 String t = list2.get(i).getBh();
                	 String b = list2.get(i).getBm();
                	 String x = list2.get(i).getXm();
                	 String c = list2.get(i).getQkje();
                	 String s = list2.get(i).getSyje();
                	 String ck = list2.get(i).getQksj();
                	 String qxj = list2.get(i).getQxj();
                	 String qbt = list2.get(i).getQbt();
                		 aa[i][0] = t;
                		 aa[i][1] = x;
                		 aa[i][2] = b;
                		 aa[i][3] = c;
                		 aa[i][4] = qxj;
                		 aa[i][5] = qbt;
                		 aa[i][6] = s;
                		 aa[i][7] = ck;
                 }
             }else if(tt[0].equals("消费明细")){
            	 list3 = queryExcelAction.queryXfmxJson(map);
                 aa = new String[list3.size()][tt.length];
                 for(int i = 0;i<list3.size();i++){
                	 String t = list3.get(i).getRybh();
                	 String b = list3.get(i).getBm();
                	 String x = list3.get(i).getXm();
                	 String c = list3.get(i).getXfje();
                	 String s = list3.get(i).getSyje();
                	 String ck = list3.get(i).getXfsj();
                	 String qxj = list3.get(i).getXfjh();
                		 aa[i][0] = t;
                		 aa[i][1] = x;
                		 aa[i][2] = b;
                		 aa[i][3] = c;
                		 aa[i][4] = s;
                		 aa[i][5] = ck;
                		 aa[i][6] = qxj;
                 }
             }else if(tt[0].equals("计次消费明细")){
            	 list4 = queryExcelAction.queryXfjcmxJson(map);
                 aa = new String[list4.size()][tt.length];
                 for(int i = 0;i<list4.size();i++){
                	 String t = list4.get(i).getRybh();
                	 String b = list4.get(i).getBm();
                	 String x = list4.get(i).getXm();
                	 String c = list4.get(i).getCs();
                	 String ck = list4.get(i).getXfsj();
                	 String qxj = list4.get(i).getXfjh();
                		 aa[i][0] = t;
                		 aa[i][1] = x;
                		 aa[i][2] = b;
                		 aa[i][3] = c;
                		 aa[i][5] = ck;
                		 aa[i][6] = qxj;
                 }
             }else if(tt[0].equals("存款统计")){
            	 list5 = queryExcelAction.queryCktjJson(map);
                 aa = new String[list5.size()][tt.length];
                 for(int i = 0;i<list5.size();i++){
                	 String t = list5.get(i).getLx();
                	 if(t.equals("1")){
                		 t="现金存款";
                	 }else if(t.equals("2")){
                		 t="补助存款";
                	 }
                	 String b = list5.get(i).getCkje();
                	 String x = list5.get(i).getCzy();
                		 aa[i][0] = t;
                		 aa[i][1] = b;
                		 aa[i][2] = x;
                 }
             }else if(tt[0].equals("小组营业统计")){
            	 list6 = queryExcelAction.queryXzyytjJson(map);
                 aa = new String[list6.size()][tt.length];
                 for(int i = 0;i<list6.size();i++){
                	 String t = list6.get(i).getDep_name();
                	 String b = list6.get(i).getLname();
                	 String x = list6.get(i).getSummoney();
                	 String xx = list6.get(i).getZrs();
                		 aa[i][0] = t;
                		 aa[i][1] = b;
                		 aa[i][2] = x;
                		 aa[i][3] = xx;
                 }
             }else if(tt[0].equals("开户人员报表")){
            	 list7 = queryExcelAction.queryKhrybbJson(map);
                 aa = new String[list7.size()][tt.length];
                 for(int i = 0;i<list7.size();i++){
                	 String t = list7.get(i).getRybh();
                	 String b = list7.get(i).getBm();
                	 String x = list7.get(i).getXm();
                	 String c = list7.get(i).getLx();
                	 if(c.equals("0")){
                		 c="普通员工（默认）";
                	 }
                	 String ck = list7.get(i).getKhsj();
                	 String qxj = list7.get(i).getKh();
                	 String czy = list7.get(i).getCzy();
                		 aa[i][0] = t;
                		 aa[i][1] = x;
                		 aa[i][2] = b;
                		 aa[i][3] = c;
                		 aa[i][4] = ck;
                		 aa[i][5] = qxj;
                		 aa[i][6] = czy;
                 }
             }else if(tt[0].equals("撤户人员报表")){
            	 list8 = queryExcelAction.queryChrybbJson(map);
                 aa = new String[list8.size()][tt.length];
                 for(int i = 0;i<list8.size();i++){
                	 String t = list8.get(i).getRybh();
                	 String b = list8.get(i).getBm();
                	 String x = list8.get(i).getXm();
                	 String c = list8.get(i).getTxj();
                	 String ck = list8.get(i).getTbt();
                	 String qxj = list8.get(i).getChsj();
                	 String czy = list8.get(i).getCzy();
                	 String cIP = list8.get(i).getIp();
                		 aa[i][0] = t;
                		 aa[i][1] = x;
                		 aa[i][2] = b;
                		 aa[i][3] = c;
                		 aa[i][4] = ck;
                		 aa[i][5] = qxj;
                		 aa[i][6] = czy;
                		 aa[i][7] = cIP;
                 }
             }else if(tt[0].equals("挂失办卡人员报表")){
            	 list9 = queryExcelAction.queryGsbkrybbJson(map);
                 aa = new String[list9.size()][tt.length];
                 for(int i = 0;i<list9.size();i++){
                	 String t = list9.get(i).getRybh();
                	 String b = list9.get(i).getBm();
                	 String x = list9.get(i).getXm();
                	 String c = list9.get(i).getBksj();
                	 String ck = list9.get(i).getSyje();
                	 String qxj = list9.get(i).getKh();
                	
                		 aa[i][0] = t;
                		 aa[i][1] = x;
                		 aa[i][2] = b;
                		 aa[i][3] = c;
                		 aa[i][4] = ck;
                		 aa[i][5] = qxj;
                 }
             }else if(tt[0].equals("纠错人员报表")){
            	 list10 = queryExcelAction.queryJcrybbJson(map);
                 aa = new String[list10.size()][tt.length];
                 for(int i = 0;i<list10.size();i++){
                	 String t = list10.get(i).getRybh();
                	 String b = list10.get(i).getBm();
                	 String x = list10.get(i).getXm();
                	 String c = list10.get(i).getBh();
                	 String ck = list10.get(i).getJcje();
                	 String qxj = list10.get(i).getYsje();
                	 String czy = list10.get(i).getKh();
                	 String cIP = list10.get(i).getJcsj();
                		 aa[i][0] = t;
                		 aa[i][1] = x;
                		 aa[i][2] = b;
                		 aa[i][3] = c;
                		 aa[i][4] = ck;
                		 aa[i][5] = qxj;
                		 aa[i][6] = czy;
                		 aa[i][7] = cIP;
                 }
             }
             
             
             //统计
             String [] tj;
             tj = count.split("&");
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
		         HSSFRow row1 = sheet.createRow(0);  
		         
		         HSSFCell row1Cell = null;  
		         int m = 1;  
		  
		         // 创建不同的列标题  
		         for (int i = 0; i < number; i = i + 1) {  
		             
		             if (i <=  number) {  
		                 row1Cell = row1.createCell(i);  
		                 row1Cell.setCellStyle(cellStyle);  
		                 row1Cell.setCellValue(new HSSFRichTextString(tt[m]  
		                         .toString()));  
		                 m++;  
		             }
		         }
		         if(!"".equals(tj[0])){
		        	 row1Cell = row1.createCell(number);  
	                 row1Cell.setCellStyle(cellStyle);  
	                 row1Cell.setCellValue(new HSSFRichTextString(tj[0])); 
	                 row1Cell = row1.createCell(number+1);  
	                 row1Cell.setCellStyle(cellStyle);  
	                 row1Cell.setCellValue(new HSSFRichTextString(tj[1])); 
		         }
		         //创建内容行
		         HSSFCell rowCell = null;
	        	 HSSFRow row=null;
	        	 if(aa.length>0){
	        		if(list1.size()>0){
	        		 for(int i=0;i<list1.size();i++){
	        			 row = sheet.createRow(i+1); 
			        	 for(int j=0;j<tt.length;j++){
			        		 rowCell = row.createCell(j);  
			                 rowCell.setCellStyle(cellStyle);  
			                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
			        	 }
			        	 
			         }
	        		}else if(list2.size()>0){
		        		 for(int i=0;i<list2.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
		        		}else if(list3.size()>0){
		        		 for(int i=0;i<list3.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
		        		}else if(list4.size()>0){
		        		 for(int i=0;i<list4.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
		        		}else if(list5.size()>0){
		        		 for(int i=0;i<list5.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
		        		}else if(list6.size()>0){
		        		 for(int i=0;i<list6.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
		        		}else if(list7.size()>0){
		        		 for(int i=0;i<list7.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
		        		}else if(list8.size()>0){
		        		 for(int i=0;i<list8.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
		        		}else if(list9.size()>0){
		        		 for(int i=0;i<list9.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
		        		}else if(list10.size()>0){
		        		 for(int i=0;i<list10.size();i++){
		        			 row = sheet.createRow(i+1);
				        	 for(int j=0;j<tt.length;j++){
				        		 rowCell = row.createCell(j);  
				                 rowCell.setCellStyle(cellStyle);  
				                 rowCell.setCellValue(new HSSFRichTextString(aa[i][j]));  
				        	 }
				        	 
				         }
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
