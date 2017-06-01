package com.ty.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.util.PictureCheckCode;
  
public class PictureCheckCodeServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    @Override  
    protected void doGet(HttpServletRequest reqeust,  
            HttpServletResponse response) throws ServletException, IOException {  
        this.doPost(reqeust, response);  
    }  
  
    protected void doPost(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        // 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        // 禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        PictureCheckCode instance = new PictureCheckCode();  
        HttpSession session=request.getSession(true); 
        session.removeAttribute("yzm");
        session.setAttribute("yzm", instance.getCode()); 
        System.out.println(instance.getCode());
        //session.invalidate();
        
        instance.write(response.getOutputStream());  
    }  
}  