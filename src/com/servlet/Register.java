package com.servlet;
import com.jdbc.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        user user=new user();
        user.init(request.getParameterMap());
        try{
            userserver us=new userserver();
            if(us.adduser(user)){
                Cookie ck=new Cookie("name",user.name);
                ck.setMaxAge(60*30);
                ck.setPath("/website");
                response.addCookie(ck);
                String time=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                logtime log=new logtime();
                log.addlog(user.name,time);
                response.getWriter().print("<script language='javascript'>alert('注册成功，点击返回主页');window.location.href='./View'</script>");
            }
            else{
                response.getWriter().print("<script language='javascript'>alert('注册失败，可能用户名重复，请重试');window.location.href='load-page.jsp'</script>");
            }
        }
        catch (Exception e){e.printStackTrace();}
        }
    }

