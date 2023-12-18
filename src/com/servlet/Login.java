package com.servlet;
import com.jdbc.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        try {
            userserver us = new userserver();
            user user = us.searchuser(name);

            response.setContentType("text/html;charset=utf-8");
            if (user != null && user.password.equals(password)) {
                Cookie ck=new Cookie("name",user.name);
                ck.setMaxAge(60*30);
                ck.setPath("/website");
                response.addCookie(ck);
                if(user.is_super==1) {
                    Cookie ck2 = new Cookie("is_super", "t");
                    ck2.setPath("/website");
                    response.addCookie(ck2);
                    response.getWriter().print("<script language='javascript'>alert('管理员账号登录成功，前往后台管理');window.location.href='./View'</script>");
                }else{
                    String time=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                    logtime log=new logtime();
                    log.addlog(name,time);
                    response.getWriter().print("<script language='javascript'>window.location.href='./View'</script>");
                }
            } else {
                response.getWriter().print("<script language='javascript'>alert('登录失败，请检测用户名与密码');window.location.href='load-page.jsp'</script>");
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
}
