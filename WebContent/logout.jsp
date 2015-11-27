<%@ page errorPage="error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="in.jdsoft.studentmanagement.database.DBConnection"%>
<%

 if(session!=null){
	 session.invalidate();
	 response.sendRedirect("user_login.jsp");
 }
%>