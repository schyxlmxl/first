package com.servlet;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.goods;
import com.jdbc.goodsserver;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import java.util.*;

@WebServlet(name = "Aboutgoods", value = "/Aboutgoods")
public class Aboutgoods extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        goods g=new goods();
        goodsserver gs=new goodsserver();
        String imgpath=null;
        Map<String,String> map=new HashMap<>();
        try{
            response.setContentType("text/html;charset=utf-8");
            DiskFileItemFactory factory =new DiskFileItemFactory();
            File f=new File("D:\\upload");
            if(!f.exists()){f.mkdirs();}
            factory.setRepository(f);
            ServletFileUpload fileUpload =new ServletFileUpload(factory);
            fileUpload.setHeaderEncoding("utf-8");

            List<FileItem>fileItems=fileUpload.parseRequest(new ServletRequestContext(request));

            for(FileItem fileItem:fileItems){
                if(fileItem.isFormField()){
                    String name=fileItem.getFieldName();
                    String value=fileItem.getString("UTF-8");
                    map.put(name,value);
                }else{
                    String filename=fileItem.getName();
                    if(filename!=null &&!filename.equals("")){

                        filename=filename.substring(filename.lastIndexOf("\\")+1);
                        filename=UUID.randomUUID().toString()+"_"+filename;
                        String webpath="/upload/";

                        String filepath=getServletContext().getRealPath(webpath+filename);
                        imgpath="upload/"+filename;

                        File file=new File(filepath);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                        InputStream in=fileItem.getInputStream();
                        FileOutputStream out=new FileOutputStream(file);
                        byte[] buffer=new byte[1024];
                        int len;
                        while((len=in.read(buffer))>0)
                            out.write(buffer,0,len);
                        in.close();
                        out.close();
                        fileItem.delete();
                    }
                }
            }
        }
        catch (Exception e){throw new RuntimeException(e);
        }

        g.init(map,imgpath);
        gs.addgoods(g);
        response.getWriter().print("<script language='javascript'>alert('添加成功，返回后台管理');window.location.href='./View?is_super=t'</script>");


    }
}
