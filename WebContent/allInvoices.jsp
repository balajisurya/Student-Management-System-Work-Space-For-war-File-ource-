<%@page import="in.jdsoft.studentmanagement.model.Receipt"%>
<%@page import="in.jdsoft.studentmanagement.controller.ReceiptController"%>
<%@page import="in.jdsoft.studentmanagement.model.StudentFeesHistory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="in.jdsoft.studentmanagement.controller.StudentFeesHistoryController"%>
<%
if(session.getAttribute("authenticated")!="true"){
	response.sendRedirect("user_login.jsp");
 }
else{
	if(!request.getParameter("studentId").equals("")){%>
	<div class="x_title">
                                    <h2><i class="fa fa-align-left"></i> Student Invoice <small>All semesters</small></h2>
                                    
                                    <div class="clearfix"></div>
                                </div>
                            
                                     <div class="x_content">
                                    
                                       <div class="col-xs-3">
                                        <!-- required for floating -->
                                        <!-- Nav tabs -->
                                        <ul class="nav nav-tabs tabs-left">
                                        <%ReceiptController receiptController=new ReceiptController();
                                         ArrayList<Receipt> receipts= receiptController.getAllReceiptId(Integer.parseInt(request.getParameter("studentId")));
                                         int dummysemestercount=1; 		
                                         for(Receipt receipt:receipts){%>
                                            <li class="active"><a href=<%out.print("#r".concat(Integer.toString(receipt.getReceiptId())));%> data-toggle="tab"><%out.print("semester".concat(Integer.toString(dummysemestercount))); %></a></li>
                                        <%dummysemestercount++;
                                        }int checkactiveReceipt=1;%>
                                         </ul>
                                    </div>

                                    <div class="col-xs-9">
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                        <%for(Receipt receipt:receipts){%>
                                        	<div class="<%if(checkactiveReceipt==1){out.print("tab-pane active");}else{out.print("tab-pane");}%>" id=<%out.print("r".concat(Integer.toString(receipt.getReceiptId())));%>>
                                                <h4>Receipt Id:<%out.print(receipt.getReceiptId()); %><a class="col-xs-9" href="studentInvoice.jsp?receiptId=<%out.print(receipt.getReceiptId());%>"><span class="glyphicon glyphicon-print" ></span> Print</a></h4>
                                                <div class="table-responsive">
                                                              						<table  class="table table-bordered" >
                                                                      					    <thead>
                                                                              					<tr class="default">
                                                                                  					<th class="text-center">Serial No</th>
                                                                                  					<th class="text-center">Fees</th>
                                                                                  					<th class="text-center" >Amount (Kshs)</th>
                                                                              					</tr>
                                                                    						</thead>
                                                             						        <tbody>
                                                             						        <%
                                                             						        StudentFeesHistoryController studentFeesHistory=new StudentFeesHistoryController();
                                                             						        ArrayList<StudentFeesHistory> feesItems=studentFeesHistory.getFeesItemsOfReceipt(receipt.getReceiptId());
                                                             						        int SNO=1;
                                                             						        for(StudentFeesHistory feesItem:feesItems){
                                                             						        %>
                                                             						        	<tr>
                                                                                          				<th class="text-center" ><%out.print(SNO); %></th>
                                                                                          				<td class="text-center"><%out.print(feesItem.getTemplateItemName());%></td>
                                                                                          				<td class="text-right"><%out.print(feesItem.getTemplateItemAmount());%></td>
																								 </tr>
                                                             						        <%SNO++;}
                                                             						        %>
																								 
																			   				</tbody>
																	                         
                                                                                      </table>
                                                    							 </div>
                                            </div>
                                        <%checkactiveReceipt++;}	
                                        %>
                                        
                                            
                                        </div>
                                    </div>

                                    <div class="clearfix"></div>
                           </div>
	
	<%
	}
 }
%>