package com.jdbc;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class logtime {
    public boolean  addlog(String name,String act,String ip) {
        String time=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();

            String sql = "INSERT INTO log(name,time,act,ip) VALUES(?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, time);
            pst.setString(3,act);
            pst.setString(4,ip);
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
    public ArrayList<String[]> loglist() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<String[]> loglist = new ArrayList<String[]>();
        try {
            con = jdbcmain.getConnction();
            st = con.createStatement();
            String sql = "SELECT * FROM log";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String[] s=new String[4];
                s[0]= rs.getString("name");
                s[1]=rs.getString("time");
                s[2]=rs.getString("act");
                s[3]=rs.getString("ip");
                loglist.add(s);
            }
            return loglist;
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
}