package com.servlet;

import com.jdbc.logtime;
import com.jdbc.user;
import com.jdbc.userserver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SellerAccount", value = "/SellerAccount")
public class SellerAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        user user=new user();
        user.init2(request.getParameterMap());

        String goal = request.getParameter("goal");
        if(goal.equals("register")){
            userserver us=new userserver();
            if(us.addseller(user)) {
                logtime log = new logtime();
                String act = "register";
                String ip=request.getRemoteAddr();
                log.addlog(user.name, act,ip);
                response.getWriter().print("<script language='javascript'>alert('注册成功，点击返回管理页');window.location.href='./View'</script>");
            }
            else{
                response.getWriter().print("<script language='javascript'>alert('注册失败，可能用户名重复，请重试');window.location.href='loadseller-page.jsp'</script>");
            }
        }
        else{
            userserver us=new userserver();
            user user1=null;
            user1=us.searchuser(user.name);
            if(user1==null){
                response.getWriter().print("<script language='javascript'>alert('账号不存在，请重试');window.location.href='loadseller-page.jsp'</script>");
            }
            else if(user1.is_super!=2){
                response.getWriter().print("<script language='javascript'>alert('非销售账号，请重试');window.location.href='loadseller-page.jsp'</script>");
            }
            if(us.deleteuser(user.name)) {
                if (user.password.equals("delete")) {
                    logtime log = new logtime();
                    String act = "deleteaccount";
                    String ip=request.getRemoteAddr();
                    log.addlog(user.name, act,ip);
                    response.getWriter().print("<script language='javascript'>alert('注销成功，点击返回管理页');window.location.href='./View'</script>");
                }
                else {
                    if (us.addseller(user)) {
                        logtime log = new logtime();
                        String act = "updateaccount";
                        String ip=request.getRemoteAddr();
                        log.addlog(user.name, act,ip);
                        response.getWriter().print("<script language='javascript'>alert('修改成功，点击返回管理页');window.location.href='./View'</script>");
                    } else {
                        response.getWriter().print("<script language='javascript'>alert('未知原因修改失败，请重试');window.location.href='loadseller-page.jsp'</script>");
                    }
                }
            }
            else{
                response.getWriter().print("<script language='javascript'>alert('未知原因修改失败，请重试');window.location.href='loadseller-page.jsp'</script>");
            }
        }
    }
}
