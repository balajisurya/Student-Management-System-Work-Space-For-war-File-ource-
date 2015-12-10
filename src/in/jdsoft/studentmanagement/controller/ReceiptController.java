package in.jdsoft.studentmanagement.controller;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.Receipt;
import in.jdsoft.studentmanagement.model.StudentAcademic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletContext;

import com.mysql.jdbc.Statement;

public class ReceiptController {

static ServletContext sc;
	
	Connection receiptConn=null;
    PreparedStatement receiptStmt=null;
    ResultSet receiptRs=null;
    
  
    
	public void getDbAccess(ServletContext sc){
		 ReceiptController.sc=sc;
	 }

	
    public void addReceipt(String []validStudentId,int feesTemplateId){
    	try{
    		DBConnection receiptSc=(DBConnection) sc.getAttribute("dbConn");
    		receiptConn=receiptSc.getDBConnection();
    		int currentSemester=0;
    		//StudentRegistrationController studentRegistrationController=new StudentRegistrationController();//
    		StudentAcademicController studentAcademicController=new StudentAcademicController();
    		StudentAcademic studentAcademic=new StudentAcademic();
    		StudentAcademic studentReceipt=new StudentAcademic();
    		FeesTemplateController feesTemplateController=new FeesTemplateController();
    		StudentFeesHistoryController studentFeesHistory=new StudentFeesHistoryController();
    		
    		for(int i=0;i<validStudentId.length;i++){
    			//ArrayList<StudentRegistration> studentDetails= studentRegistrationController.getStudentDetails(Integer.parseInt(validStudentId[i]));
        			//for(StudentRegistration studentDetail:studentDetails){
        				//currentSemester=studentDetail.getSemester();
        			//}
        			
        			currentSemester=studentAcademicController.studentCurrentSemester(Integer.parseInt(validStudentId[i]));
        		
        			double templateFees=(Double)feesTemplateController.templateDetailsFromId(feesTemplateId).get("template_fee");
        		
    			receiptStmt=receiptConn.prepareStatement("INSERT INTO tbl_student_fees_receipt(student_id,semester,total_fees,fees_pending,created_date) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        		receiptStmt.setInt(1,Integer.parseInt(validStudentId[i]));
        		receiptStmt.setInt(2,currentSemester);
        		receiptStmt.setDouble(3,templateFees);
        		receiptStmt.setDouble(4, pendingFee(templateFees,0));
        		Calendar calendar = Calendar.getInstance();
        		java.util.Date createdDate = calendar.getTime();
        		Timestamp createdDateTime = new Timestamp(createdDate.getTime());
        		receiptStmt.setTimestamp(5, createdDateTime);
        		receiptStmt.execute();
        		receiptRs=receiptStmt.getGeneratedKeys();
        		if(receiptRs.next()){
					studentFeesHistory.addStudentFeesHistory(receiptRs.getInt(1), feesTemplateId);//adding fees histoty for student
					studentAcademic.setStudentId(Integer.parseInt(validStudentId[i]));
					studentAcademic.setInvoiceStatus(1);
					studentAcademicController.updateInvoiceStatus(studentAcademic);
					studentReceipt.setStudentId(Integer.parseInt(validStudentId[i]));
					studentReceipt.setReceipId(receiptRs.getInt(1));
					studentAcademicController.updateReceiptId(studentReceipt);
        		}
        		
    		}
    		
    	   }catch(Exception e){ 
    		System.out.println("Exception in Add Receipt "+e);
    	   }
           finally{
    		 close();
    	   }
      }
    
public Receipt getReceiptDetails(int receiptId){
    	
    Receipt receipt=new Receipt();
    	try{
    			DBConnection receiptSc=(DBConnection) sc.getAttribute("dbConn");
    			receiptConn=receiptSc.getDBConnection();
    			receiptStmt=receiptConn.prepareStatement("SELECT * FROM tbl_student_fees_receipt where receipt_id=?");
    			receiptStmt.setInt(1,receiptId);
    			receiptRs=receiptStmt.executeQuery();
    			receiptRs.next();
    			receipt.setReceiptId(receiptRs.getInt("receipt_id"));
    			receipt.setStudentId(receiptRs.getInt("student_id"));
    			receipt.setSemester(receiptRs.getInt("semester"));
    			receipt.setTotalFees(receiptRs.getDouble("total_fees"));
    			receipt.setPendingFees(receiptRs.getDouble("fees_pending"));
    			receipt.setStatus(receiptRs.getInt("status"));

    		}
    		catch(Exception e){
    			System.out.println("Exception in getting receipt details of Receipt controller"+e);
    		}
    		finally{
    			close();
    		}
       return receipt;
  }

   public ArrayList<Receipt> getAllReceiptId(int studentId){
	   ArrayList<Receipt> receiptIds=new ArrayList<>();
	   try{
			DBConnection receiptSc=(DBConnection) sc.getAttribute("dbConn");
			receiptConn=receiptSc.getDBConnection();
			receiptStmt=receiptConn.prepareStatement("SELECT receipt_id FROM tbl_student_fees_receipt where student_id=?");
			receiptStmt.setInt(1,studentId);
			receiptRs=receiptStmt.executeQuery();
			while(receiptRs.next()){
				Receipt receipId=new Receipt();
				receipId.setReceiptId(receiptRs.getInt("receipt_id"));
				receiptIds.add(receipId);
			}
        }
		catch(Exception e){
			System.out.println("Exception in getting all receipt id of student in Receipt controller "+e);
		}
		finally{
			close();
		}
	   
	   return receiptIds;
   }
     
   
    public double pendingFee(double totalFee ,double paidFee){
    	  double pendingFee=totalFee-paidFee;
    	  return pendingFee;
     }
      
      
      public ArrayList<StudentAcademic>  currentSemesterInvoice(){
    	  ArrayList<StudentAcademic> returnCurrentSemesterInvoices=new ArrayList<>();
    	  
    	  try{
    		    DBConnection receiptSc=(DBConnection) sc.getAttribute("dbConn");
      		    receiptConn=receiptSc.getDBConnection();
      			receiptStmt=receiptConn.prepareStatement("SELECT * FROM tbl_student_academic WHERE academic_status=? AND invoice_status=?");
          		receiptStmt.setString(1,"N/A");
          		receiptStmt.setInt(2,1);
          		receiptRs=receiptStmt.executeQuery();
          		while(receiptRs.next()){
          			StudentAcademic studentInvoices=new StudentAcademic();
          			studentInvoices.setStudentId(receiptRs.getInt("student_id"));
          			studentInvoices.setReceipId(receiptRs.getInt("receipt_id"));
          			returnCurrentSemesterInvoices.add(studentInvoices);
          		}
       	   }catch(Exception e){ 
      		System.out.println("Exception in getting currentSemester invoices "+e);
      	   }
             finally{
      		 close();
      	   }
     	 return returnCurrentSemesterInvoices;
       }
    
      
    public void close(){
    	try{
			
			if(receiptConn!=null){
				receiptConn.close();
			}
			if(receiptStmt!=null){
				receiptStmt.close();	
			}
			if(receiptRs!=null){
				receiptRs.close();
		   }
		}catch(Exception e){
			System.out.println("Exception in connection closing of Receipt Controller"+e);
		}
    }
}
