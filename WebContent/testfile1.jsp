<%@ page errorPage="error.jsp" %>

<%@page import="in.jdsoft.studentmanagement.controller.FeesTemplateController"%>

<%@page import="in.jdsoft.studentmanagement.model.FeesTemplate"%>
<%@page import="java.util.Map"%>
<%@page import="in.jdsoft.studentmanagement.controller.TemplateItemController"%>
<%@page import="in.jdsoft.studentmanagement.model.TemplateItem"%>
<%@page import="in.jdsoft.studentmanagement.controller.CourseController"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="in.jdsoft.studentmanagement.controller.GroupController"%>
<%@page import="in.jdsoft.studentmanagement.model.Group"%>

<%
String action=request.getParameter("action");
FeesTemplate feestemplate=new FeesTemplate();
FeesTemplateController feestemplateController=new FeesTemplateController();
//TemplateGroup templateGroup=new TemplateGroup();
//TemplateGroupController templateGroupController=new TemplateGroupController();

if("Insert".equals(action)){
	String templatename=request.getParameter("templatename");
	String templateitem=request.getParameter("templateitem");
	String groupids=request.getParameter("groupids");
   	
    feestemplate.setTemplateName(templatename);
	feestemplate.setTemplateItemIds(templateitem);
	//feestemplate.setTemplateGroupIds(groupids);	
	feestemplateController.addFeesTemplate(feestemplate);
}
 else if("Delete".equals(action)){
	int id=Integer.parseInt(request.getParameter("templatename"));
    feestemplate.setTemplateId(id);
	FeesTemplateController obj1=new FeesTemplateController();
	obj1.deleteTemplate(feestemplate);
	out.println("Deleted");
}else if("Update".equals(action)){
	String name=request.getParameter("templatename");
	
	String template[]=name.split(",");
	
	String templateName=template[0];
	int templateId=Integer.parseInt(template[1]);
	
	String templateitem=request.getParameter("templateitem") ;
	String templateGrou=request.getParameter("groupids");
	
	feestemplate.setTemplateId(templateId);
	feestemplate.setTemplateName(templateName);
	feestemplate.setTemplateItemIds(templateitem);
	//feestemplate.setTemplateGroupIds(templateGrou);
	
	FeesTemplateController obj1=new FeesTemplateController();
	
	obj1.updateTemplate(feestemplate);
	out.println("updated");
}
else if("Display".equals(action)){
	FeesTemplateController fees=new FeesTemplateController();
	
	ArrayList<FeesTemplate> groups=fees.viewTemplates();
	Iterator<FeesTemplate> itr=groups.iterator();
	for(FeesTemplate grou:groups){
	   out.println(grou.getTemplateId());
	   out.println("</br>");
	   out.println(grou.getTemplateName());
	   out.println("</br>");
	   out.println(grou.getTemplateItemIds());
	   out.println("</br>");
	   //out.println(grou.getTemplateGroupIds());
	   out.println("</br>");
	   out.println("</br>");
	   out.println("</br>");
	   
	   /*Map<String, Object> details =obj3.templateItemDetailsFromId(grou.getTemplateItemId());
	   
	   out.println(details.get("template_name"));
	   out.println("</br>");
	   out.println(details.get("template_item_price"));
	   out.println("</br>");
	   out.println(details.get("template_currency_id"));
	   out.println("</br>");*/
	}
	//out.println("displayed");
}
%>