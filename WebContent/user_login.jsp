<%@ page errorPage="error.jsp" %>
<% if(session.getAttribute("authenticated")!=null && session.getAttribute("authenticated").equals("true")){
	response.sendRedirect("dashboard.jsp");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- bootstrap theme -->
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="css/elegant-icons-style.css" rel="stylesheet" />
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/mycustom/loginform.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery.min.js"></script>
    
   <script type="text/javascript">
$(document).ready(function(){
$("#login_frm").submit(function(){
 
//remove previous class and add new "myinfo" class
$("#msgbox").removeClass().addClass('validating').text('Validating Your Login ').fadeIn(500);
 
this.timer = setTimeout(function () {
$.ajax({
url: 'logincheck.jsp',
data: 'user-name='+ $('#login_id').val() +'&pass-word=' + $('#password').val(),
type: 'post',
success: function(msg){
if(msg!='Failed') // Message Sent, check and redirect
{                // and direct to the success page
 $("#msgbox").html('Login Verified, Logging in.....').addClass('myinfo').fadeTo(900,1,function(){
		document.location='dashboard.jsp';
	});
}
else{
	$("#msgbox").fadeTo(200,0.1,function() //start fading the messagebox
			{
			//add message and change the class of the box and start fading
			$(this).html('Invalid Username or Password').removeClass().addClass('myerror').fadeTo(900,1);
			});
}
}
 
});
}, 1500);
return false;
});
 
});
 
</script>

</head>
  <body class="login-img3-body">
    <div class="container">
      <form class="login-form" id="login_frm" action="" method="post">        
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="input-group">
              <span class="input-group-addon"><i class="icon_profile"></i></span>
              <input type="text" id="login_id" class="form-control" placeholder="Username" autofocus name="user-name">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" id="password" class="form-control" placeholder="Password" name="pass-word">
            </div>
            <div id="msgbox"></div>
            <label class="checkbox">
                <span class="pull-right"> <a href="#"> Forgot Password?</a></span>
            </label>
            <button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
            <!-- <button class="btn btn-info btn-lg btn-block" type="submit">Signup</button> -->
        </div>
      </form>
    </div>
  </body>
</html>
