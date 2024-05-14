package com.servlet;

import com.jdbc.logtime;
import com.jdbc.pay;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Addcart", value = "/Addcart")
public class Addcart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String payer=request.getParameter("payer");
        int id= Integer.parseInt(request.getParameter("id"));
        pay p=new pay();

        logtime log=new logtime();
        String act="addcart";
        String ip=request.getRemoteAddr();
        log.addlog(payer, act,ip);
        if(p.addcart(payer,id)){
            response.getWriter().print("<script language='javascript'>alert('添加成功，点击返回主页');window.location.href='./View'</script>");
        }
        else{
            response.getWriter().print("<script language='javascript'>alert('添加失败，可能购物车已有该商品');window.location.href='./View'</script>");
        }
    }
}
