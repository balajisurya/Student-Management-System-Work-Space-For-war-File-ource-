function loadReceiptDetails(studentId){
	$.get('ReceiptServlet', {
        studentId : studentId
       }, function(response) {
       	var tableBody = $('#bodyContent');
    	    tableBody.find('tr').remove();
				  $.each(response, function(index, receiptObject) {
	  				 $("#bodyContent").append('<tr>'
	  				 +'<td>'+receiptObject.receiptId+'</td>'
	  				 +'<td>'+receiptObject.studentId+'</td>'
	  			     +'<td>'+receiptObject.semester+'</td>'
	  			     +'<td>'+receiptObject.totalFees+'</td>'
	  			     +'<td>'+receiptObject.pendingFees+'</td>'
	  			     +'<td>'
                    +'<div class="form-group">'
                      +'<div class="col-sm-8">'
                        +'<input type="text" class="form-control" id="yearsem" placeholder="Amount Paying"/>'
        	           +'</div>'
            		      +'<a href="#"  data-href="#" data-id="" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirm-delete">'
                           +'<span class="glyphicon glyphicon-floppy-disk"></span> Save </a>'
                    +'</div>'
                +'</td></tr>')
	  				   //need to change based on object
	  				   
             });
				document.getElementById('FormDiv').style.display="block";		  
   });
}