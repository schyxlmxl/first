package com.servlet;
import com.jdbc.goods;
import com.jdbc.goodsserver;
import com.jdbc.logtime;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Updategoods", value = "/Updategoods")
public class Updategoods extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String s=request.getParameter("second");
        int id= Integer.parseInt(request.getParameter("id"));
        goodsserver gs=new goodsserver();
        goods g=gs.searchgoods(id);
        if(s==null ||s.isEmpty()){
            request.setAttribute("id",g.id);
            request.setAttribute("name",g.name);
            request.setAttribute("price",g.price);
            request.setAttribute("number",g.number);
            request.setAttribute("introduction",g.introduction);
            request.getRequestDispatcher("update-goods.jsp").include(request,response);}
        else{
            g.name=request.getParameter("name");
            g.introduction=request.getParameter("introduction");
            g.price= Integer.parseInt(request.getParameter("price"));
            g.number= Integer.parseInt(request.getParameter("number"));
            String name=null;
            Cookie[] cookies=request.getCookies();
            for(int i=0;cookies!=null && i<cookies.length;i++)
            {
                if("name".equals(cookies[i].getName())){
                    name=cookies[i].getValue();
                }
            }
            logtime log=new logtime();
            String act="updategoods";
            String ip=request.getRemoteAddr();
            log.addlog(name, act,ip);
            if(gs.updategoods(g))
                response.getWriter().print("<script language='javascript'>alert('修改成功，返回后台管理');window.location.href='./View?is_super=t'</script>");
        }
    }
}
