package com.servlet;

import com.jdbc.*;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Performance", value = "/Performance")
public class Performance extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        String goal = request.getParameter("goal");
        Cookie[] cookies=request.getCookies();
        String name=null;
        for(int i=0;cookies!=null && i<cookies.length;i++) {
            if ("name".equals(cookies[i].getName())) {
                 name= cookies[i].getValue();
            }
        }
        logtime log=new logtime();
        String act="check";
        String ip=request.getRemoteAddr();
        log.addlog(name, act,ip);

        if (goal.equals("seller")) {
            userserver us = new userserver();
            ArrayList<user> userlist = us.userlist();
            for (int i = userlist.size() - 1; i >= 0; i--) {
                if (userlist.get(i).is_super != 2) {
                    userlist.remove(i);
                }
            }
            pay pay = new pay();
            ArrayList<pay> paylist = pay.paylist();
            ArrayList<Integer> profit = new ArrayList<Integer>();
            ArrayList<pay> loglist = new ArrayList<pay>();
            for (int i = 0; i < userlist.size(); i++) {
                int sum = 0;
                for (int j = 0; j < paylist.size(); j++) {
                    if (paylist.get(j).belong.equals(userlist.get(i).name)) {
                        loglist.add(paylist.get(j));
                        sum += paylist.get(j).price * paylist.get(j).number;
                    }
                }
                profit.add(sum);
            }
            request.setAttribute("goal", "seller");
            request.setAttribute("userlist",userlist);
            request.setAttribute("loglist", loglist);
            request.setAttribute("profit", profit);
            request.getRequestDispatcher("manage-page.jsp").include(request, response);
        }
        else if(goal.equals("goods")){
            goodsserver gs=new goodsserver();
            pay pay=new pay();
            ArrayList<pay> paylist=pay.paylist();
            ArrayList<Integer>profit =new ArrayList<>();
            ArrayList<pay> loglist=new ArrayList<>();
            ArrayList<String> sortlist=new ArrayList<>(4);
            sortlist.add("food");sortlist.add("cloth");sortlist.add("elec");sortlist.add("book");
            for(int i=0;i<sortlist.size();i++){
                int sum=0;
                for(int j=0;j<paylist.size();j++){
                    String sort=gs.searchgoods(paylist.get(j).id).sort;
                    if(sort.equals(sortlist.get(i))){
                        sum+=paylist.get(j).price*paylist.get(j).number;
                        loglist.add(paylist.get(j));
                    }
                }
                profit.add(sum);
            }
            request.setAttribute("goal", "goods");
            request.setAttribute("loglist",loglist);
            request.setAttribute("sortlist",sortlist);
            request.setAttribute("profit", profit);
            request.getRequestDispatcher("manage-page.jsp").include(request, response);

        }
    }
}
