package in.jdsoft.studentmanagement.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.jdsoft.studentmanagement.controller.CourseController;
import in.jdsoft.studentmanagement.model.Courses;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		Courses course=new Courses();
		CourseController courseController=new CourseController();
		String courseName=request.getParameter("course-name");	
		int durationInyears=Integer.parseInt(request.getParameter("years"));
		int durationInsem=Integer.parseInt(request.getParameter("sems"));
		String courseDescrirption=request.getParameter("description");
		if(request.getParameter("save")!=null){
			course.setCourseName(courseName);
			course.setDurationInYears(durationInyears);
			course.setDurationInSemester(durationInsem);
			course.setCourseImage("courses.png");
			course.setCourseDescription(courseDescrirption);	
			courseController.addCourse(course);  
            response.sendRedirect("course.jsp");
		}
		
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
		int courseId=Integer.parseInt(request.getParameter("courseid"));
		Courses course=new Courses();
		CourseController courseController=new CourseController();
		course.setCourseId(courseId);
	    courseController.deleteCourse(course);
		response.sendRedirect("course.jsp");
		
	}

}
