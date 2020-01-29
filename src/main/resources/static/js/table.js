 const $tableID = $('#table');
 const $BTN = $('#export-btn');
 const $EXPORT = $('#export');
 var $tblrows = $("#itemTable tbody tr");
 var ttlAmount = [];
 var ttlQty = [];
 var ttlTaxableValue = [];
 var ttlCgst = [];
 var ttlIgst = [];
 var ttlSgst = [];
 var ttlTotalAmount = [];
 var discount = [];
 var gstBill = false;
 var gstShip = false;
 var shippingType ='';
 
 var gstRegex = /^([0-9]{2}[a-zA-Z]{4}([a-zA-Z]{1}|[0-9]{1})[0-9]{4}[a-zA-Z]{1}([a-zA-Z]|[0-9]){3}){0,15}$/;
 
 const newTr = '<tr>            <td class="pt-3-half"><input type="text" id="srNo" name="excluded:skip" placeholder="Sr No" readonly="readonly"></td>            <td class="pt-3-half"><textarea cols="40" rows="5" id="productDesc" style="height:60px;" name="excluded:skip" placeholder="Product Description"></textarea></td>            <td class="pt-3-half"><input type="text" id="hsnCode" name="excluded:skip" placeholder="HSN Code"></td>            <td class="pt-3-half"><input type="text" id="uom" name="excluded:skip" placeholder="UOM"></td>            <td class="pt-3-half"><input type="text" id="qty" name="excluded:skip" placeholder="QTY"></td>            <td class="pt-3-half"><input type="text" id="rate" name="excluded:skip" placeholder="Rate"></td>            <td class="pt-3-half"><input type="text" id="amount" name="excluded:skip" placeholder="Amount" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="discount" name="excluded:skip" placeholder="Discount"></td>            <td class="pt-3-half"><input type="text" id="gstRate" name="excluded:skip" placeholder="GST Rate"></td>            <td class="pt-3-half"><input type="text" id="taxableValue" name="excluded:skip" placeholder="Taxable Value" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="cgst" name="excluded:skip" placeholder="CGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="sgst" name="excluded:skip" placeholder="SGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="igst" name="excluded:skip" placeholder="IGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="totalAmount" name="excluded:skip" placeholder="Total Amount" readonly="readonly"></td>			<td>			<figure style="display:flex;">              <span class="table-add"><img class="autoResizeImage" style="margin-right: 2px;" src="/images/add.png" alt=""></span>              <span class="table-remove"><img class="autoResizeImage" style="margin-left: 2px;" src="/images/remove.png" alt=""></span>              </figure>            </td>          </tr>';

 $tableID.on('click', '.table-remove', function () {
if ($tableID.find('tbody tr').length !== 1) {
   $(this).parents('tr').detach();
   
   resetValues();
   setValues();
}
else
	{
	alert("Cannot Remove Last Row");
	}
 });
 
 $tableID.on('click', '.table-add', function () {
	 $(this).closest('tr').after(newTr);
	   resetValues();
	   setValues();
	   updateTableColumn(false);
	  });

function validateGST(val){
	removeTableColumnClass();
	 var value = $(val).val();
	 if(!gstRegex.test(value) || value === "")
		 {
			 if($(val).attr("name").includes("Bill")) {
				 gstBill=false;
			 }
			 else{
				 gstShip=false;
			 }
		 }
	 else {
		 if($(val).attr("name").includes("Bill")) {
			 gstBill=true;
		 }
		 else{
			 gstShip=true;
		 } 
		 updateTableColumn(true);
	 }
 }

function removeTableColumnClass() {
	var container = document.querySelector("#itemTable");
	var cells = container.querySelectorAll('td');
	for (var i = 0; i < cells.length; i++) {
		cells[i].classList.remove('unselectable');
	}
}

function updateTableColumn(showAlert) {
	removeTableColumnClass();
	var gstShipValue = $("[name=gstinBill]").val();
	var gstBillValue = $("[name=gstinShip]").val();

	disableColumns(gstShipValue, gstBillValue);

	if (showAlert) {
		if (gstShipValue.substring(0, 2) === gstBillValue.substring(0, 2)) {
			setAlert("Intra-State Form")
		} else if(gstShipValue!==""&&gstBillValue!=="") {
			setAlert("Inter-State Form")
		}
	}
}

