<html>
<head>
<title>AJAX in Servlet using JQuery and JSON</title>
<script src="js/jquery-1.11.3.js" type="text/javascript"></script>

<script>
$(document).ready(function() {

$('#sports').change(function(event) {
        var sports = $("select#sports").val();
        $.get('TestAjax', {
                sportsName : sports
        }, function(response) {
        	document.getElementById('player').style.display="block";
        var select = $('#player');
        select.find('option').remove();
        
          $.each(response, function(index, value) {
        	  var jsonobject=value;
        	    $('<option>').val(jsonobject.studentId).text(jsonobject.firstname).appendTo(select);
        	    
        	  
         
      });
        });
        });
});
</script>
</head>
<body>
<h3>AJAX in Servlet using JQuery and JSON</h3>
        Select Favorite Sports:	
        <select id="sports">
                <option>Select Sports</option>
                <option value="Football">Football</option>
                <option value="Cricket">Cricket</option>
        </select>
        <br /> <br /> 
        Select Favorite Player:
        <select id="player" style="display: none;">
        <option value="Cricket">please select value</option>
        </select>
</body>
</html>