package com.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.logtime;
import com.jdbc.userserver;
import  com.jdbc.user;
@WebServlet(name = "DeleteAccount", value = "/DeleteAccount")
public class DeleteAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies=request.getCookies();
        String name=null;
        for(int i=0;cookies!=null && i<cookies.length;i++)
        {
            if("name".equals(cookies[i].getName())){
                name=cookies[i].getValue();
            }
        }
        userserver us=new userserver();
        if(us.deleteuser(name)){
            Cookie[] cookie=request.getCookies();
            Cookie ck=new Cookie("name","");
            ck.setMaxAge(-1);
            ck.setPath("/website");
            response.addCookie(ck);

            logtime log=new logtime();
            String act="deleteaccount";
            String ip=request.getRemoteAddr();
            log.addlog(name, act,ip);
            response.getWriter().print("<script language='javascript'>alert('账号已注销，即将返回主页');window.location.href='./View'</script>");
        }
        else{
            response.getWriter().print("<script language='javascript'>alert('注销失败，请重试');window.location.href='./View'</script>");
        }
    }
}
