package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.Currency;

public class CurrencyController {

static ServletContext sc;
	
	Connection currencyConn=null;
    PreparedStatement currencyStmt=null;
    ResultSet currencyRs=null;
    ArrayList<Currency> viewCurrency=new ArrayList<>();
 
	public void getDbAccess(ServletContext sc){
		 CurrencyController.sc=sc;
	 }
	
    public void addCurrency(Currency cu){
    	try{
    	DBConnection currencySc=(DBConnection) sc.getAttribute("dbConn");
    	currencyConn=currencySc.getDBConnection();
    	currencyStmt=currencyConn.prepareStatement("INSERT INTO tbl_currency(currenct_title,currenct_code,symbol_left,symbol_right) values(?,?,?,?)");
    	currencyStmt.setString(1,cu.getCurrencyTitle());
    	currencyStmt.setString(2,cu.getCurrencyCode());
    	currencyStmt.setString(3,cu.getSymbolLeft());
    	currencyStmt.setString(4,cu.getSymbolRight());
    	currencyStmt.execute();
    	}catch(Exception e){ 
    		System.out.println("Exception in Add currency of currency controller "+e);
    	}
    	finally{
    		close();
    	}
    }
    
    public ArrayList<Currency> viewCurrency(){
    	try{
    		DBConnection currencySc=(DBConnection) sc.getAttribute("dbConn");
        	currencyConn=currencySc.getDBConnection();
        	currencyStmt=currencyConn.prepareStatement("SELECT * FROM tbl_currency");
        	currencyRs=currencyStmt.executeQuery();
        	while(currencyRs.next()){
        		Currency currency=new Currency();
        	    currency.setCurrencyId(currencyRs.getInt("currency_id"));
        		currency.setCurrencyTitle(currencyRs.getString("currency_title"));
        		currency.setCurrencyCode(currencyRs.getString("currency_code"));
        		currency.setSymbolLeft(currencyRs.getString("symbol_left"));
        		currency.setSymbolRight(currencyRs.getString("symbol_right"));
        		viewCurrency.add(currency);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in view currency of currency controller "+e);
    	}finally{
    		close();
    	}
    	
    	return viewCurrency;
    }
    

    public void deleteCurrency(Currency cu){
    	try{
    		DBConnection currencySc=(DBConnection) sc.getAttribute("dbConn");
        	currencyConn=currencySc.getDBConnection();
        	currencyStmt=currencyConn.prepareStatement("DELETE FROM tbl_currency where currency_id=?");
        	currencyStmt.setInt(1,cu.getCurrencyId());
        	currencyStmt.executeUpdate();
    	}catch(Exception e){
    		System.out.println("Exception in delete  currency of currency controller "+e);
    	}finally{
    		close();
    	}
    }
    
    
    public void updateCurrency(Currency cu){
    	  try{
    		  DBConnection currencySc=(DBConnection) sc.getAttribute("dbConn");
          	  currencyConn=currencySc.getDBConnection();
          	  currencyStmt=currencyConn.prepareStatement("UPDATE tbl_currency SET currency_title=?,currency_code=?,symbol_left=?,symbol_right=? where currency_id=?");
          	  currencyStmt.setString(1,cu.getCurrencyTitle());
          	  currencyStmt.setString(2,cu.getCurrencyCode());
          	  currencyStmt.setString(3,cu.getSymbolLeft());
          	  currencyStmt.setString(4,cu.getSymbolRight());
          	  currencyStmt.setInt(4,cu.getCurrencyId());
          	  currencyStmt.executeUpdate();
    	  }catch(Exception e){
    		  System.out.println("Exception in update currency of currency controller "+e);
    	  }finally{
    		  close();
    	  }
    }
    
    public Map<String,Object> currencyDetailsFromId(int currencyId){
    	HashMap<String, Object> currencyDetails=new HashMap<>();
       try{
     	  DBConnection currencySc=(DBConnection) sc.getAttribute("dbConn");
       	  currencyConn=currencySc.getDBConnection();
       	  currencyStmt=currencyConn.prepareStatement("SELECT * FROM tbl_currency where currency_id=?");
       	  currencyStmt.setInt(1,currencyId);
       	  currencyRs=currencyStmt.executeQuery();
       	  currencyRs.next();
       	  currencyDetails.put("currency_title",currencyRs.getString("currency_title"));
       	  currencyDetails.put("currency_code",currencyRs.getString("currency_code"));
       	  currencyDetails.put("symbol_left",currencyRs.getString("symbol_left"));
       	  currencyDetails.put("symbol_right",currencyRs.getString("symbol_right"));
       	  
       }catch(Exception e){
     	  System.out.println("Exception in currency details of currency controller"+e);
       }finally{
     	  close();
       }
       return currencyDetails;
 }
    
    
    public void close(){
    	try{
    		if(currencyConn!=null){
				currencyConn.close();
			}
			if(currencyStmt!=null){
			  currencyStmt.close();	
			}
			if(currencyRs!=null){
				currencyRs.close();
		   }
		}catch(Exception e){
			System.out.println("Exception in closing currency connection of currency controller"+e);
		}
    }
}
