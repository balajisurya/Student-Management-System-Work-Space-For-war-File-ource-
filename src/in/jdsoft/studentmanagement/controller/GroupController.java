package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import com.mysql.jdbc.Statement;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.Group;

public class GroupController {

static ServletContext sc;
	
	Connection groupsConn=null;
    PreparedStatement groupsStmt=null;
    ResultSet groupsRs=null;
    ArrayList<Group> viewGroups=new ArrayList<>();
  
    
	public void getDbAccess(ServletContext sc){
		 GroupController.sc=sc;
	 }

	
    public int addGroup(Group g){
    	int groupId=0;
    	try{
    			DBConnection groupSc=(DBConnection) sc.getAttribute("dbConn");
    			groupsConn=groupSc.getDBConnection();
    			groupsStmt=groupsConn.prepareStatement("INSERT INTO tbl_groups(group_name) values(?)",Statement.RETURN_GENERATED_KEYS);
    			groupsStmt.setString(1,g.getGroupName());
    			groupsStmt.execute();
    			groupsRs=groupsStmt.getGeneratedKeys();
    			if(groupsRs.next()){
    				groupId=groupsRs.getInt(1);
    			}
    		}
    		catch(Exception e){ 
    			System.out.println("Exception in Add group of group controller "+e);
    		}
    		finally{
    			close();
    		}
    	
    	 return groupId;
    }
 
    public ArrayList<Group> viewGroups(){
    	try{
    		DBConnection groupSc=(DBConnection) sc.getAttribute("dbConn");
        	groupsConn=groupSc.getDBConnection();
        	groupsStmt=groupsConn.prepareStatement("SELECT * FROM tbl_groups ORDER BY group_id DESC");
        	groupsRs=groupsStmt.executeQuery();
        	while(groupsRs.next()){
        		Group group=new Group();
        		group.setGroupId(groupsRs.getInt("group_id"));
        		group.setGroupName(groupsRs.getString("group_name"));
        		viewGroups.add(group);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in view group of group controller "+e);
    	}finally{
    		close();
    	}	
    	return viewGroups;
    }
    

    public void deleteGroup(Group g){
    	try{
    		DBConnection groupSc=(DBConnection) sc.getAttribute("dbConn");
        	groupsConn=groupSc.getDBConnection();
        	groupsStmt=groupsConn.prepareStatement("DELETE FROM tbl_groups where group_id=?");
        	groupsStmt.setInt(1,g.getGroupId());
        	groupsStmt.executeUpdate();
    	}catch(Exception e){
    		System.out.println("Exception in delete group of group controller "+e);
    	}finally{
    		close();
    	}
    }
    
    
    public void updateGroup(Group g){
    	  try{
    		  DBConnection groupSc=(DBConnection) sc.getAttribute("dbConn");
          	  groupsConn=groupSc.getDBConnection();
        	groupsStmt=groupsConn.prepareStatement("UPDATE tbl_groups SET group_name=?,course_id=? where group_id=?");
        	groupsStmt.setString(1,g.getGroupName());
        	groupsStmt.setInt(3,g.getGroupId());
          	groupsStmt.executeUpdate();
    	  }catch(Exception e){
    		  System.out.println("Exception in update group of group controller "+e);
    	  }finally{
    		  close();
    	  }
    }
    
    
    public String groupNameFromId(int groupId){
    	   String group_name=null;
          try{
        	  DBConnection groupSc=(DBConnection) sc.getAttribute("dbConn");
          	  groupsConn=groupSc.getDBConnection();
          	  groupsStmt=groupsConn.prepareStatement("SELECT group_name FROM tbl_groups where group_id=?");
          	  groupsStmt.setInt(1,groupId);
          	  groupsRs=groupsStmt.executeQuery();
          	  groupsRs.next();
          	  group_name=groupsRs.getString("group_name");
          }catch(Exception e){
        	  System.out.println("Exception in group Name from group Id of group controller "+e);
          }finally{
        	  close();
          }
          return group_name;
    }
    
    public Map<String,Object> groupDetailsFromId(int groupId){
    	HashMap<String, Object> groupDetails=new HashMap<>();
       try{
     	  DBConnection groupSc=(DBConnection) sc.getAttribute("dbConn");
       	  groupsConn=groupSc.getDBConnection();
       	  groupsStmt=groupsConn.prepareStatement("SELECT * FROM tbl_groups where group_id=?");
       	  groupsStmt.setInt(1,groupId);
       	  groupsRs=groupsStmt.executeQuery();
       	  groupsRs.next();
       	  groupDetails.put("group_name", groupsRs.getString("group_name"));
       	  
       }catch(Exception e){
     	  System.out.println("Exception in group details of group controller"+e);
       }finally{
     	  close();
       }
       return groupDetails;
 }
    
    public void close(){
    	try{
			
			if(groupsConn!=null){
				groupsConn.close();
			}
			if(groupsStmt!=null){
				groupsStmt.close();	
			}
			if(groupsRs!=null){
				groupsRs.close();
		   }
		}catch(Exception e){
			System.out.println("Exception in connection closing group controller"+e);
		}
    }
}