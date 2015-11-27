package in.jdsoft.studentmanagement.servlets;

import in.jdsoft.studentmanagement.controller.StudentAcademicController;
import in.jdsoft.studentmanagement.controller.StudentRegistrationController;
import in.jdsoft.studentmanagement.model.StudentAcademic;
import in.jdsoft.studentmanagement.model.StudentRegistration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		ArrayList<StudentRegistration> studentList=(ArrayList<StudentRegistration>) request.getSession(false).getAttribute("students");
		StudentAcademicController academicController=new StudentAcademicController();
		StudentRegistrationController registrationController=new StudentRegistrationController();
		
		for(StudentRegistration student:studentList){
			
			StudentAcademic academicResult=new StudentAcademic();
			StudentAcademic newAcademic=new StudentAcademic();
			String result=request.getParameter(Integer.toString(student.getStudentId()));
			
			academicResult.setStudentId(student.getStudentId());
			academicResult.setAcademicStatus(result);
			
			if(result.equals("pass")){
				int courseId=0;
				int currentSemester=0;
				newAcademic.setStudentId(student.getStudentId());
				
						    ArrayList<StudentRegistration> studentsdetails=registrationController.getStudentDetails(student.getStudentId());
							for(StudentRegistration studentdetails:studentsdetails){
									courseId=studentdetails.getCourseId();
							}
				
				newAcademic.setCourseId(courseId);
				
							currentSemester=academicController.studentCurrentSemester(student.getStudentId())+1;
				
				newAcademic.setSemester(currentSemester);
				newAcademic.setInvoiceStatus(0);
				academicController.updateAcademicStatus(academicResult);//updating result
				academicController.addAcademics(newAcademic);//creating new semester record
				registrationController.updateCurrentSemester(currentSemester, student.getStudentId());//updating student current semester
				registrationController.updateStudentLock(1,student.getStudentId());//updating student lock
			}
			else if(result.equals("fail")){
				int courseId=0;
				int currentSemester=0;
				newAcademic.setStudentId(student.getStudentId());
				
						    ArrayList<StudentRegistration> studentsdetails=registrationController.getStudentDetails(student.getStudentId());
							for(StudentRegistration studentdetails:studentsdetails){
									courseId=studentdetails.getCourseId();
							}
				
				newAcademic.setCourseId(courseId);
				
							currentSemester=academicController.studentCurrentSemester(student.getStudentId());
				
				newAcademic.setSemester(currentSemester);
				newAcademic.setInvoiceStatus(0);
				academicController.updateAcademicStatus(academicResult);//updating result
				academicController.addAcademics(newAcademic);//creating new semester record
				registrationController.updateCurrentSemester(currentSemester, student.getStudentId());//updating student current semester
				registrationController.updateStudentLock(1,student.getStudentId());//updating student lock
			}
			else if(result.equals("discontinue")){
				academicController.updateAcademicStatus(academicResult);//updating result
				registrationController.updateStudentLock(1,student.getStudentId());//updating student lock
				registrationController.updateStudentStatus(2,student.getStudentId());//updating student lock
			}
			
		}
		response.sendRedirect("feedresults.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
