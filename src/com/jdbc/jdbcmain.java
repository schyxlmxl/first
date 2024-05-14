package com.jdbc;
import java.sql.*;
public class jdbcmain {
    public static Connection getConnction()throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="rootroot";
        Connection con=DriverManager.getConnection(url,user,password);
        return  con;
    }//链接数据库的基本操作
    public static void release(PreparedStatement pst,Connection con)throws SQLException,ClassNotFoundException{
        if(pst!=null){
            try{
                pst.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            pst=null;
        }
        if(con!=null){
            try{
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            con=null;
        }
    }
    public static void release(ResultSet rs,Statement st,Connection con)throws SQLException,ClassNotFoundException{
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                st.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            st=null;
        }
        if(con!=null){
            try{
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            con=null;
        }
    }
}//根据使用到statement还是preparedstatement用不同的函数进行释放资源