function disableColumns(ship, bill) {
	if (gstBill && gstShip) {
		if (ship.substring(0, 2) === bill.substring(0, 2)) {
			var container = document.querySelector("#itemTable");
			var cells = container.querySelectorAll('td:nth-child(13)');

			$("#igstData").addClass('unselectable');
			for (var i = 0; i < cells.length; i++) {
				cells[i].classList.add('unselectable');

			}
		} else {
			var container = document.querySelector("#itemTable");
			var cells = container.querySelectorAll('td:nth-child(12)');
			var cells1 = container.querySelectorAll('td:nth-child(11)');
			$("#cgstData").addClass('unselectable');
			$("#sgstData").addClass('unselectable');

			for (var i = 0; i < cells.length; i++) {
				cells[i].classList.add('unselectable');

			}

			for (var i = 0; i < cells1.length; i++) {
				cells1[i].classList.add('unselectable');

			}
		}
	}
}

function setAlert(message) {
	$('#alert_placeholder').html(
			'<div class="alert alert-info table-width fade-in"><span><center>'
					+ message + '</center></span></div>')
	$("#alert_placeholder").fadeTo(2000, 500).slideUp(500);
}

 $BTN.on('click', function () {
   var json = '';
   
   var f = $("#form")[0];
   
   if(gstBill && gstShip && f.reportValidity()) {
		$('#tableJson table').map(function(i, table){
			   var $rows = $("#" +table.id).find('tr:not(:hidden)');
			   var newFormData = [];
			   jQuery( $("#" +table.id).find('tr:not(:first), tr:not(:last)')).each(function(i) {
			       var tb = jQuery(this);
			       var obj = {};
			       tb.find('textarea,input').each(function() {
			    	   if(this.id!=="")
			         obj[this.id] = this.value;
			       });
			       if(Object.keys(obj).length!==0)
			       newFormData.push(obj);
			     });
			   $('#itemList').val(JSON.stringify(newFormData));
		});
		// replace function used to remove extra "" while parsing.
						var json = JSON.stringify($('#form').serializeJSON())
								.replace(/\\/g, "").replace("\"[", "[")
								.replace("]\"", "]");
						
						$('#overlay').fadeIn();
						$.ajax({
							url : "/home/salesinvoice",
							contentType : "application/text; charset=utf-8",
							type : 'POST',
							datatype : 'text',
							data : json,
							xhrFields : {
								responseType : "blob"
							},
							success : function(blob) {
								var filename = "invoice.pdf";
								var link = document.createElement('a');
								link.href = window.URL.createObjectURL(blob);
								link.download = "invoice.pdf";
								link.click();
								$("#form")[0].reset();
								window.scrollTo(0, 0);
								setValues();
								gstShip=false;
								gstBill=false;
								$('#overlay').delay(500).fadeOut();
							}
						});
   }
 });

