package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.CourseCategory;

public class CourseCategoryController {
static ServletContext sc;
	
	Connection courseCategoryConn=null;
    PreparedStatement courseCategoryStmt=null;
    ResultSet courseCategoryRs=null;
    ArrayList<CourseCategory> returnCourseCategorys=new ArrayList<>();

    
	public void getDbAccess(ServletContext sc){
		CourseCategoryController .sc=sc;
	 }
   
    public ArrayList<CourseCategory> getCategoryList(){
    	try{
    		DBConnection categorySc=(DBConnection) sc.getAttribute("dbConn");
        	courseCategoryConn=categorySc.getDBConnection();
        	courseCategoryStmt=courseCategoryConn.prepareStatement("SELECT * FROM tbl_course_category");
        	courseCategoryRs=courseCategoryStmt.executeQuery();
        	while(courseCategoryRs.next()){
        		
        		CourseCategory courseCategory=new CourseCategory();
        		courseCategory.setCategoryId(courseCategoryRs.getInt("category_id"));
        		courseCategory.setCategoryName(courseCategoryRs.getString("category_name"));
        		returnCourseCategorys.add(courseCategory);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in getting course category list "+e);
    	}finally{
    		close();
    	}
    	
    	return returnCourseCategorys;
    }
    

    public void close(){
    	try{
    		if(courseCategoryConn!=null){
				courseCategoryConn.close();
			}
			if(courseCategoryStmt!=null){
			  courseCategoryStmt.close();	
			}
			if(courseCategoryRs!=null){
				courseCategoryRs.close();
		   }
		}catch(Exception e){
			System.out.println("Exception in closing course category connection"+e);
		}
    }
}
