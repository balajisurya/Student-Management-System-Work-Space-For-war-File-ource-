
<%
if(session.getAttribute("authenticated")!="true"){
	response.sendRedirect("user_login.jsp");
 }
else{%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">
    <!-- editor -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link href="css/editor/external/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="css/editor/index.css" rel="stylesheet">
    <!-- select2 -->
    <link href="css/select/select2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/switchery/switchery.min.css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="//code.jquery.com/jquery-1.8.2.min.js"></script> 
    <script type = "text/javascript"  src = "http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>  
   <title>Dashboard	</title>
   <script>
   $(document).ready(function(){
	   loadDonotGraph();
   }
   );
   </script>
 </head>
 
<body class="nav-md">

  <div class="container body">
    <div class="main_container">
    
    <%@ include file="master_menu.jsp" %>
    <%@ include file="master_header.jsp" %>
      <div class="right_col" role="main">
       <div class="">
        <div class="row">
        <%if(!session.getAttribute("type").equals("student")){%>
          <!-- pie chart donut -->
                 <div class="col-md-6 col-sm-6 col-xs-12">
                      <div class="x_panel">
                           <div class="x_title">
                               <h2>Fees Paid/Pending</h2>
                                   <div class="clearfix"></div>
                            </div>
                             <div class="x_content2">
                                 <div id="graph_donut" style="width:100%; height:300px;"></div>
                             </div>
                       </div>
                   </div>
            <!-- /Pie chart donut -->
            <!-- Pie graph -->
                        <div class="col-md-6 col-sm-6 col-xs-12">
                           <div class="x_panel">
                              <div class="x_title">
                                    <h2>Class-wise Students (Morning/Evening/Distance Learning)</h2>
                               			<div class="clearfix"></div>
                               </div>
                                <div class="x_content2">
                                    <div style="width:100%; height:300px;"> 
										<canvas id="canvas_pie"></canvas>
							        </div>
                                </div>
                               
                            </div>
                        </div>
               <!-- /Pie graph -->
                <!-- line graph -->
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Year-wise Students Admitted</h2>
                                   <div class="clearfix"></div>
                                </div>
                                 <div class="x_content2">
                                    <div id="graph_line" style="width:100%; height:300px;"></div>
                                 </div>
                               
                             </div>
                          </div>
             <!-- /line graph -->
            <!-- bar charts group -->
                  <div class="col-md-6 col-sm-6 col-xs-12">
                      <div class="x_panel">
                         <div class="x_title">
                              <h2>Results (Passed/Failed/Discontinued)</h2>
                                  <div class="clearfix"></div>
                          </div>
                           <div class="x_content2">
                                  <div id="graphx" style="width:100%; height:300px;"></div>
                            </div>
                            
                       </div>
                   </div>
              <!-- /bar charts group -->
              <!-- bar chart -->
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Year-wise Fees Collected (KShs)</h2>
                                 <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                     <div id="graph_bar" style="width:100%; height:290px;"></div>
                                </div>
                                
                            </div>
                        </div>
            <!-- /bar charts -->
 <%}else if(session.getAttribute("type").equals("student")){%>
 <!-- bar charts group -->
                  <div class="col-md-6 col-sm-6 col-xs-12">
                      <div class="x_panel">
                         <div class="x_title">
                              <h2>Your Results</h2>
                                  <div class="clearfix"></div>
                          </div>
                           <div class="x_content2">
                                  <div id="graphx" style="width:100%; height:300px;"></div>
                            </div>
                            
                       </div>
                   </div>
              <!-- /bar charts group -->
              <!-- bar chart -->
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Year-wise Fees Paid (KShs)</h2>
                                 <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                     <div id="graph_bar" style="width:100%; height:290px;"></div>
                                </div>
                                
                            </div>
                        </div>
            <!-- /bar charts -->
  <%}
%>
           </div>
        </div>
    </div>
   </div>
</div>
<script src="js/bootstrap.min.js"></script>

    <!-- chart js -->
    <script src="js/chartjs/chart.min.js"></script>

    <!-- moris js -->
    <script src="js/moris/raphael-min.js"></script>
    <script src="js/moris/morris.js"></script>
    <script src="js/mycustom/reports.js"></script>
    <script src="js/moris/donut.js"></script>
    <script src="js/moris/raphael-min.res.js"></script>
    <script src="js/moris/morris.min.res.js"></script>
    <div id="custom_notifications" class="custom-notifications dsp_none">
        <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
        </ul>
        <div class="clearfix"></div>
        <div id="notif-group" class="tabbed_notifications"></div>
    </div>

    <script src="js/bootstrap.min.js"></script>
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script>

    <!-- chart js -->
    <script src="js/chartjs/chart.min.js"></script>
    <!-- bootstrap progress js -->
    <script src="js/progressbar/bootstrap-progressbar.min.js"></script>
    <!-- icheck -->
    <script src="js/icheck/icheck.min.js"></script>
    <!-- daterangepicker -->
    <script type="text/javascript" src="js/moment.min2.js"></script>
    <script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
    <!-- sparkline -->
    <script src="js/sparkline/jquery.sparkline.min.js"></script>

    <script src="js/custom.js"></script>

    <!-- flot js -->
    <!--[if lte IE 8]><script type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->
    <script type="text/javascript" src="js/flot/jquery.flot.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.pie.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.orderBars.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.time.min.js"></script>
    <script type="text/javascript" src="js/flot/date.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.spline.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.stack.js"></script>
    <script type="text/javascript" src="js/flot/curvedLines.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.resize.js"></script>

    
    
    <script>
        NProgress.done();
   </script>
 </body>
 </html>	
<%}%>
