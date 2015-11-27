package in.jdsoft.studentmanagement.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.jdsoft.studentmanagement.controller.GroupController;
import in.jdsoft.studentmanagement.controller.GroupMappingController;
import in.jdsoft.studentmanagement.model.CourseSemester;
import in.jdsoft.studentmanagement.model.Group;

@WebServlet("/GroupServlet")
public class GroupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		Group group=new Group();
		GroupController groupController=new GroupController();
		GroupMappingController groupMappingController=new GroupMappingController();
		ArrayList<CourseSemester> courseSemesters=new ArrayList<>();
		
		String groupName=request.getParameter("group-name");
		String courseSemesterId[]=request.getParameterValues("courseSemList");
		
		for(String courseSemId:courseSemesterId){
			CourseSemester courseSemester=new CourseSemester();
			courseSemester.setCourseSemesterId(Integer.parseInt(courseSemId));
		    courseSemesters.add(courseSemester);	
		}
		
		if(request.getParameter("save")!=null){
			group.setGroupName(groupName);
			groupMappingController.addGroupMapping(groupController.addGroup(group), courseSemesters);
		    response.sendRedirect("groups.jsp");
		}
		
		}

	  
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
		int groupId=Integer.parseInt(request.getParameter("groupid"));
		Group group=new Group();
		GroupController groupController=new GroupController();
		group.setGroupId(groupId);
	    groupController.deleteGroup(group);
		response.sendRedirect("groups.jsp");
		
	   }
}
