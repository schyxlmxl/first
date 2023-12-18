package com.servlet;

import com.jdbc.goods;
import com.jdbc.goodsserver;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Deletegoods", value = "/Deletegoods")
public class Deletegoods extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        goodsserver gs=new goodsserver();
        if(gs.deletegoods(Integer.parseInt(request.getParameter("id")))){
            response.getWriter().print("<script language='javascript'>alert('删除成功，返回后台管理');window.location.href='./View?is_super=t'</script>");
        }
        else{
            response.getWriter().print("<script language='javascript'>alert('删除失败，返回后台管理');window.location.href='./View?is_super=t'</script>");
        }
    }
}
