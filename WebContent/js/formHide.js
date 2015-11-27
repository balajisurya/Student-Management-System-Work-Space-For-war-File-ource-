function showDiv(){
		if(document.getElementById('FormDiv').style.display=="none"){
			alert("hai");
		document.getElementById('FormDiv').style.display="block";
		document.getElementById('ListDiv').style.display="none";
	}
	else{
		document.getElementById('FormDiv').style.display="none";
		document.getElementById('ListDiv').style.display="block";
	}
	
}

function showUserManagementDiv(){
	if(document.getElementById('addUser').style.display=="none"){
	document.getElementById('addUser').style.display="block";
	document.getElementById('dispUser').style.display="none";
}
else{
	document.getElementById('addUser').style.display="none";
	document.getElementById('dispUser').style.display="block";
}

}