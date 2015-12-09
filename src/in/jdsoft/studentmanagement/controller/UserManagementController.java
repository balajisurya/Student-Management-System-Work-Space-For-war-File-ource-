package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.UserManagement;

public class UserManagementController {

static ServletContext sc;
	
	Connection userManagementConn=null,userManagementConn1=null;
    PreparedStatement userManagementStmt=null,userManagementStmt1=null;
    ResultSet userManagementRs=null,userManagementRs1=null;
    ArrayList<UserManagement> returnUsersList=new ArrayList<>();
    ArrayList<UserManagement> returnUserStatus=new ArrayList<>();
    ArrayList<UserManagement> returnUserType=new ArrayList<>();
  
	public void getDbAccess(ServletContext sc){
		UserManagementController.sc=sc;
	 }

	 
    public void addUser(UserManagement user){
    	try{
    		DBConnection userManagementSc=(DBConnection) sc.getAttribute("dbConn");
    		userManagementConn=userManagementSc.getDBConnection();
    		userManagementStmt=userManagementConn.prepareStatement("INSERT INTO tbl_users(name,email,password,type,created_by,created_date,status) values(?,?,?,?,?,?,?)");
    		userManagementStmt.setString(1,user.getUsername());
    		userManagementStmt.setString(2,user.getUserEmail());
    		userManagementStmt.setString(3,user.getPassword());
    		userManagementStmt.setString(4,user.getUserType());
    		userManagementStmt.setString(5,user.getCreatedBy());
    		userManagementStmt.setTimestamp(6,user.getCreatedDate());
    		userManagementStmt.setInt(7,user.getUserStatusId());
    		userManagementStmt.execute();
    	}
    	catch(Exception e){ 
    		System.out.println("Exception in creating new user "+e);
    	}
    	finally{
    		close();
    	}
    }
    
    public ArrayList<UserManagement> getUsersList(){
    	try{
    		DBConnection userManagementSc=(DBConnection) sc.getAttribute("dbConn");
        	userManagementConn=userManagementSc.getDBConnection();
        	userManagementStmt=userManagementConn.prepareStatement("SELECT * FROM tbl_users");
        	userManagementRs=userManagementStmt.executeQuery();
        	while(userManagementRs.next()){
        		UserManagement user=new UserManagement();
        		user.setUserId(userManagementRs.getInt("user_id"));
        		user.setUsername(userManagementRs.getString("name"));
        		user.setUserEmail(userManagementRs.getString("email"));
        		user.setPassword(userManagementRs.getString("password"));
        		user.setUserType(userManagementRs.getString("type"));
        		user.setCreatedBy(userManagementRs.getString("created_by"));
        		user.setCreatedDate(userManagementRs.getTimestamp("created_date"));
        		user.setUserStatusTitle(getStatusTitle(userManagementRs.getInt("status")));
        		returnUsersList.add(user);
        		
        	}
    	}catch(Exception e){
    		System.out.println("Exception in Getting Users List "+e);
    	}finally{
    		close();
    	}
    	
    	return returnUsersList;
    }
    
