package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.jdbc.goodsserver;
import  com.jdbc.goods;
public class pay {

    public String payer;
    public String goods;
    public int id;
    public int number;
    public int price;
    public String time;
    public String belong;
    public String img;

    public void init(String payer1,String goods1,int id1,int number1,int price1,String time1){
        payer=payer1;
        goods=goods1;
        id=id1;
        number=number1;
        price=price1;
        time=time1;
    }
    public String getPayer(){
        return payer;
    }
    public String getGoods(){
        return  goods;
    }
    public  int getId(){
        return  id;
    }
    public  int getNumber(){
        return  number;
    }
    public  int getPrice(){
        return  price;
    }
    public  String getTime(){return  time;}
    public  String getBelong(){return  belong;}
    public String getImg(){return img;}
    public boolean addpay(String payer){
        PreparedStatement pst = null;
        Statement st=null;
        Connection con = null;
        ResultSet rs=null;
        try {
            con = jdbcmain.getConnction();
            st = con.createStatement();
            String sql = "SELECT * FROM cart WHERE payer='"+payer+"'";
            rs = st.executeQuery(sql);
            String timenow=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            while (rs.next()) {
                pay p = new pay();
                sql="INSERT INTO pay(payer,goods,id,number,price,time,belong) VALUES(?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, rs.getString("payer"));
                pst.setString(2, rs.getString("goods"));
                pst.setInt(3,rs.getInt("id"));
                pst.setInt(4,rs.getInt("number"));
                pst.setInt(5,rs.getInt("price"));
                pst.setString(6,timenow);
                pst.setString(7,rs.getString("belong"));
                int num = pst.executeUpdate();
                if (num <= 0) return false;
            }
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcmain.release(pst, con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<pay> paylist() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<pay> paylist = new ArrayList<pay>();
        try {
            con = jdbcmain.getConnction();
            st = con.createStatement();
            String sql = "SELECT * FROM pay";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                pay p = new pay();

                p.payer = rs.getString("payer");
                p.goods = rs.getString("goods");
                p.id = rs.getInt("id");
                p.number=rs.getInt("number");
                p.price=rs.getInt("price");
                p.time=rs.getString("time");
                p.belong=rs.getString("belong");
                paylist.add(p);
            }
            return paylist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcmain.release(rs, st, con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public ArrayList<pay> paylistforseller(String name) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<pay> paylist = new ArrayList<pay>();
        try {
            con = jdbcmain.getConnction();
            st = con.createStatement();
            String sql = "SELECT * FROM pay WHERE belong='"+name+"'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                pay p = new pay();

                p.payer = rs.getString("payer");
                p.goods = rs.getString("goods");
                p.id = rs.getInt("id");
                p.number=rs.getInt("number");
                p.price=rs.getInt("price");
                p.time=rs.getString("time");
                p.belong=rs.getString("belong");
                paylist.add(p);
            }
            return paylist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcmain.release(rs, st, con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    public boolean addcart(String payer,int id){
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();
            goodsserver gs=new goodsserver();
            goods g=gs.searchgoods(id);
            String timenow=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String sql = "INSERT INTO cart(payer,goods,id,number,price,belong,time) VALUES(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, payer);
            pst.setString(2, g.name);
            pst.setInt(3,id);
            pst.setInt(4,1);
            pst.setInt(5,g.price);
            pst.setString(6,g.belong);
            pst.setString(7,timenow);
            int num = pst.executeUpdate();
            if (num > 0) return true;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcmain.release(pst, con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public ArrayList<pay> cartlist(String name) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<pay> cartlist = new ArrayList<pay>();
        try {

            con = jdbcmain.getConnction();
            st = con.createStatement();
            String sql = "SELECT * FROM cart WHERE payer='"+name+"'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                pay p = new pay();

                p.payer = rs.getString("payer");
                p.goods = rs.getString("goods");
                p.id = rs.getInt("id");
                p.number=rs.getInt("number");
                p.price=rs.getInt("price");
                goodsserver gs=new goodsserver();
                goods g=gs.searchgoods(p.id);
                p.img=g.img;
                cartlist.add(p);
            }
            return cartlist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcmain.release(rs, st, con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    public ArrayList<pay> cartlistforseller(String name) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<pay> cartlist = new ArrayList<pay>();
        try {

            con = jdbcmain.getConnction();
            st = con.createStatement();
            String sql = "SELECT * FROM cart WHERE belong='"+name+"'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                pay p = new pay();

                p.payer = rs.getString("payer");
                p.goods = rs.getString("goods");
                p.id = rs.getInt("id");
                p.number=rs.getInt("number");
                p.price=rs.getInt("price");
                p.time=rs.getString("time");
                goodsserver gs=new goodsserver();
                goods g=gs.searchgoods(p.id);
                p.img=g.img;

                cartlist.add(p);
            }
            return cartlist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcmain.release(rs, st, con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    public boolean updatecart(String payer,int id,int number) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();
            String sql=null;
            goodsserver gs=new goodsserver();
            goods g=gs.searchgoods(id);
            //if(number>g.number)return false;

            if(number>0){
                sql = "UPDATE cart SET number=? WHERE payer=? AND id=?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, number);
                pst.setString(2,payer);
                pst.setInt(3,id);
            }
            else{
                sql="DELETE FROM cart WHERE payer=? AND id=?";
                pst = con.prepareStatement(sql);
                pst.setString(1,payer);
                pst.setInt(2,id);
            }

            int num = pst.executeUpdate();
            if (num > 0) return true;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcmain.release(pst, con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deletecart(String payer) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();
            String sql ="DELETE FROM cart WHERE payer=?";
            pst = con.prepareStatement(sql);
            pst.setString(1,payer);
            int num = pst.executeUpdate();
            if (num > 0) return true;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcmain.release(pst, con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}

