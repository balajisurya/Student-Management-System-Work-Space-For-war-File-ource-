package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.StudentStatus;

public class StudentStatusController {
	
static ServletContext sc;
	
	Connection studentStatusConn=null;
    PreparedStatement studentStatusStmt=null;
    ResultSet studentStatusRs=null;
    ArrayList<StudentStatus> returnStudentStatus=new ArrayList<>();
    ArrayList<StudentStatus> returnStudentStatusTitle=new ArrayList<>();
    

    
	public void getDbAccess(ServletContext sc){
		StudentStatusController.sc=sc;
	 }
	
	
	public ArrayList<StudentStatus> getStudentStatusList(){
    	try{
    		DBConnection studentSc=(DBConnection) sc.getAttribute("dbConn");
        	studentStatusConn=studentSc.getDBConnection();
        	studentStatusStmt=studentStatusConn.prepareStatement("SELECT * FROM tbl_student_status");
        	studentStatusRs=studentStatusStmt.executeQuery();
        	while(studentStatusRs.next()){
        		StudentStatus status=new StudentStatus();
        		status.setStudentStatusId(studentStatusRs.getInt("student_status_id"));
        		status.setStudentStatusTitle(studentStatusRs.getString("status_title"));
        		returnStudentStatus.add(status);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in getting student status list "+e);
    	}finally{
    		close();
    	}
    	
    	return returnStudentStatus;
    }
	
	
	
	public ArrayList<StudentStatus> getStudentStatusFromId(int studentStatusId){
    	try{
    		DBConnection studentSc=(DBConnection) sc.getAttribute("dbConn");
        	studentStatusConn=studentSc.getDBConnection();
        	studentStatusStmt=studentStatusConn.prepareStatement("SELECT * FROM tbl_student_status WHERE student_status_id=?");
        	studentStatusStmt.setInt(1, studentStatusId);
        	studentStatusRs=studentStatusStmt.executeQuery();
        	while(studentStatusRs.next()){
        		StudentStatus status=new StudentStatus();
        		status.setStudentStatusTitle(studentStatusRs.getString("status_title"));
        		returnStudentStatusTitle.add(status);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in getting student status "+e);
    	}finally{
    		close();
    	}
    	
    	return returnStudentStatusTitle;
    }
	
	
	 public void close(){
	    	try{
	    		if(studentStatusConn!=null){
					studentStatusConn.close();
				}
				if(studentStatusStmt!=null){
				  studentStatusStmt.close();	
				}
				if(studentStatusRs!=null){
					studentStatusRs.close();
			   }
			}catch(Exception e){
				System.out.println("Exception in closing student status connection of student status controller"+e);
			}
	    }

}
