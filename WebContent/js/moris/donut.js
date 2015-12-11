function loadDonotGraph(){
	alert("hai");
	$(function () {
		
		var paidfees=65;
	    var pendingfees=66;
	    
	    $.getJSON('FeesFeederServlet', function(jd) {
	    	
	        paidfees=jd.F1;
	        pendingfees=jd.F2;
	        
	        Morris.Donut({
		        element: 'graph_donut',
		        data: [
		            {label: 'PaidFees', value: paidfees},
		            {label: 'PendingFees', value: pendingfees}
		        ],
		resize: true,
		        colors: ['#26B99A', '#34495E', '#ACADAC', '#3498DB'],
		        formatter: function (y) {
		            return y + "%"
		        }

		    });
	     });
	});
}