function setValues() {

	    $tableID.find('tbody tr').each(function (index) {
	    	
	        var $tblrow = $(this);

	        $tblrow.find("#srNo").val(index+1);
	        $tblrow.on('change', function () {
	            var qty = $tblrow.find("[id=qty]").val();
	            if(!isNaN(qty)){
	            	ttlQty[index]=parseFloat(qty);
	            	$("[name=ttlQty]").val(getSum(ttlQty));
	            }
	            var rate = $tblrow.find("[id=rate]").val();
	            var amount = parseInt(qty, 10) * parseFloat(rate);
	            if (!isNaN(amount)) {
	            	ttlAmount[index]=amount;
	                $tblrow.find("#amount").val(amount.toFixed(2));

	                $("[name=ttlAmount]").val(getSum(ttlAmount).toFixed(2));
	                
	            }
	            
	            var discount = $tblrow.find("[id=discount]").val();
	            var gst = $tblrow.find("[id=gstRate]").val();
	            var taxableValue = amount - parseFloat(discount);
	            
	            if(!isNaN(taxableValue)) {
	            	$tblrow.find('#taxableValue').val(taxableValue.toFixed(2));
	            	ttlTaxableValue[index]=taxableValue;
	            	$("[name=ttlTaxableValue]").val(getSum(ttlTaxableValue).toFixed(2));
	            }
	            var igst = taxableValue * (parseFloat(gst,10)/100);
	            var cgst = taxableValue * (parseFloat(gst,10)/200);
	            var sgst = taxableValue * (parseFloat(gst,10)/200);;
	            
	            if(!isNaN(igst)) {
	            	$tblrow.find('#igst').val(igst.toFixed(2));
	            	$tblrow.find('#cgst').val(cgst.toFixed(2));
	            	$tblrow.find('#sgst').val(sgst.toFixed(2));
	            	ttlIgst[index]=igst;
	            	ttlCgst[index]=cgst;
	            	ttlSgst[index]=sgst;
	            	$("[name=ttlIgst]").val(getSum(ttlIgst).toFixed(2));
	            	$("[name=ttlCgst]").val(getSum(ttlCgst).toFixed(2));
	            	$("[name=ttlSgst]").val(getSum(ttlSgst).toFixed(2));
	            	
	            }
	            
	            var totalAmount = taxableValue+igst;
	            if(!isNaN(totalAmount)) {
	            	$tblrow.find('#totalAmount').val(totalAmount.toFixed(2));
	            	ttlTotalAmount[index]=totalAmount;
	            	$("[name=ttlTotalAmount]").val(getSum(ttlTotalAmount).toFixed(2));
	            }
	            
	    	    $("[name=totalAmountBeforeTax]").val(getSum(ttlTaxableValue).toFixed(2));
	    	    $("[name=totalAddIGst]").val(getSum(ttlIgst).toFixed(2));
	    	    $("[name=totalAddSGst]").val(getSum(ttlSgst).toFixed(2));
	    	    $("[name=totalAddCGst]").val(getSum(ttlCgst).toFixed(2));
	    	    $("[name=totalTaxAmount]").val(getSum(ttlIgst).toFixed(2));
	    	    $("[name=totalAmountAfterTax]").val(getSum(ttlTotalAmount).toFixed(2));
	        });
	        
	    });
	    
	}

function getSum(arr) {
	var total = 0;
	for (var i = 0; i < arr.length; i++) {
	    total += arr[i];
	}
	return total;
}


function resetValues(){
	 ttlAmount = [];
	 ttlQty = [];
	 ttlTaxableValue = [];
	 ttlCgst = [];
	 ttlIgst = [];
	 ttlSgst = [];
	 ttlTotalAmount = [];
	 $tableID.find('tbody tr').each(function (index) {
	        var $tblrow = $(this);
	        $tblrow.find("#srNo").val(index+1);
	        ttlQty[index]=checkValueNaN(parseFloat($tblrow.find("#qty").val()));
	        ttlAmount[index]=checkValueNaN(parseFloat($tblrow.find("#amount").val()));
	        ttlTaxableValue[index]=checkValueNaN(parseFloat($tblrow.find("#taxableValue").val()));
	        ttlIgst[index]=checkValueNaN(parseFloat($tblrow.find("#igst").val()));
	        ttlCgst[index]=checkValueNaN(parseFloat($tblrow.find("#cgst").val()));
	        ttlSgst[index]=checkValueNaN(parseFloat($tblrow.find("#sgst").val()));
	        ttlTotalAmount[index]=checkValueNaN(parseFloat($tblrow.find("#totalAmount").val()));
	    });
	    
	    $("[name=ttlQty]").val(getSum(ttlQty));
	    $("[name=ttlAmount]").val(getSum(ttlAmount).toFixed(2));
	    $("[name=ttlTaxableValue]").val(getSum(ttlTaxableValue).toFixed(2));
	    $("[name=ttlIgst]").val(getSum(ttlIgst).toFixed(2));
	    $("[name=ttlCgst]").val(getSum(ttlCgst).toFixed(2));
	    $("[name=ttlSgst]").val(getSum(ttlSgst).toFixed(2));
	    $("[name=ttlTotalAmount]").val(getSum(ttlTotalAmount).toFixed(2));
	    
	    $("[name=totalAmountBeforeTax]").val(getSum(ttlTaxableValue).toFixed(2));
	    $("[name=totalAddIGst]").val(getSum(ttlIgst).toFixed(2));
	    $("[name=totalAddSGst]").val(getSum(ttlSgst).toFixed(2));
	    $("[name=totalAddCGst]").val(getSum(ttlCgst).toFixed(2));
	    $("[name=totalTaxAmount]").val(getSum(ttlIgst).toFixed(2));
	    $("[name=totalAmountAfterTax]").val(getSum(ttlTotalAmount).toFixed(2));
	    
	    
}

