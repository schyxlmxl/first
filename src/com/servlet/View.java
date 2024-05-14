package com.servlet;

import com.jdbc.goods;
import com.jdbc.goodsserver;
import com.jdbc.logtime;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "View", value = "/View")
public class View extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{doPost(request,response);}catch (Exception e){e.printStackTrace();}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        String name=null;
        //check whether the user is super
        String is_super=null;
        String is_seller=null;
        Cookie[] cookies=request.getCookies();
        for(int i=0;cookies!=null && i<cookies.length;i++) {
            if ("name".equals(cookies[i].getName())) {
                name = cookies[i].getValue();
            }
            if ("is_super".equals(cookies[i].getName())) {
                is_super = cookies[i].getValue();
            }
            if ("is_seller".equals(cookies[i].getName())) {
                is_seller = cookies[i].getValue();

            }
        }
        //get the goods-list
        String sort= (String) request.getParameter("sort");
        goodsserver gs=new goodsserver();
        ArrayList<goods> goods=gs.goodslist(sort);
        request.setAttribute("goods",goods);
        

        //go to the right page
        if(is_super!=null && is_super.equals("t"))
            request.getRequestDispatcher("manage-page.jsp").include(request,response);
        else if(is_seller!=null && is_seller.equals("t"))
            request.getRequestDispatcher("seller-page.jsp").include(request,response);
        else
            request.getRequestDispatcher("first-page.jsp").include(request,response);
    }
}

