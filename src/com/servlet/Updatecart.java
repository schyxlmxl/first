package com.servlet;

import com.jdbc.pay;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Updatecart", value = "/Updatecart")
public class Updatecart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html;charset=utf-8");
        String payer=request.getParameter("payer");
        int id= Integer.parseInt(request.getParameter("id"));
        int number= Integer.parseInt(request.getParameter("number"));
        pay p=new pay();
        if(p.updatecart(payer,id,number)){
            response.getWriter().print("<script language='javascript'>alert('修改成功，点击返回购物车');window.location.href='./View2?goal=cart'</script>");
        }
        else{
            response.getWriter().print("<script language='javascript'>alert('修改失败，请检查商品余量和购买数目');window.location.href='./View2?goal=cart'</script>");
        }
    }
}
