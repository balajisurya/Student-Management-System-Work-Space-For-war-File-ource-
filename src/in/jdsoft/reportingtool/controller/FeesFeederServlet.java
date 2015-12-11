package in.jdsoft.reportingtool.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.jdsoft.reportingtool.feeders.FeesFeeder;
import in.jdsoft.reportingtool.model.Report;

/**
 * Servlet implementation class FeesFeederServlet
 */
@WebServlet("/FeesFeederServlet")
public class FeesFeederServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("working");
		FeesFeeder feesfeeder=new FeesFeeder();
		Report report=feesfeeder.getFeesDetailsPercentage();
		 String json = null;
         json = new Gson().toJson(report);
         response.setContentType("application/json");
         System.out.print(json);
         response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
