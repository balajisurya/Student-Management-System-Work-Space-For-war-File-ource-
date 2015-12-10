package in.jdsoft.studentmanagement.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.jdsoft.studentmanagement.controller.ReceiptController;
import in.jdsoft.studentmanagement.model.Receipt;

public class ReceiptServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {
        	   if(request.getParameter("studentId")!=null){
            	   int studentId =Integer.parseInt(request.getParameter("studentId"));
                   ReceiptController receiptController=new ReceiptController();
                   ArrayList<Receipt> receiptIdList =receiptController.getAllReceiptId(studentId);
                   ArrayList<Receipt> receipts=new ArrayList<Receipt>();
                   
                   for(Receipt receiptId:receiptIdList){
                   	receipts.add(receiptController.getReceiptDetails(receiptId.getReceiptId()));
                   }
                   
                   String json = null;
                   json = new Gson().toJson(receipts);
                   response.setContentType("application/json");
                   System.out.print(json);
                   response.getWriter().write(json);
               }
        }
}