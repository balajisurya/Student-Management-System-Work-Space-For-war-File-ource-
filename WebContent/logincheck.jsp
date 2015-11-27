<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Calendar"%>
<%@page import="in.jdsoft.studentmanagement.model.UserManagement"%>
<%@page import="in.jdsoft.studentmanagement.controller.UserManagementController"%>
<%@ page errorPage="error.jsp" %>
<%@page import="in.jdsoft.studentmanagement.model.Login"%>
<%@page import="in.jdsoft.studentmanagement.controller.LoginController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    
    LoginController login=new LoginController();
    if(login.checkUser(request.getParameter("user-name"), request.getParameter("pass-word"))){
       Login logindetails= new Login();
       UserManagementController userManagement=new UserManagementController();
       UserManagement user=new UserManagement();
      
       
       if(logindetails.getUserStatus()==1){
    	   session.setAttribute("username",request.getParameter("user-name"));
           session.setAttribute("name",userManagement.getUserDetails(request.getParameter("user-name")).get("USER_NAME").toString());
           session.setAttribute("type",userManagement.getUserDetails(request.getParameter("user-name")).get("USER_TYPE").toString());
           session.setAttribute("lastlogin",userManagement.getUserDetails(request.getParameter("user-name")).get("LAST_LOGIN").toString());
           session.setAttribute("authenticated","true");
           Calendar calendar = Calendar.getInstance();
   		   java.util.Date createdDate = calendar.getTime();
   		   Timestamp loginTime = new Timestamp(createdDate.getTime());
           user.setLastLogin(loginTime);
           user.setUserEmail(request.getParameter("user-name"));
           userManagement.updateLoginTime(user);
           response.sendRedirect("dashboard.jsp");
           
       }
       else{
    	   out.print("Invalid User");
    	          }
    }
    else{
    	out.print("failed");
    }
    %>