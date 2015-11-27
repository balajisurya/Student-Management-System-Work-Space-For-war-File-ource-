function validate(){
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	if(username==null||username==""){
		alert("Username can't be blank");
		return false;
	}
	else if(password.lenght<6){
		alert("Password must be of at least 6 characters long ");
		return false
	}
	
}


//not used