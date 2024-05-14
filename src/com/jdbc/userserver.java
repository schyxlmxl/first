package com.jdbc;
import java.sql.*;
import java.util.ArrayList;

public class userserver {
    public boolean adduser(user user) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();

            if(searchuser(user.name)!=null)return false;
            String sql = "INSERT INTO users(name,password,email) VALUES(?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, user.name);
            pst.setString(2, user.password);
            pst.setString(3, user.email);
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
    public boolean addseller(user user) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();

            if(searchuser(user.name)!=null)return false;
            String sql = "INSERT INTO users(name,password,super) VALUES(?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, user.name);
            pst.setString(2, user.password);
            pst.setInt(3, 2);
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

    public boolean deleteuser(String name) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();
            String sql = "DELETE FROM users WHERE name=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
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

    public boolean updateuser(user user) {
        PreparedStatement pst = null;
        Connection con = null;
        try {
            con = jdbcmain.getConnction();
            String sql = "UPDATE users SET name=?,password=?,email=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, user.name);
            pst.setString(2, user.password);
            pst.setString(3, user.email);
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

    public ArrayList<user> userlist() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<user> userlist = new ArrayList<user>();
        try {
            con = jdbcmain.getConnction();
            st = con.createStatement();
            String sql = "SELECT * FROM users";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                user user = new user();
                user.id = rs.getInt("id");
                user.name = rs.getString("name");
                user.password = rs.getString("password");
                user.email = rs.getString("email");
                user.is_super=rs.getInt("super");
                userlist.add(user);
            }
            return userlist;
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


    public user searchuser(String name) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con=jdbcmain.getConnction();
            st=con.createStatement();
            String sql="SELECT * FROM users WHERE name='"+name+"'";
            rs=st.executeQuery(sql);
            while (rs.next()) {
                user user=new user();
                user.id = rs.getInt("id");
                user.name = rs.getString("name");
                user.password = rs.getString("password");
                user.email = rs.getString("email");
                user.is_super=rs.getInt("super");
                return user;
            }
            return null;
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