    public void updateLoginTime(UserManagement user){
    	try{
		      DBConnection userManagementSc=(DBConnection) sc.getAttribute("dbConn");
		      userManagementConn=userManagementSc.getDBConnection();
		      userManagementStmt=userManagementConn.prepareStatement("UPDATE tbl_users SET last_login=? where email=?");
		      userManagementStmt.setTimestamp(1,user.getLastLogin());
		      userManagementStmt.setString(2,user.getUserEmail());
		      userManagementStmt.executeUpdate();
	  }catch(Exception e){
		  System.out.println("Exception in updating lastLogin "+e);
	  }finally{
		  close();
	  }
    }
    
    
    public String getStatusTitle(int statusId){
    	
    	String status=null;
    	try{
    		DBConnection userManagementSc=(DBConnection) sc.getAttribute("dbConn");
        	userManagementConn1=userManagementSc.getDBConnection();
        	userManagementStmt1=userManagementConn1.prepareStatement("SELECT user_status_title FROM tbl_user_status WHERE user_status_id=?");
        	userManagementStmt1.setInt(1,statusId);
        	userManagementRs1=userManagementStmt1.executeQuery();
        	if(userManagementRs1.next()){
        		status=userManagementRs1.getString("user_status_title");
        	}
    	}catch(Exception e){
    		System.out.println("Exception in Getting status from statusId "+e);
    	}finally{
    		try{
    			if(userManagementConn1!=null){
    				userManagementConn1.close();
    			}
    			if(userManagementStmt1!=null){
    			  userManagementStmt1.close();	
    			}
    			if(userManagementRs1!=null){
    				userManagementRs1.close();
    		   }
    		}catch(Exception e){
    			System.out.println("Exception in closing getStatusTitle() connection student registration controller"+e);
    		}
    	}
    	return status;
    }
    
    public ArrayList<UserManagement> getUserStatus(){
    	
    	try{
    		DBConnection userManagementSc=(DBConnection) sc.getAttribute("dbConn");
        	userManagementConn=userManagementSc.getDBConnection();
        	userManagementStmt=userManagementConn.prepareStatement("SELECT * FROM tbl_user_status");
        	userManagementRs=userManagementStmt.executeQuery();
        	while(userManagementRs.next()){
        		UserManagement userStatus=new UserManagement();
        		userStatus.setUserStatusId(userManagementRs.getInt("user_status_id"));
        		userStatus.setUserStatusTitle(userManagementRs.getString("user_status_title"));
        		returnUserStatus.add(userStatus);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in Getting User status  "+e);
    	}finally{
    		close();
    	}
    	return returnUserStatus;
    }
    
public ArrayList<UserManagement> getUserType(){
    	
    	try{
    		DBConnection userManagementSc=(DBConnection) sc.getAttribute("dbConn");
        	userManagementConn=userManagementSc.getDBConnection();
        	userManagementStmt=userManagementConn.prepareStatement("SELECT * FROM tbl_master_user_type");
        	userManagementRs=userManagementStmt.executeQuery();
        	while(userManagementRs.next()){
        		UserManagement usertype=new UserManagement();
        		usertype.setUserType(userManagementRs.getString("user_type"));
        		returnUserType.add(usertype);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in Getting User type "+e);
    	}finally{
    		close();
    	}
    	return returnUserType;  
}


    public Map<String,Object> getUserDetails(String email){
    	HashMap<String, Object> userDetails=new HashMap<>();
       try{
     	  DBConnection userManagementSc=(DBConnection) sc.getAttribute("dbConn");
     	 userManagementConn=userManagementSc.getDBConnection();
     	 userManagementStmt=userManagementConn.prepareStatement("SELECT * FROM tbl_users where email=?");
     	 userManagementStmt.setString(1,email);
     	 userManagementRs=userManagementStmt.executeQuery();
       	 if(userManagementRs.next()){
       		userDetails.put("USER_NAME", userManagementRs.getString("name"));
         	userDetails.put("USER_TYPE", userManagementRs.getString("type"));
         	userDetails.put("LAST_LOGIN", userManagementRs.getTimestamp("last_login"));
         }
       	  
       	  
       }catch(Exception e){
     	  System.out.println("Exception in course details of course controller"+e);
       }finally{
     	  close();
       }
       return userDetails;
 }
    
    
    public void close(){
    	try{
    		if(userManagementConn!=null){
				userManagementConn.close();
			}
			if(userManagementStmt!=null){
			  userManagementStmt.close();	
			}
			if(userManagementRs!=null){
				userManagementRs.close();
		   }
			
		}catch(Exception e){
			System.out.println("Exception in closing student Registration connection of student registration controller"+e);
		}
    }
}
