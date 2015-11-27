package in.jdsoft.studentmanagement.database;

import java.util.ArrayList;

import javax.servlet.ServletContext;

public class ConnectionObject {
	static ServletContext sc;
	ArrayList<ServletContext> conn=new ArrayList<>();
	
	
	 public void setDbAccess(ServletContext sc){
		ConnectionObject.sc=sc;
		conn.add(ConnectionObject.sc);
	 }
	 
	 public ArrayList<ServletContext> getDbAccess(){
		 
		 return conn;
	 }
}
