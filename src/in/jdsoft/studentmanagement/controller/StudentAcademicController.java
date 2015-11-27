package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.StudentAcademic;

public class StudentAcademicController {
static ServletContext sc;
	
	Connection academicConn=null,academicConn1=null;
    PreparedStatement academicStmt=null,academicStmt1=null;
    ResultSet academicRs=null,academicRs1=null;
    ArrayList<StudentAcademic> viewAcademics=new ArrayList<>();

    
	public void getDbAccess(ServletContext sc){
		 StudentAcademicController.sc=sc;
	 }
	
	
	 
    public void addAcademics(StudentAcademic academics){
    	try{
    	DBConnection academicsSc=(DBConnection) sc.getAttribute("dbConn");
    	academicConn=academicsSc.getDBConnection();
    	academicStmt=academicConn.prepareStatement("INSERT INTO tbl_student_academic(student_id,course_id,semester,invoice_status) values(?,?,?,?)");
    	academicStmt.setInt(1,academics.getStudentId());
    	academicStmt.setInt(2, academics.getCourseId());
    	academicStmt.setInt(3,academics.getSemester());
    	academicStmt.setInt(4,academics.getInvoiceStatus());
    	academicStmt.execute();
    	}catch(Exception e){ 
    		System.out.println("Exception in Adding academics"+e);
    	}
    	finally{
    		close();
    	}
    }
    
    public void updateInvoiceStatus(StudentAcademic studentAcademic){
    	try{
  		    DBConnection academicsSc=(DBConnection) sc.getAttribute("dbConn");
  		    academicConn=academicsSc.getDBConnection();
  		    academicStmt=academicConn.prepareStatement("UPDATE tbl_student_academic SET invoice_status=? where student_id=? AND semester=? AND academic_status=?");
  		    academicStmt.setInt(1,studentAcademic.getInvoiceStatus());
  		    academicStmt.setInt(2,studentAcademic.getStudentId());
  		    academicStmt.setInt(3,studentCurrentSemester(studentAcademic.getStudentId()));
  		    academicStmt.setString(4,"N/A");
  		    academicStmt.executeUpdate();
  	  }catch(Exception e){
  		  System.out.println("Exception in updating  of invoice status "+e);
  	  }finally{
  		  close();
  	  }
    }
    
    public void updateReceiptId(StudentAcademic receiptDetails){
    	try{
  		    DBConnection academicsSc=(DBConnection) sc.getAttribute("dbConn");
  		    academicConn=academicsSc.getDBConnection();
  		    academicStmt=academicConn.prepareStatement("UPDATE tbl_student_academic SET receipt_id=? where student_id=? AND semester=? AND academic_status=?");
  		    academicStmt.setInt(1,receiptDetails.getReceiptId());
  		    academicStmt.setInt(2,receiptDetails.getStudentId());
  		    academicStmt.setInt(3,studentCurrentSemester(receiptDetails.getStudentId()));
  		    academicStmt.setString(4,"N/A");
  		    academicStmt.executeUpdate();
  	  }catch(Exception e){
  		  System.out.println("Exception in updating Receipt Id"+e);
  	  }finally{
  		  close();
  	  }
    }
    
    public int studentCurrentSemester(int studentId){
    	int currentSemester=0;
    	try{
 		    DBConnection academicsSc=(DBConnection) sc.getAttribute("dbConn");
 		    academicConn1=academicsSc.getDBConnection();
 		    academicStmt1=academicConn1.prepareStatement("SELECT current_semester FROM `tbl_student` WHERE student_id=?");
 		    academicStmt1.setInt(1,studentId);
 		    academicRs1=academicStmt1.executeQuery();
 		    if(academicRs1.next()){
 		    	currentSemester=academicRs1.getInt(1);
 		    }
 	 }catch(Exception e){
 		  System.out.println("Exception in getting current semester of student"+e);
 	  }finally{
 		  try{
 			  	if(academicConn1!=null){
 			  		academicConn1.close();
 			  	}
 			  	if(academicStmt1!=null){
 			  		academicStmt1.close();	
 			  	}
 			  	if(academicRs1!=null){
 			  		academicRs1.close();
 			  	}
 		  	}
 		  	catch(Exception e){
 		  		System.out.println("Exception in closing connection of student current semester "+e);
 		  	}
 		 
 	  }
    	return currentSemester;
    }
    
    public boolean checkInvoiceStatus(int studentId){
    	boolean invoiceStatus=false;
    	int invoiceStatusId=0;
    	try{
 		    DBConnection academicsSc=(DBConnection) sc.getAttribute("dbConn");
 		    academicConn=academicsSc.getDBConnection();
 		    academicStmt=academicConn.prepareStatement("SELECT invoice_status FROM `tbl_student_academic` WHERE student_id=? AND semester=? AND academic_status=?");
 		    academicStmt.setInt(1,studentId);
 		    academicStmt.setInt(2,studentCurrentSemester(studentId));
 		    academicStmt.setString(3,"N/A");
 		    academicRs=academicStmt.executeQuery();
 		    if(academicRs.next()){
 		    	invoiceStatusId=academicRs.getInt(1);
 		    	if(invoiceStatusId==1){
 		    		invoiceStatus=false;
 		    	}
 		    	else if(invoiceStatusId==0){
 		    		invoiceStatus=true;
 		    	}
 		    }
 	 }catch(Exception e){
 		  System.out.println("Exception in checking invoice status"+e);
 	  }finally{
 		 close();
 	  }
    	return invoiceStatus;
    }
    
    
    
    public void updateAcademicStatus(StudentAcademic studentAcademic){
    	try{
    			DBConnection academicsSc=(DBConnection) sc.getAttribute("dbConn");
  		    	academicConn=academicsSc.getDBConnection();
  		    	academicStmt=academicConn.prepareStatement("UPDATE tbl_student_academic SET academic_status=? where student_id=? AND semester=? AND academic_status=?");
  		    	academicStmt.setString(1,studentAcademic.getAcademicStatus());
  		    	academicStmt.setInt(2,studentAcademic.getStudentId());
  		    	academicStmt.setInt(3,studentCurrentSemester(studentAcademic.getStudentId()));
  		    	academicStmt.setString(4,"N/A");
  		    	academicStmt.executeUpdate();
  	  }catch(Exception e){
  		  System.out.println("Exception in updating of academic status "+e);
  	  }finally{
  		  close();
  	  }
    }
    public void close(){
    	try{
    		if(academicConn!=null){
				academicConn.close();
			}
			if(academicStmt!=null){
			  academicStmt.close();	
			}
			if(academicRs!=null){
				academicRs.close();
		   }
		   
		}catch(Exception e){
			System.out.println("Exception in closing connections of student academic controller"+e);
		}
    }
}
