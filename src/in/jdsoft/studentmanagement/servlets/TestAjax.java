package in.jdsoft.studentmanagement.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.jdsoft.studentmanagement.controller.StudentRegistrationController;
import in.jdsoft.studentmanagement.model.StudentRegistration;

public class TestAjax extends HttpServlet {

        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {

                String sportsName =request.getParameter("sportsName") ;
                System.out.println(sportsName);
                StudentRegistrationController controller=new StudentRegistrationController();
                ArrayList<StudentRegistration> list = controller.getStudentList(); 
                String json = null;

                /*if (sportsName.equals("Football")) {
                        list.add("Lionel Messi");
                        list.add("Cristiano Ronaldo");
                        list.add("David Beckham");
                        list.add("Diego Maradona");
                } else if (sportsName.equals("Cricket")) {
                        list.add("Sourav Ganguly");
                        list.add("Sachin Tendulkar");
                        list.add("Lance Klusener");
                        list.add("Michael Bevan");
                } else if (sportsName.equals("Select Sports")) {
                        list.add("Select Player");
                }*/

                json = new Gson().toJson(list);
                response.setContentType("application/json");
                System.out.print(json);
                response.getWriter().write(json);

        }
        
}