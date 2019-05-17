package com.wh.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

public class JDBCDemo01 {

	public static void main(String[] args) {
		//seletALL();
		System.out.println(selectByUsernamePassword("wh", "123"));
		System.out.println(selectByUsernamePassword("a", "123"));
		System.out.println(selectByUsernamePassword("a", "123' or '1'='1"));//(SQL×¢Èë)
		System.out.println(selectByUsernamePassword2("a", "123' or '1'='1"));
}
	public static void seletALL() {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/web01?serverTimezone=UTC&useSSL=false";
			String user="root";
			String password="wu6791245HAO";
	        con =DriverManager.getConnection(url, user, password);
	  	    stmt = con.createStatement();
			rs=stmt.executeQuery("select * from user");
			 while(rs.next()) {
				 System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
			 }	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			finally {
	            try {
	            	if(stmt!=null)rs.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
	            finally {

	                try {
	                	if(con!=null)con.close();
	                } catch (Exception e) {
	    	            // TODO: handle exception
	                }
				}
			}
		}
	}
	
public static boolean selectByUsernamePassword(String username,String password) {
    Connection con=null;   
    Statement stmt=null;
    ResultSet rs=null;
	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/web01?serverTimezone=UTC&useSSL=false";
	        con =DriverManager.getConnection(url, "root", "wu6791245HAO");
	  	    stmt = con.createStatement();
	  	    String sql="select * from user where username='"+username+"' and password='"+password+"'";
	  	    rs=stmt.executeQuery(sql);
	  	    if(rs.next()) {
	  	    	return true;
	  	    }
	  	    else {
	  	    	return false;
	  	    }
	 }catch (Exception e) {
	            // TODO: handle exception
     }
	return false;

   }
//(SQL×¢Èë)
public static boolean selectByUsernamePassword2(String username,String password) {
	Connection con=null;   
    Statement stmt=null;
    ResultSet rs=null;
	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/web01?serverTimezone=UTC&useSSL=false";
	        con =DriverManager.getConnection(url, "root", "wu6791245HAO");
	        String sql="select * from where username=? and password=?";
	  	    PreparedStatement pstmt=con.prepareStatement(sql);
	  	    pstmt.setString(1, username);
	  	    pstmt.setString(2, password);
	  	    rs=pstmt.executeQuery();
	  	  if(rs.next()) {
	  	    	return true;
	  	    }
	  	    else {
	  	    	return false;
	  	    }
	  	    
	 }catch (Exception e) {
	            // TODO: handle exception
     }
	return false;
  }
}//(¸ÄÁ¼)
