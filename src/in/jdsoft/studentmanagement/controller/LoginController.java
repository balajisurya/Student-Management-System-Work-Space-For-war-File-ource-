package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.Login;

public class LoginController {
	
	static ServletContext sc;
	public boolean uservalidation=false;
	Connection loginconn=null;
    PreparedStatement loginstmt=null;
    ResultSet loginrs=null;
	
	public void getDbAccess(ServletContext sc){
		 LoginController.sc=sc;
	 }
	 
	 public boolean checkUser(String username, String password){
		 Login loginvalidate=new Login();
	     
	     loginvalidate.setUsername(username);
	     loginvalidate.setPassword(password);
	     try{
	    	 DBConnection loginSc=(DBConnection) sc.getAttribute("dbConn");
				loginconn=loginSc.getDBConnection();
				loginstmt=loginconn.prepareStatement("SELECT * FROM tbl_users where email=?");
				loginstmt.setString(1,username);
				loginrs=loginstmt.executeQuery();
				if(loginrs.next()){
				  if(loginrs.getString("password").equals(password))
				  {
					  loginvalidate.setUserStatus(loginrs.getInt("status"));
			       	  uservalidation=true;
				  }
				  else{
					  uservalidation=false;
				  }
				}
				else{
					//no email id found
				}
	    	 
	     }
	     catch(Exception e){
	    	 
	    	 System.out.println("In login controller"+e);
	     }
	     finally {
			close(); 
		 }
	     return uservalidation;
	 }
	 
	 public void close(){
	    	try{
	    		if(loginconn!=null){
					loginconn.close();
				}
				if(loginstmt!=null){
				  loginstmt.close();	
				}
				if(loginrs!=null){
					loginrs.close();
			   }
			}catch(Exception e){
				System.out.println("Exception in closing login connections"+e);
			}
	    }
	
}
