<html>

   <head>
      <title>The jQuery Example</title>
      <script type = "text/javascript" 
         src = "http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		
      <script type = "text/javascript">
         $(document).ready(function() {
            $("#driver").click(function(event){
				
            	var sports="Cricket";
               $.getJSON('result.json',{sportsName:sports}, function(jd) {
                  $('#stage').html('<p> Name: ' + jd.name + '</p>');
                  $('#stage').append('<p>Age : ' + value+ '</p>');
                  $('#stage').append('<p> Sex: ' + value+ '</p>');
               });
					
            });
         });
      </script>
   </head>
	
   <body>
	
      <p>Click on the button to load result.json file −</p>
		
      <div id = "stage" style = "background-color:#eee;">
         STAGE
      </div>
		
      <input type = "button" id = "driver" value = "Load Data" />
		
   </body>
	
</html>