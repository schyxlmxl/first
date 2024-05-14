package com.servlet;

import com.jdbc.logtime;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies=request.getCookies();
        String name=null;
        
        for(int i=0;cookies!=null && i<cookies.length;i++) {
            if ("name".equals(cookies[i].getName())) {
                name = cookies[i].getValue();
            }
        }
        logtime log=new logtime();
        String act="logout";
        String ip=request.getRemoteAddr();
        log.addlog(name, act,ip);

        Cookie ck=new Cookie("name","");
        ck.setMaxAge(-1);
        ck.setPath("/website");
        Cookie ck2=new Cookie("is_super","");
        ck2.setMaxAge(-1);
        ck2.setPath("/website");
        Cookie ck3=new Cookie("is_seller","");
        ck3.setMaxAge(-1);
        ck3.setPath("/website");
        response.addCookie(ck);
        response.addCookie(ck2);
        response.addCookie(ck3);

        response.getWriter().print("<script language='javascript'>alert('已退出登录，前往主页面');window.location.href='./View'</script>");
    }
}
