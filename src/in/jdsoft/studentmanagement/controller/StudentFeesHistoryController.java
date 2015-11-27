package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.StudentFeesHistory;

public class StudentFeesHistoryController {

static ServletContext sc;
	
	Connection feesHistoryConn=null,feesHistoryConn1=null,feesHistoryConn2=null;
    PreparedStatement feesHistoryStmt=null,feesHistoryStmt1=null,feesHistoryStmt2=null;
    ResultSet feesHistoryRs=null,feesHistoryRs1=null,feesHistoryRs2=null;
    
  
    
	public void getDbAccess(ServletContext sc){
		 StudentFeesHistoryController.sc=sc;
	 }

	
	
    public void addStudentFeesHistory(int receiptId,int templateStructureId){
    	try{
    		
    		DBConnection feesHistorySc1=(DBConnection) sc.getAttribute("dbConn");
    		feesHistoryConn1=feesHistorySc1.getDBConnection();
    		feesHistoryStmt1=feesHistoryConn1.prepareStatement("SELECT * FROM tbl_student_fees_receipt WHERE receipt_id=?");
    		feesHistoryStmt1.setInt(1, receiptId);
    		feesHistoryRs1=feesHistoryStmt1.executeQuery();
    		int studentId=0,semester=0;
    		String templateItemIds;
    		TemplateItemController templateItem=new TemplateItemController();
    		
    	    if(feesHistoryRs1.next()){
    	    	studentId=feesHistoryRs1.getInt("student_id");
    	    	semester=feesHistoryRs1.getInt("semester");
    	    }
    	    
    	    
    	    DBConnection feesHistorySc2=(DBConnection) sc.getAttribute("dbConn");
    		feesHistoryConn2=feesHistorySc2.getDBConnection();
    		feesHistoryStmt2=feesHistoryConn2.prepareStatement("SELECT template_item_id FROM tbl_fees_template WHERE template_id=?");
    		feesHistoryStmt2.setInt(1, templateStructureId);
    		feesHistoryRs2=feesHistoryStmt2.executeQuery();
    		if(feesHistoryRs2.next()){
    			templateItemIds=feesHistoryRs2.getString("template_item_id");
    			String templateItemsIds[]=templateItemIds.split(",");
    			DBConnection feesHistorySc=(DBConnection) sc.getAttribute("dbConn");
        		feesHistoryConn=feesHistorySc.getDBConnection();
        		
    			for(String templateItemId:templateItemsIds){
    				feesHistoryStmt=feesHistoryConn.prepareStatement("INSERT INTO tbl_student_fees_history(student_id,semester,receipt_id,template_item_name,template_item_amount,created_date ) values(?,?,?,?,?,?)");
    	    		feesHistoryStmt.setInt(1,studentId);
    	    		feesHistoryStmt.setInt(2,semester);
    	    		feesHistoryStmt.setInt(3,receiptId);
    	    		feesHistoryStmt.setString(4,templateItem.templateItemDetailsFromId(Integer.parseInt(templateItemId)).get("template_item_name").toString());
    	    		feesHistoryStmt.setDouble(5,(Double)templateItem.templateItemDetailsFromId(Integer.parseInt(templateItemId)).get("template_item_price"));
    	    		Calendar calendar = Calendar.getInstance();
            		java.util.Date createdDate = calendar.getTime();
            		Timestamp createdDateTime = new Timestamp(createdDate.getTime());
    	    		feesHistoryStmt.setTimestamp(6,createdDateTime);
    	    		feesHistoryStmt.execute();
            	}
    		}
   	   }catch(Exception e){ 
    		System.out.println("Exception in Adding fees history "+e);
    	   }
           finally{
    		 close();
    	   }
     }
    
    public ArrayList<StudentFeesHistory> getFeesItemsOfReceipt(int receiptId){
    	ArrayList<StudentFeesHistory> feesItems=new ArrayList<>();
    	try{
    		DBConnection feesHistorySc=(DBConnection) sc.getAttribute("dbConn");
    		feesHistoryConn=feesHistorySc.getDBConnection();
    		feesHistoryStmt=feesHistoryConn.prepareStatement("SELECT * FROM tbl_student_fees_history WHERE receipt_id=?");
    		feesHistoryStmt.setInt(1, receiptId);
    		feesHistoryRs=feesHistoryStmt.executeQuery();
    			while(feesHistoryRs.next()){
    				StudentFeesHistory feesItem=new StudentFeesHistory();
    				feesItem.setTemplateItemName(feesHistoryRs.getString("template_item_name"));
    				feesItem.setTemplateItemAmount(feesHistoryRs.getDouble("template_item_amount"));
    				feesItems.add(feesItem);
    			}
         }
    	 catch(Exception e){ 
    		System.out.println("Exception in getting fees history of student fees controller"+e);
    	 }
         finally{
    		 close();
    	 }
    	return feesItems;
     }
    
    public double getTotalPendingFee(int studentId){
    	double pendingFee=0;
    	try{
    		DBConnection feesHistorySc=(DBConnection) sc.getAttribute("dbConn");
    		feesHistoryConn=feesHistorySc.getDBConnection();
    		feesHistoryStmt=feesHistoryConn.prepareStatement("SELECT SUM(fees_pending) FROM `tbl_student_fees_receipt` WHERE student_id=?");
    		feesHistoryStmt.setInt(1, studentId);
    		feesHistoryRs=feesHistoryStmt.executeQuery();
    			if(feesHistoryRs.next()){
    		     	pendingFee=feesHistoryRs.getDouble(1);
    			}
         }
    	 catch(Exception e){ 
    		System.out.println("Exception in getting total pending fees student fees historycontroller"+e);
    	 }
         finally{
    		 close();
    	 }
    	return pendingFee;
    }
    
    
    public void close(){
    	try{
			
			if(feesHistoryConn!=null){
				feesHistoryConn.close();
			}
			if(feesHistoryStmt!=null){
				feesHistoryStmt.close();	
			}
			if(feesHistoryRs!=null){
				feesHistoryRs.close();
		   }
			if(feesHistoryConn1!=null){
				feesHistoryConn1.close();
			}
			if(feesHistoryStmt1!=null){
				feesHistoryStmt1.close();	
			}
			if(feesHistoryRs1!=null){
				feesHistoryRs1.close();
		   }
			if(feesHistoryConn2!=null){
				feesHistoryConn2.close();
			}
			if(feesHistoryStmt2!=null){
				feesHistoryStmt2.close();	
			}
			if(feesHistoryRs2!=null){
				feesHistoryRs2.close();
		   }
		}catch(Exception e){
			System.out.println("Exception in connection closing of fees history controller "+e);
		}
    }
}
