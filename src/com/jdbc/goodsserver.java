package com.jdbc;
import java.sql.*;
import java.util.ArrayList;

public class goodsserver {
    public boolean addgoods(goods goods) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();
            String sql = "INSERT INTO goods(name,sort,introduction,img,belong,price,number) VALUES(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, goods.name);
            pst.setString(2, goods.sort);
            pst.setString(3, goods.introduction);
            pst.setString(4, goods.img);
            pst.setString(5,goods.belong);
            pst.setInt(6, goods.price);
            pst.setInt(7, goods.number);
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


    public boolean deletegoods(int id) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();
            String sql = "DELETE FROM goods WHERE id=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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

    public boolean updategoods(goods goods) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();
            String sql = "UPDATE goods SET name=?,introduction=?,price=?,number=? WHERE id=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, goods.name);
            pst.setString(2, goods.introduction);
            pst.setInt(3, goods.price);
            pst.setInt(4,goods.number);
            pst.setInt(5,goods.id);
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


    public ArrayList<goods> goodslist(String sort) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<goods> goodslist = new ArrayList<goods>();
        try {
            con = jdbcmain.getConnction();
            st = con.createStatement();
            String sql ;
            if(sort==null)
                sql = "SELECT * FROM goods";
            else
                sql="SELECT * FROM goods WHERE sort='"+sort+"'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                goods goods = new goods();
                goods.id = rs.getInt("id");
                goods.name = rs.getString("name");
                goods.sort = rs.getString("sort");
                goods.introduction = rs.getString("introduction");
                goods.img=rs.getString("img");
                goods.belong=rs.getString("belong");
                goods.number=rs.getInt("number");
                goods.price=rs.getInt("price");
                goodslist.add(goods);
            }
            return goodslist;
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

    public ArrayList<goods> searchgoods(String name) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<goods> goodslist = new ArrayList<goods>();
        try{
            con=jdbcmain.getConnction();
            st=con.createStatement();
            String sql="SELECT * FROM goods WHERE name='"+name+"'";
            rs=st.executeQuery(sql);
            while (rs.next()) {
                goods goods=new goods();
                goods.id = rs.getInt("id");
                goods.name = rs.getString("name");
                goods.sort = rs.getString("sort");
                goods.introduction = rs.getString("introduction");
                goods.img=rs.getString("img");
                goods.belong=rs.getString("belong");
                goods.number=rs.getInt("number");
                goods.price=rs.getInt("price");
                goodslist.add(goods);
            }
            return goodslist;
        }
        catch (Exception e){e.printStackTrace();}
        finally {
            try {
                jdbcmain.release(rs,st,con);
            }catch (Exception e){e.printStackTrace();}
        }
        return null;
    }

    public goods searchgoods(int id) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con=jdbcmain.getConnction();
            st=con.createStatement();
            String sql="SELECT * FROM goods WHERE id="+id;
            rs=st.executeQuery(sql);
            while (rs.next()) {
                goods goods=new goods();
                goods.id = rs.getInt("id");
                goods.name = rs.getString("name");
                goods.sort = rs.getString("sort");
                goods.introduction = rs.getString("introduction");
                goods.img=rs.getString("img");
                goods.belong=rs.getString("belong");
                goods.number=rs.getInt("number");
                goods.price=rs.getInt("price");
                return goods;
            }

        }
        catch (Exception e){e.printStackTrace();}
        finally {
            try {
                jdbcmain.release(rs,st,con);
            }catch (Exception e){e.printStackTrace();}
        }
        return null;
    }
}
