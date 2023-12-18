package com.servlet;
import com.jdbc.pay;
import com.jdbc.logtime;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "View2", value = "/View2")
public class View2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html;charset=utf-8");
        String goal=request.getParameter("goal");
        if(goal.equals("log")){
            logtime log=new logtime();
            ArrayList<String[]> loglist=log.loglist();
            request.setAttribute("goal","log");
            request.setAttribute("loglist",loglist);
            request.getRequestDispatcher("manage-page.jsp").include(request,response);
        }
        if(goal.equals("pay")){
            pay p=new pay();
            ArrayList<pay> paylist=p.paylist();
            request.setAttribute("goal","pay");
            request.setAttribute("paylist",paylist);
            request.getRequestDispatcher("manage-page.jsp").include(request,response);
        }
        if(goal.equals("cart")){
            Cookie[] cookies=request.getCookies();
            String payer=null;
            for(int i=0;cookies!=null && i<cookies.length;i++)
            {
                if("name".equals(cookies[i].getName())){
                    payer=cookies[i].getValue();
                }
            }
            if(payer==null){response.getWriter().print("do not find payer");}
            pay p=new pay();
            ArrayList<pay> cartlist=p.cartlist(payer);
            request.setAttribute("payer",payer);
            request.setAttribute("cartlist",cartlist);
            request.getRequestDispatcher("cart.jsp").include(request,response);
        }
    }
}
