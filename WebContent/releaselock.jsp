
<%@page import="in.jdsoft.studentmanagement.controller.StudentRegistrationController"%>
<%@page import="in.jdsoft.studentmanagement.model.StudentRegistration"%>
<%@page import="in.jdsoft.studentmanagement.model.Courses"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.jdsoft.studentmanagement.controller.CourseController"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage="error.jsp" %>
<%
		if(session.getAttribute("authenticated")!="true" && session.getAttribute("type").equals("admin")){
				response.sendRedirect("user_login.jsp");
		  }
		else{
			StudentRegistrationController studentController=new StudentRegistrationController();
			studentController.releaseLock();
			response.sendRedirect("dashboard.jsp");
  		
}%>
 