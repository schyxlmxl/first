package com.servlet;

import com.jdbc.goods;
import com.jdbc.goodsserver;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Search", value = "/Search")
public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html;charset=utf-8");
        //check whether the user is super
        String is_super=null;
        Cookie[] cookies=request.getCookies();
        for(int i=0;cookies!=null && i<cookies.length;i++)
        {
            if("is_super".equals(cookies[i].getName())){
                is_super=cookies[i].getValue();
            }
        }
        //get the goods-list
        String name= (String) request.getParameter("name");
        goodsserver gs=new goodsserver();
        ArrayList<goods> goods=gs.searchgoods(name);
        request.setAttribute("goods",goods);
        //go to the right page
        if(is_super!=null && is_super.equals("t"))
            request.getRequestDispatcher("manage-page.jsp").include(request,response);
        else
            request.getRequestDispatcher("first-page.jsp").include(request,response);
    }
}
