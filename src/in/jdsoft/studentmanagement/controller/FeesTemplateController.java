package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.FeesTemplate;

public class FeesTemplateController {
   
	static ServletContext sc;
	
	Connection templateConn=null;
    PreparedStatement templateStmt=null;
    ResultSet templateRs=null;
    ArrayList<FeesTemplate> viewTemplate=new ArrayList<>();
    
  
    
	public void getDbAccess(ServletContext sc){
		 FeesTemplateController.sc=sc;
	 }

	
    public void addFeesTemplate(FeesTemplate ft){
    	try{
    		DBConnection templateSc=(DBConnection) sc.getAttribute("dbConn");
    		templateConn=templateSc.getDBConnection();
    		templateStmt=templateConn.prepareStatement("INSERT INTO tbl_fees_template(template_name, template_item_id,template_fee) values(?,?,?)");
    		templateStmt.setString(1,ft.getTemplateName());
    		templateStmt.setString(2,ft.getTemplateItemIds());
    		templateStmt.setDouble(3,ft.getTemplateFee());
    		templateStmt.execute();
    	   }catch(Exception e){ 
    		System.out.println("Exception in Add feesTemplate of feesTemplate controller "+e);
    	   }
           finally{
    		 close();
    	   }
      }
    
    
    public ArrayList<FeesTemplate> viewTemplates(){
    	try{
    		DBConnection templateSc=(DBConnection) sc.getAttribute("dbConn");
        	templateConn=templateSc.getDBConnection();
        	templateStmt=templateConn.prepareStatement("SELECT * FROM tbl_fees_template ORDER BY template_id DESC");
        	templateRs=templateStmt.executeQuery();
        	while(templateRs.next()){
        		FeesTemplate feesTemplate=new FeesTemplate();
        		feesTemplate.setTemplateId(templateRs.getInt("template_id"));
        		feesTemplate.setTemplateName(templateRs.getString("template_name"));
        		feesTemplate.setTemplateItemIds(templateRs.getString("template_item_id"));
        		feesTemplate.setTemplateFee(templateRs.getDouble("template_fee"));
        		viewTemplate.add(feesTemplate);
        	}
    	}catch(Exception e){
    		System.out.println("Exception in view template of fees template controller "+e);
    	}finally{
    		close();
    	}	
    	return viewTemplate;
    }
    

    public void deleteTemplate(FeesTemplate ft){
    	try{
    			DBConnection templateSc=(DBConnection) sc.getAttribute("dbConn");
            	templateConn=templateSc.getDBConnection();
            	templateStmt=templateConn.prepareStatement("DELETE FROM tbl_fees_template where template_id=?");
            	templateStmt.setInt(1,ft.getTemplateId());
            	templateStmt.executeUpdate();
    	
    	}catch(Exception e){
    		System.out.println("Exception in delete template of fees template controller "+e);
    	}finally{
    		close();
    	}
    }
    
    
    public void updateTemplate(FeesTemplate ft){
    	  try{
    		  	  
            	  DBConnection templateSc=(DBConnection) sc.getAttribute("dbConn");
              	  templateConn=templateSc.getDBConnection();
            	  templateStmt=templateConn.prepareStatement("UPDATE tbl_fees_template SET template_name=?,template_item_id=? where template_id=?");
            	  templateStmt.setString(1,ft.getTemplateName());
            	  templateStmt.setString(2,ft.getTemplateItemIds());
            	  templateStmt.setInt(3,ft.getTemplateId());
              	  templateStmt.executeUpdate();
        
    	  }catch(Exception e){
    		  System.out.println("Exception in update template of fees template controller "+e);
    	  }finally{
    		  close();
    	  }
    }
    
    
    public Map<String,Object> templateDetailsFromId(int templateId){
    	HashMap<String, Object> templateDetails=new HashMap<>();
       try{
     	  DBConnection templateSc=(DBConnection) sc.getAttribute("dbConn");
       	  templateConn=templateSc.getDBConnection();
       	  templateStmt=templateConn.prepareStatement("SELECT * FROM tbl_fees_template where template_id=?");
       	  templateStmt.setInt(1,templateId);
       	  templateRs=templateStmt.executeQuery();
       	  templateRs.next();
       	  templateDetails.put("template_name",templateRs.getString("template_name"));
       	  templateDetails.put("template_item_ids",templateRs.getString("template_item_id"));
       	  templateDetails.put("template_fee",templateRs.getDouble("template_fee"));
       	  
       }catch(Exception e){
     	  System.out.println("Exception in template details from id  "+e);
       }finally{
     	  close();
       }
       return templateDetails;
 }
    
    
    
    public void close(){
    	try{
			
			if(templateConn!=null){
				templateConn.close();
			}
			if(templateStmt!=null){
				templateStmt.close();	
			}
			if(templateRs!=null){
				templateRs.close();
		   }
		}catch(Exception e){
			System.out.println("Exception in connection closing Fees Template Controller"+e);
		}
    }
	
}
