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

			for (var i = 0; i < cells.length; i++) {
				cells[i].classList.add('unselectable');

			}
		} else {
			var container = document.querySelector("#itemTable");
			var cells = container.querySelectorAll('td:nth-child(12)');
			var cells1 = container.querySelectorAll('td:nth-child(11)');

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
	            
	            var totalAmount = taxableValue+igst+cgst+sgst;
	            if(!isNaN(totalAmount)) {
	            	$tblrow.find('#totalAmount').val(totalAmount.toFixed(2));
	            	ttlTotalAmount[index]=totalAmount;
	            	$("[name=ttlTotalAmount]").val(getSum(ttlTotalAmount).toFixed(2));
	            }
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
	        ttlQty[index]=checkValueNaN(parseInt($tblrow.find("#qty").val(),10));
	        ttlAmount[index]=checkValueNaN(parseInt($tblrow.find("#amount").val(),10));
	        ttlTaxableValue[index]=checkValueNaN(parseInt($tblrow.find("#taxableValue").val(),10));
	        ttlIgst[index]=checkValueNaN(parseFloat($tblrow.find("#igst").val()));
	        ttlCgst[index]=checkValueNaN(parseFloat($tblrow.find("#cgst").val()));
	        ttlSgst[index]=checkValueNaN(parseFloat($tblrow.find("#sgst").val()));
	        ttlTotalAmount[index]=checkValueNaN(parseInt($tblrow.find("#totalAmount").val(),10));
	    });
	    
	    $("[name=ttlQty]").val(getSum(ttlQty));
	    $("[name=ttlAmount]").val(getSum(ttlAmount).toFixed(2));
	    $("[name=ttlTaxableValue]").val(getSum(ttlTaxableValue).toFixed(2));
	    $("[name=ttlIgst]").val(getSum(ttlIgst).toFixed(2));
	    $("[name=ttlCgst]").val(getSum(ttlCgst).toFixed(2));
	    $("[name=ttlSgst]").val(getSum(ttlSgst).toFixed(2));
	    $("[name=ttlTotalAmount]").val(getSum(ttlTotalAmount).toFixed(2));
	    
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