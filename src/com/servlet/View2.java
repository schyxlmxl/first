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
        String is_super=null;
        String is_seller=null;
        Cookie[] cookies=request.getCookies();
        for(int i=0;cookies!=null && i<cookies.length;i++)
        {
            if("is_super".equals(cookies[i].getName())){
                is_super=cookies[i].getValue();
            }
            if("is_seller".equals(cookies[i].getName())){
                is_seller=cookies[i].getValue();
            }
        }
        if(is_super!=null && is_super.equals("t")) {
            String name=null;
            for(int i=0;cookies!=null && i<cookies.length;i++) {
                if ("name".equals(cookies[i].getName())) {
                    name= cookies[i].getValue();
                }
            }
            logtime log=new logtime();
            String act="log";
            String ip=request.getRemoteAddr();
            log.addlog(name, act,ip);
            String goal = request.getParameter("goal");

            if (goal.equals("log")) {
                ArrayList<String[]> loglist = log.loglist();
                request.setAttribute("goal", "log");
                request.setAttribute("loglist", loglist);
                request.getRequestDispatcher("manage-page.jsp").include(request, response);
            }
            if (goal.equals("pay")) {
                pay p = new pay();
                ArrayList<pay> paylist = p.paylist();
                request.setAttribute("goal", "pay");
                request.setAttribute("paylist", paylist);
                request.getRequestDispatcher("manage-page.jsp").include(request, response);
            }
        }
        else if(is_seller!=null && is_seller.equals("t")){
            String goal = request.getParameter("goal");
            String seller = null;
            for (int i = 0; cookies != null && i < cookies.length; i++) {
                if ("name".equals(cookies[i].getName())) {
                    seller = cookies[i].getValue();
                }
            }
            logtime log0=new logtime();
            String act="check";
            String ip=request.getRemoteAddr();
            log0.addlog(seller, act,ip);

            if (goal.equals("log")) {

                pay log=new pay();
                ArrayList<pay> loglist = log.cartlistforseller(seller);
                request.setAttribute("goal", "log");
                request.setAttribute("loglist", loglist);
                request.getRequestDispatcher("seller-page.jsp").include(request, response);
            }
            if (goal.equals("pay")) {
                pay p = new pay();
                ArrayList<pay> paylist = p.paylistforseller(seller);
                request.setAttribute("goal", "pay");
                request.setAttribute("paylist", paylist);
                request.getRequestDispatcher("seller-page.jsp").include(request, response);
            }
        }
        else {
            String goal = request.getParameter("goal");
            if (goal.equals("cart")) {
                String payer = null;
                for (int i = 0; cookies != null && i < cookies.length; i++) {
                    if ("name".equals(cookies[i].getName())) {
                        payer = cookies[i].getValue();
                    }
                }
                if (payer == null) {
                    response.getWriter().print("do not find payer");
                }
                pay p = new pay();
                ArrayList<pay> cartlist = p.cartlist(payer);
                request.setAttribute("payer", payer);
                request.setAttribute("cartlist", cartlist);
                request.getRequestDispatcher("cart.jsp").include(request, response);
            }
        }
    }

}
