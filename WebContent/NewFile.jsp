

<%@ page language="java" import="java.sql.*"%>
<jsp:useBean id="dbConn" scope="application" class="in.jdsoft.studentmanagement.database.DBConnection"/>
<%
session.setAttribute("conn", dbConn);
    Connection conn=dbConn.getDBConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("select * from tbl_login");
%>
<html>
<head>
<title>web.xml and Servlet database connectivity</title>
</head>
<body>
<%
while(rs.next())
{
 
 out.print(rs.getString("email"));
}
%>
</body>
</html>
<%
  if(conn!=null)
    conn.close();
%>

