package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.TemplateItem;

public class TemplateItemController {

static ServletContext sc;
	
	Connection temItemConn=null;
    PreparedStatement temItemStmt=null;
    ResultSet tempItemRs=null;
    ArrayList<TemplateItem> viewTemplateItem=new ArrayList<>();
    

    
	public void getDbAccess(ServletContext sc){
		 TemplateItemController.sc=sc;
	 }
	
	
	 
	
    public void addTemplateItem(TemplateItem ti){
    	try{
    	DBConnection templateItemSc=(DBConnection) sc.getAttribute("dbConn");
    	temItemConn=templateItemSc.getDBConnection();
    	temItemStmt=temItemConn.prepareStatement("INSERT INTO tbl_fees_template_item(template_item_name,template_item_price) values(?,?)");
    	temItemStmt.setString(1,ti.getTemplateItemName());
    	temItemStmt.setDouble(2, ti.getTemplateItemPrice());
    	temItemStmt.execute();
    	}catch(Exception e){ 
    		System.out.println("Exception in Add template Item of template item controller "+e);
    	}
    	finally{
    		close();
    	}
    }
    
    public ArrayList<TemplateItem> viewTemplateItem(){
    	try{
    		DBConnection templateItemSc=(DBConnection) sc.getAttribute("dbConn");
        	temItemConn=templateItemSc.getDBConnection();
        	temItemStmt=temItemConn.prepareStatement("SELECT * FROM tbl_fees_template_item ORDER BY template_item_id DESC");
        	tempItemRs=temItemStmt.executeQuery();
        	while(tempItemRs.next()){
        		TemplateItem templateitem=new TemplateItem();
        		templateitem.setTemplateItemId(tempItemRs.getInt("template_item_id"));
        		templateitem.setTemplateItemName(tempItemRs.getString("template_item_name"));
        		templateitem.setTemplateItemPrice(tempItemRs.getDouble("template_item_price"));
        		viewTemplateItem.add(templateitem);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in view template Item of template item controller "+e);
    	}finally{
    		close();
    	}
    	
    	return viewTemplateItem;
    }
    

    public void deleteTemplateItem(TemplateItem ti){
    	try{
    		DBConnection templateItemSc=(DBConnection) sc.getAttribute("dbConn");
        	temItemConn=templateItemSc.getDBConnection();
        	temItemStmt=temItemConn.prepareStatement("DELETE  FROM tbl_fees_template_item where template_item_id=?");
        	temItemStmt.setInt(1, ti.getTemplateItemId());
        	temItemStmt.executeUpdate();
    	}catch(Exception e){
    		System.out.println("Exception in delete  template Item of template item controller "+e);
    	}finally{
    		close();
    	}
    }
    
    
    public void updateTemplateItem(TemplateItem ti){
    	  try{
    		  DBConnection templateItemSc=(DBConnection) sc.getAttribute("dbConn");
          	  temItemConn=templateItemSc.getDBConnection();
          	  temItemStmt=temItemConn.prepareStatement("UPDATE tbl_fees_template_item SET template_item_name=?,template_item_price=? where template_item_id=?");
          	  temItemStmt.setString(1,ti.getTemplateItemName());
          	  temItemStmt.setDouble(2, ti.getTemplateItemPrice());
          	  temItemStmt.setInt(3,ti.getTemplateItemId());
          	  temItemStmt.executeUpdate();
    	  }catch(Exception e){
    		  System.out.println("Exception in update template Item of template item controller "+e);
    	  }finally{
    		  close();
    	  }
    }
    
    public Map<String,Object> templateItemDetailsFromId(int templateItemId){
    	HashMap<String, Object> templateItemDetails=new HashMap<>();
       try{
     	  DBConnection templateItemSc=(DBConnection) sc.getAttribute("dbConn");
       	  temItemConn=templateItemSc.getDBConnection();
       	  temItemStmt=temItemConn.prepareStatement("SELECT * FROM tbl_fees_template_item where template_item_id=?");
       	  temItemStmt.setInt(1,templateItemId);
       	  tempItemRs=temItemStmt.executeQuery();
       	  tempItemRs.next();
       	  templateItemDetails.put("template_item_name",tempItemRs.getString("template_item_name"));
       	  templateItemDetails.put("template_item_price",tempItemRs.getDouble("template_item_price"));
       }catch(Exception e){
     	  System.out.println("Exception in template Item details of template item controller"+e);
       }finally{
     	  close();
       }
       return templateItemDetails;
 }
    
    
    public void close(){
    	try{
    		if(temItemConn!=null){
				temItemConn.close();
			}
			if(temItemStmt!=null){
			  temItemStmt.close();	
			}
			if(tempItemRs!=null){
				tempItemRs.close();
		   }
		}catch(Exception e){
			System.out.println("Exception in closing template Item connection of template item controller"+e);
		}
    }
}
