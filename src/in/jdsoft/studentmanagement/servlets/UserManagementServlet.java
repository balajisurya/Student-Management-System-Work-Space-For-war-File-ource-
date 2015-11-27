package in.jdsoft.studentmanagement.servlets;



import in.jdsoft.studentmanagement.controller.UserManagementController;
import in.jdsoft.studentmanagement.model.UserManagement;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserManagementServlet")
public class UserManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 String userName=request.getParameter("user-name");
	     String userEmail=request.getParameter("user-email");
	     String userPassword=request.getParameter("user-password");
	     String userType=request.getParameter("user-type");
	     int userStatus=Integer.parseInt(request.getParameter("user-status"));
	     UserManagementController userController=new UserManagementController();
		if(request.getParameter("createuser")!=null){
			UserManagement user=new UserManagement();
			user.setUsername(userName);
			user.setUserEmail(userEmail);
			user.setPassword(userPassword);
			user.setUserType(userType);
			user.setCreatedBy(request.getSession().getAttribute("username").toString());
			Calendar calendar = Calendar.getInstance();
			java.util.Date createdDate = calendar.getTime();
			Timestamp createdDateTime = new Timestamp(createdDate.getTime());
			user.setCreatedDate(createdDateTime);
			user.setUserStatusId(userStatus);			
			userController.addUser(user);
			response.sendRedirect("usermanagement.jsp");
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
