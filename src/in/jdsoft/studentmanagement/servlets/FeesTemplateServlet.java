package in.jdsoft.studentmanagement.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.jdsoft.studentmanagement.controller.FeesTemplateController;
import in.jdsoft.studentmanagement.controller.TemplateItemController;
import in.jdsoft.studentmanagement.model.FeesTemplate;


@WebServlet("/FeesTemplateServlet")
public class FeesTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
			
			String templateName=request.getParameter("structure-name");
			String templateItemIdList[]=request.getParameterValues("templateItemList");
		   
		    
		    String lastTemplateItemIdList=templateItemIdList[templateItemIdList.length-1];
			String templateItemIds="";
			
			FeesTemplate feesTemplate=new FeesTemplate();
			FeesTemplateController feesTemplateController=new FeesTemplateController();
			TemplateItemController templateItemController=new TemplateItemController();
			
		    double template_total_amount=0.0;
		    
		    for (String templateItemId : templateItemIdList) {
		    	
		    	template_total_amount+=(Double)templateItemController.templateItemDetailsFromId(Integer.parseInt(templateItemId)).get("template_item_price");
				 
				if(!templateItemId.equals(lastTemplateItemIdList)){
				  templateItemIds=templateItemIds.concat(templateItemId+",");
				}else{
					templateItemIds=templateItemIds.concat(templateItemId);
				}
				
			}
		  
		    feesTemplate.setTemplateName(templateName);
		    feesTemplate.setTemplateItemIds(templateItemIds.trim());
		    feesTemplate.setTemplateFee(template_total_amount);
		    feesTemplateController.addFeesTemplate(feesTemplate);
		    response.sendRedirect("fees-structure.jsp");
		    
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
		int feesStructureId=Integer.parseInt(request.getParameter("feesStructureid"));
		FeesTemplate feesStructure=new FeesTemplate();
		FeesTemplateController feesStructureController=new FeesTemplateController();
		feesStructure.setTemplateId(feesStructureId);
		feesStructureController.deleteTemplate(feesStructure);
		response.sendRedirect("fees-structure.jsp");
		
		
	}


}
