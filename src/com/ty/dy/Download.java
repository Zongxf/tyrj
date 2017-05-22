package com.ty.dy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.nutz.dao.entity.annotation.Comment;
  
/** 
 * 下载模版工具类 
*     
* 类名称：downLoadExcel    
* 类描述：    
* 创建人：xiaofei    
* 创建时间：2017-5-20 
* 修改人：xiaofei   
* 修改时间：2017-5-20
* 修改备注：    
* @version     
* 
 */  
public class Download {  
      
    //下载模版工具类  
    public void downloadFile(HttpServletRequest request,    
            HttpServletResponse response,String fileName) throws Exception {    
        response.setContentType("text/html;charset=UTF-8");     
        BufferedInputStream in = null;    
        BufferedOutputStream out = null;    
        request.setCharacterEncoding("UTF-8");    
        String rootpath = "D:/uploadFile/";
        response.reset();
        try {    
            File f = new File(rootpath+ fileName);    
            response.setContentType("application/x-excel");    
            response.setCharacterEncoding("UTF-8");    
            response.setHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));    
            response.setHeader("Content-Length",String.valueOf(f.length()));    
            in = new BufferedInputStream(new FileInputStream(f));    
            out = new BufferedOutputStream(response.getOutputStream());    
            byte[] data = new byte[1024];    
            int len = 0;    
            while (-1 != (len=in.read(data, 0, data.length))) {    
                out.write(data, 0, len);    
            }    
        } catch (Exception e) {    
            e.printStackTrace();    
        } finally {    
            if (in != null) {   
                in.close(); 
                in = null;
            }    
            if (out != null) { 
                out.close(); 
                out = null;
                
            }    
        }    
        
    }  
}  