package in.jdsoft.studentmanagement.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.jdsoft.studentmanagement.controller.TemplateItemController;
import in.jdsoft.studentmanagement.model.TemplateItem;

@WebServlet("/TemplateItemServlet")
public class TemplateItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String templateItemName=request.getParameter("template-name");
		Double templateItemPrice=Double.parseDouble(request.getParameter("item-price"));
		
		TemplateItem templateItem=new TemplateItem();
		TemplateItemController templateItemController=new TemplateItemController();
		templateItem.setTemplateItemName(templateItemName);
		templateItem.setTemplateItemPrice(templateItemPrice);
		templateItemController.addTemplateItem(templateItem);
		response.sendRedirect("templateitem.jsp");
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int templateItemId=Integer.parseInt(request.getParameter("templateitemid"));
		TemplateItem templateItem=new TemplateItem();
		TemplateItemController templateItemController=new TemplateItemController();
		templateItem.setTemplateItemId(templateItemId);
	    templateItemController.deleteTemplateItem(templateItem);
		response.sendRedirect("templateitem.jsp");
	}

}
