package in.jdsoft.reportingtool.feeders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import in.jdsoft.reportingtool.model.Report;
import in.jdsoft.studentmanagement.database.DBConnection;

public class FeesFeeder {
	
static ServletContext sc;
	
	Connection feesFeederConn=null;
    PreparedStatement feesFeederStmt=null;
    ResultSet feesFeederRs=null;
    

    
	public void getDbAccess(ServletContext sc){
		FeesFeeder .sc=sc;
	 }
	
	
	public Report getFeesDetailsPercentage(){
		Report feesDetailsPercentage=new Report();
		try{
			DBConnection feesFeederSc=(DBConnection) sc.getAttribute("dbConn");
			feesFeederConn=feesFeederSc.getDBConnection();
			 String reportingProcedure= "{ call reportingProcedure() }";
			  java.sql.CallableStatement cs = feesFeederConn.prepareCall(reportingProcedure);
			  cs.execute();
			  
			feesFeederStmt=feesFeederConn.prepareStatement("SELECT F1,F2 FROM tbl_reporting");
			feesFeederRs=feesFeederStmt.executeQuery();
			if(feesFeederRs.next()){
				feesDetailsPercentage.setF1(roundUp(feesFeederRs.getDouble("F1")) );
				feesDetailsPercentage.setF2(roundUp(feesFeederRs.getDouble("F2")));
			}
		}catch(Exception e){
			System.out.println("Exception in getting fees details percentage fees feeder connection"+e);
		}finally{
			close();
		}
		return feesDetailsPercentage;
	}
	
	
	public double roundUp(double value){
		double roundedValue = Math.round( value * 100.0 ) / 100.0;
		return roundedValue;
	}
	
	
	public void close(){
    	try{
    		if(feesFeederConn!=null){
    			feesFeederConn.close();
			}
			if(feesFeederStmt!=null){
				feesFeederStmt.close();	
			}
			if(feesFeederRs!=null){
				feesFeederRs.close();
		   }
		}catch(Exception e){
			System.out.println("Exception in closing fees feeder connection"+e);
		}
    }
	
}
