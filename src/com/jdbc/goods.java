package com.jdbc;
import javax.servlet.http.Cookie;
import java.util.Map;
public class goods {
    public int id;
    public String name;
    public int number;
    public int price;
    public String introduction;
    public String img;
    public String sort;
    public String belong;

    public void init(Map<String,String> map,String imgpath,String name1){
        name=map.get("name");
        number= Integer.parseInt(map.get("number"));
        price= Integer.parseInt(map.get("price"));
        introduction=map.get("introduction");
        img=imgpath;
        sort=map.get("sort");
        belong=name1;
}

    public  String getName(){return name;}

    public  String getSort(){return sort;}
    public  String getIntroduction(){return introduction;}
    public  String getImg(){return img;}
    public String getBelong(){return  belong;}
    public  int getId(){return id;}
    public  int getPrice(){return price;}
    public  int getNumber(){return number;}

}

