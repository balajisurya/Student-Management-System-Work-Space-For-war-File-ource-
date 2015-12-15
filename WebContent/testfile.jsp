<%@ page errorPage="error.jsp" %>
<form action="testfile1.jsp" method="post">
<input name="templatename" type="text"/><br>
<input name="templateitem" type="text"/><br>
<input name="groupids" type="text"/><br>
<%out.println("hello"); %>
<div>
<input type="submit" name="action" value="Insert" />
<input type="submit" name="action" value="Delete"/>
<input type="submit" name="action" value="Update"/>
<input type="submit" name="action" value="Display"/>
</div>

</form>