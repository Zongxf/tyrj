package com.ty.dy;

 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

 public class PreviewDemo {

     public static String convertExceltoHtml(String path,String title) throws IOException,ParserConfigurationException, TransformerException,InvalidFormatException {
         HSSFWorkbook workBook = null;
         String content = null;
         StringWriter writer = null;
         File excelFile = new File(path);
         InputStream is = new FileInputStream(excelFile);;
         //判断Excel文件是2003版还是2007版
         String suffix = path.substring(path.lastIndexOf("."));
         if(suffix.equals(".xlsx")){
             //将07版转化为03版
            /* Xssf2Hssf xlsx2xls = new Xssf2Hssf();
             XSSFWorkbook xSSFWorkbook = new XSSFWorkbook(is);
             workBook = new HSSFWorkbook();
             xlsx2xls.transformXSSF(xSSFWorkbook, workBook);*/
             
         }else{
             workBook = new HSSFWorkbook(is);
        }
         try {
             ExcelToHtmlConverter converter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
             converter.setOutputColumnHeaders(false);// 不显示列的表头
             converter.setOutputRowNumbers(false);// 不显示行的表头
            converter.processWorkbook(workBook);
 
             writer = new StringWriter();
             Transformer serializer = TransformerFactory.newInstance().newTransformer();
             serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
             serializer.setOutputProperty(OutputKeys.INDENT, "yes");
             serializer.setOutputProperty(OutputKeys.METHOD, "html");
             serializer.transform(new DOMSource(converter.getDocument()),
                    new StreamResult(writer));
 
             content = writer.toString();
             int start = content.indexOf("<h2>")+4;
             int end = content.indexOf("</h2>");
             String ss = content.substring(start, end);
             content = content.replace(ss, title);
             System.out.println(content);
             
             writer.close();
         } finally {
             try {
                 if (is != null) {
                     is.close();
                 }
                 if (writer != null) {
                     writer.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         return content;
     }
 
 public static void main(String[] args) throws InvalidFormatException, IOException, ParserConfigurationException, TransformerException {
	 PreviewDemo c = new PreviewDemo();
	 c.convertExceltoHtml("D:/uploadFile/存款明细20170522041625.xls","存款统计表"); 
	 
}

 }