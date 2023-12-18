package com.sell;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "file-upload", value = "/file-upload")
public class fileupload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
    doPost(request,response);}
        catch (Exception e){throw new RuntimeException(e);}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String imgpath=null;
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

    }
}
