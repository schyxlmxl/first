package com.jdbc;
import java.util.Map;
public class goods {
    public int id;
    public String name;
    public int number;
    public int price;
    public String introduction;
    public String img;
    public String sort;

    public void init(Map<String,String> map,String imgpath){
        name=map.get("name");
        number= Integer.parseInt(map.get("number"));
        price= Integer.parseInt(map.get("price"));
        introduction=map.get("introduction");
        img=imgpath;
        sort=map.get("sort");
}

    public  String getName(){return name;}

    public  String getSort(){return sort;}
    public  String getIntroduction(){return introduction;}
    public  String getImg(){return img;}
    public  int getId(){return id;}
    public  int getPrice(){return price;}
    public  int getNumber(){return number;}
}

