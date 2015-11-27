package in.jdsoft.studentmanagement.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.jdsoft.studentmanagement.controller.ReceiptController;

/**
 * Servlet implementation class GenerateServlet
 */

@WebServlet("/GenerateInvoice")
public class GenerateInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int feesTemplateId=Integer.parseInt(request.getParameter("feesTemplateId"));
		String validStudentId[]= request.getParameterValues("studentId");
		
		ReceiptController receiptController=new ReceiptController();
		if(validStudentId!=null){
			receiptController.addReceipt(validStudentId,feesTemplateId);
			response.sendRedirect("generateInvoice.jsp");
		}
		
}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}