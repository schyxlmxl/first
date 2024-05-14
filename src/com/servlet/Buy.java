package com.servlet;

import java.io.*;
import java.rmi.ServerError;
import java.rmi.ServerException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.logtime;
import com.jdbc.pay;
import com.jdbc.user;
import com.jdbc.userserver;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@WebServlet(name = "Buy", value = "/Buy")
public class Buy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServerException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServerException {
        response.setContentType("text/html;charset=utf-8");
        String goal=request.getParameter("goal");
        String payer=request.getParameter("payer");
        pay p=new pay();
        boolean buy_ok=false;
        if(goal.equals("buy")){
            buy_ok=p.addpay(payer);
        }
        boolean del_ok=p.deletecart(payer);
        if(buy_ok && del_ok){
            Properties prop = new Properties();
            prop.setProperty("mail.host","smtp.163.com"); // 设置163邮件服务器
            prop.setProperty("mail.transport.protocol","smtp"); // 邮件发送协议
            prop.setProperty("mail.smtp.auth","true"); // 需要验证用户名密码

            Session session = Session.getDefaultInstance(prop);
            //开启session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);

            Transport ts = null;
            try {
                ts = session.getTransport();
                ts.connect("smtp.163.com", "15678489835@163.com", "UWXQFNAIKWZYBYAZ");
                //创建邮件
                //需要传递session
                MimeMessage message = new MimeMessage(session);
                //发件人
                message.setFrom(new InternetAddress("15678489835@163.com"));
                //收件人
                userserver us=new userserver();
                user user=us.searchuser(payer);
                String mail=user.email;
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
                //设置邮件的主体
                message.setSubject("购物确认邮件");
                //设置邮件的内容
                message.setContent("您在sch购物网站上进行了结账，如非本人操作请联系管理员", "text/html;charset=UTF-8");
                //发送邮件
                ts.sendMessage(message, message.getAllRecipients());
                //关闭连接
                ts.close();
            } catch (NoSuchProviderException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            logtime log=new logtime();
            String act="buy";
            String ip=request.getRemoteAddr();
            log.addlog(payer, act,ip);
            response.getWriter().print("<script language='javascript'>alert('结账成功！');window.location.href='./View2?goal=cart'</script>");
        }
        else if(del_ok){
            logtime log=new logtime();
            String act="cleancart";
            String ip=request.getRemoteAddr();
            log.addlog(payer, act,ip);
            response.getWriter().print("<script language='javascript'>alert('已清空购物车！');window.location.href='./View2?goal=cart'</script>");
        }
        else{
            response.getWriter().print("<script language='javascript'>alert('操作失败！');window.location.href='./View2?goal=cart'</script>");
        }

    }
}