function checkValueNaN(value) {
	var val=0;
	if(!isNaN(value))
		val=value;
	
	return val;
}

var _URL = window.URL || window.webkitURL;

$("#companylogo").change(function(e) {
    var file, img;
    if ((file = this.files[0])) {
        img = new Image();
        img.onload = function() {
        	if(this.width>400 || this.height>400){
        		alert("Image resolution should be within 400x400");
        		document.getElementById("companylogo").value='';
        		document.getElementById('companylogopreview').src = "/images/image-400x400.jpg";
        	}else{
        		var input = $("#companylogo")[0];
        		readURL(input);
        	}
        };
        img.onerror = function() {
            alert( "Please upload valid image " + file.type);
            document.getElementById("companylogo").value='';
        };
        img.src = _URL.createObjectURL(file);
    }
});

function readURL(input) {
		if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    	reader.onload = function(e) {
	  	      $('#companylogopreview').attr('src', e.target.result);
	  	    }
	  	    reader.readAsDataURL(input.files[0]);
		}
}

function showAlert() {
	$('#alert_showinvoice').html(
			'<div class="alert alert-info fade-in"><span><center>Invoice Downloaded</center></span></div>')
	$("#alert_showinvoice").fadeTo(2000, 500).slideUp(500);
}

$("#registrationform").submit(function () {
    if ($('#password').val() == $('#confirmpassword').val()) {
        $('#messagePassword').html('Passwords Match').css('color', 'green');
        return true;
    } else 
        $('#messagePassword').html('Passwords Not Matching').css('color', 'red');
    	return false;
});


$("#changepasswordform").submit(function () {
	if($('#oldpassword').val()!=''){
    if ($('#password').val() == $('#confirmpassword').val()) {
        $('#messagePassword').html('Passwords Match').css('color', 'green');
        return true;
    } else {
        $('#messagePassword').html('Passwords Not Matching').css('color', 'red');
    	return false;
    	}
	}else{
		 $('#messagePassword').html('Please enter old password').css('color', 'red');
	}
});


function checkifImageExists(data) {
	if(!data.includes('/9j/4AAQSkZJRgABAQEAYABgAAD/4QB2RXhpZgAATU0AKgAAAAgABFEAAAQAAAABAAAAAFEBAAMAAAABAAEAAFECAAEAAAAwAAAAPlEDAAEAAAABAAAAAAAAAADd3d2Wlpa5ubnU1NTCwsKenp6wsLCnp6fLy8sAAAAAAAAAAAAAAAAAAAAAAAAAAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/2wBDAQICAgMDAwYDAwYMCAcIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAGQAZADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9dKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAoooo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kVgCAwDAjOCRn3NS6Ro9n4f0+OzsLW2sbWHOyG3iEcaZJJwqgAZJJ+pNAHzT+1P4L1L4afF6z8c6bC0lrNNFO7Bfkhnj2ja2OzhQcnqSwrM+Ov7Q7ftAaZpPh/QdKv42lnWWWNsNJNLghUULnKjJOTjPHAxX1dc20d7bvDNHHNFICro6hlYHqCD1rP0TwNonhm5abTdH0vT5n4Z7a0jiZvqVANAGR8Hfh0vw5+F2m6HNslkjhJuuPleRyWce4BYqD3AFfN/h/ULz9kD44XS31rc3Wk3CPCrKMG5gJDI6Z4LKQMjPXcMjrX13VPWvD+n+JLT7PqNjZ6hb53eXcwrKmfXDAigD5Q8Qaheftf8AxwtVsbW5tdJt0SFmYZNtACWd3xwGYk4Geu0ZPWvrmGFbeFY41CxxgKqjoAOgqronh3T/AA1a+RptjZ6fCTkx20CxKT9FAFXKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAP/Z'))
	document.getElementById("companylogo").required = false;
};