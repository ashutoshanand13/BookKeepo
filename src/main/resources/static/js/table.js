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
 var isGstValid = false;
 var shippingType ='';
 
 var controllerMap = { salesInvoice: "/home/salesinvoice", exportInvoice: "/home/exportinvoice", debitNote:"/home/debitnote", creditNote:"/home/creditnote" , purchaseOrder:"/home/addpurchaseorder"  , purchaseInvoice:"/home/addpurchaseinvoice"};
 var fileMap = { salesInvoice: "salesinvoice.pdf", exportInvoice: "exportinvoice.pdf", debitNote:"debitnote.pdf", creditNote:"creditNote.pdf", purchaseOrder:"purchaseOrder.pdf", purchaseInvoice:"purchaseInvoice.pdf" };
 
 var gstRegex = /^([0-9]{2}[a-zA-Z]{4}([a-zA-Z]{1}|[0-9]{1})[0-9]{4}[a-zA-Z]{1}([a-zA-Z]|[0-9]){3}){0,15}$/;
 
 const newTr = '<tr>            <td class="pt-3-half"><input type="text" id="srNo" name="excluded:skip" placeholder="Sr No" readonly="readonly"></td>            <td class="pt-3-half"><input list="data" id="productDesc" class="form-control" placeholder="Product Description" required="required" autocomplete="off"></td>            <td class="pt-3-half"><input type="text" id="hsnCode" name="excluded:skip" placeholder="HSN Code"></td>            <td class="pt-3-half"><input type="text" id="uom" name="excluded:skip" placeholder="UOM"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="qty" name="excluded:skip" placeholder="QTY" required="required"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="rate" name="excluded:skip" placeholder="Rate" required="required"></td>            <td class="pt-3-half"><input type="text" id="amount" name="excluded:skip" placeholder="Amount" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="discount" name="excluded:skip" placeholder="Discount" required="required"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="gstRate" name="excluded:skip" placeholder="GST Rate" required="required"></td>            <td class="pt-3-half"><input type="text" id="taxableValue" name="excluded:skip" placeholder="Taxable Value" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="cgst" name="excluded:skip" placeholder="CGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="sgst" name="excluded:skip" placeholder="SGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="igst" name="excluded:skip" placeholder="IGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="totalAmount" name="excluded:skip" placeholder="Total Amount" readonly="readonly"></td>			<td>			<figure style="display:flex;">              <span class="table-add"><img class="autoResizeImage" style="margin-right: 2px;" src="/images/add.png" alt=""></span>              <span class="table-remove"><img class="autoResizeImage" style="margin-left: 2px;" src="/images/remove.png" alt=""></span>              </figure>            </td>          </tr>';

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
	 if(!gstRegex.test(value) || value === "") {
			isGstValid=false;
		 } else {
		 isGstValid=true;
		 updateTableColumn(true);
	 }
 }

function removeTableColumnClass() {
	var container = document.querySelector("#itemTable");
	var cells = container.querySelectorAll('td');
	for (var i = 0; i < cells.length; i++) {
		cells[i].classList.remove('unselectable');
		cells[i].classList.remove('hide');
	}
}

function updateTableColumn(showAlert) {
	removeTableColumnClass();
	var gstHeader = $('#headerGstin').text();
	var gstValue = $("[name=gstinBill]").val();
	
	if(gstValue === undefined) {
		var gstValue = $("[name=partyGstin]").val();
	}

	disableColumns(gstHeader, gstValue);

	if (showAlert) {
		if (gstHeader.substring(0, 2) === gstValue.substring(0, 2)) {
			setAlert("Intra-State Form")
		} else if(gstHeader!==""&&gstValue!=="") {
			setAlert("Inter-State Form")
		}
	}
}

function disableColumns(ship, bill) {
	if (isGstValid) {
		if (ship.substring(0, 2) === bill.substring(0, 2)) {
			var container = document.querySelector("#itemTable");
			var cells = container.querySelectorAll('td:nth-child(13)');

			$("#igstData").addClass('unselectable');
			$("#igstData").addClass('hide');
			$("#ttlIgst").addClass('unselectable');
			$("#ttlIgst").addClass('hide');
			for (var i = 0; i < cells.length; i++) {
				cells[i].classList.add('unselectable');
				cells[i].classList.add('hide');
			}
		} else {
			var container = document.querySelector("#itemTable");
			var cells = container.querySelectorAll('td:nth-child(12)');
			var cells1 = container.querySelectorAll('td:nth-child(11)');
			$("#cgstData").addClass('unselectable');
			$("#sgstData").addClass('unselectable');
			$("#cgstData").addClass('hide');
			$("#sgstData").addClass('hide');
			$("#ttlSgst").addClass('unselectable');
			$("#ttlSgst").addClass('hide');
			$("#ttlCgst").addClass('unselectable');
			$("#ttlCgst").addClass('hide');

			for (var i = 0; i < cells.length; i++) {
				cells[i].classList.add('unselectable');
				cells[i].classList.add('hide');
			}

			for (var i = 0; i < cells1.length; i++) {
				cells1[i].classList.add('unselectable');
				cells1[i].classList.add('hide');
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
   var name = $(this).attr("name");
   var url = controllerMap[name];
   var fileName = fileMap[name];
   
   var f = $("#form")[0];
   
   if(name==='purchaseOrder' ||name==='purchaseInvoice'){
	   isGstValid=true;
   }
   
   if(isGstValid && f.reportValidity()) {
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
			       if(Object.keys(obj).length!==0 && Object.keys(obj).length===14)
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
							url : url,
							contentType : "application/text; charset=utf-8",
							type : 'POST',
							datatype : 'text',
							data : json,
							xhrFields : {
								responseType : "blob"
							},
							success : function(blob) {
								var link = document.createElement('a');
								link.href = window.URL.createObjectURL(blob);
								link.download = fileName;
								link.click();
								$("#form")[0].reset();
								window.scrollTo(0, 0);
								setValues();
								isGstValid=false;
							}
						});
						$('#overlay').delay(500).fadeOut();
   }
   isGstValid=false;
 });

function setValues() {

	    $tableID.find('tbody tr').each(function (index) {
	    	
	        var $tblrow = $(this);

	        $tblrow.find("#srNo").val(index+1);
	        $tblrow.on('change', function () {
	        	var product = $tblrow.find("#productDesc").val();
	        	
	        	if(product !== "") {
					$.ajax({
						type : "GET",
						contentType : "application/json",
						url : "getItemData?itemDesc=" +product,
						dataType : 'json',
						async : false,
						success : function(data) {
							$tblrow.find("[id=gstRate]").val(parseFloat(data.productGstRate).toFixed(2));
							$tblrow.find("[id=hsnCode]").val(data.productHnscode);
							$tblrow.find("[id=uom]").val(data.productUom);
							$tblrow.find("[id=discount]").val(parseFloat(data.productDiscount).toFixed(2));
							$tblrow.find("[id=rate]").val(parseFloat(data.productRate).toFixed(2));
							$tblrow.find("[id=qty]").val(parseFloat(data.productQuantity).toFixed(2));
						}
						});
	        	}
	        	
	            var qty = $tblrow.find("[id=qty]").val();
	            if(!isNaN(qty)){
	            	ttlQty[$tblrow.find("#srNo").val()-1]=parseFloat(qty);
	            	$("[name=ttlQty]").val(checkValueNaN(getSum(ttlQty).toFixed(2)));
	            }
	            var rate = $tblrow.find("[id=rate]").val();
	            var amount = parseFloat(qty) * parseFloat(rate);
	            if (!isNaN(amount)) {
	            	ttlAmount[$tblrow.find("#srNo").val()-1]=amount;
	                $tblrow.find("#amount").val(amount.toFixed(2));

	                $("[name=ttlAmount]").val(getSum(ttlAmount).toFixed(2));
	                
	            }
	            
	            var discount = $tblrow.find("[id=discount]").val();
	            var gst = $tblrow.find("[id=gstRate]").val();
	            var taxableValue = amount - parseFloat(discount);
	            
	            if(!isNaN(taxableValue)) {
	            	$tblrow.find('#taxableValue').val(taxableValue.toFixed(2));
	            	ttlTaxableValue[$tblrow.find("#srNo").val()-1]=taxableValue;
	            	$("[name=ttlTaxableValue]").val(getSum(ttlTaxableValue).toFixed(2));
	            }
	            var igst = taxableValue * (parseFloat(gst,10)/100);
	            var cgst = taxableValue * (parseFloat(gst,10)/200);
	            var sgst = taxableValue * (parseFloat(gst,10)/200);;
	            
	            if(!isNaN(igst)) {
	            	$tblrow.find('#igst').val(igst.toFixed(2));
	            	$tblrow.find('#cgst').val(cgst.toFixed(2));
	            	$tblrow.find('#sgst').val(sgst.toFixed(2));
	            	ttlIgst[$tblrow.find("#srNo").val()-1]=igst;
	            	ttlCgst[$tblrow.find("#srNo").val()-1]=cgst;
	            	ttlSgst[$tblrow.find("#srNo").val()-1]=sgst;
	            	$("[name=ttlIgst]").val(getSum(ttlIgst).toFixed(2));
	            	$("[name=ttlCgst]").val(getSum(ttlCgst).toFixed(2));
	            	$("[name=ttlSgst]").val(getSum(ttlSgst).toFixed(2));
	            	
	            }
	            
	            var totalAmount = taxableValue+igst;
	            if(!isNaN(totalAmount)) {
	            	$tblrow.find('#totalAmount').val(totalAmount.toFixed(2));
	            	ttlTotalAmount[$tblrow.find("#srNo").val()-1]=totalAmount;
	            	$("[name=ttlTotalAmount]").val(getSum(ttlTotalAmount).toFixed(2));
	            }
	            
	    	    $("[name=totalAmountBeforeTax]").val(getSum(ttlTaxableValue).toFixed(2));
	    	    $("[name=totalAddIGst]").val(getSum(ttlIgst).toFixed(2));
	    	    $("[name=totalAddSGst]").val(getSum(ttlSgst).toFixed(2));
	    	    $("[name=totalAddCGst]").val(getSum(ttlCgst).toFixed(2));
	    	    $("[name=totalTaxAmount]").val(getSum(ttlIgst).toFixed(2));
	    	    $("[name=totalAmountAfterTax]").val(getSum(ttlTotalAmount).toFixed(2));
	    	    if(parseInt($("[name=ttlTotalAmount]").val(),10)!==0 && $("[name=ttlTotalAmount]").val()!=="") {
	    		    getAmountInWords();
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


function resetValues() {
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
	    
	    $("[name=ttlQty]").val(getSum(ttlQty).toFixed(2));
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
	    
	    if(parseInt($("[name=ttlTotalAmount]").val(),10)!==0) {
	    getAmountInWords();
	    }
	    
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
	
	$.ajax({
		type : "GET",
		contentType : "text/plain",
		url : "imagebase64",			
		success : function(base64String) {
			if(!data.includes(base64String))
				$("#companylogo").removeAttr('required');
		}
		});
	
};

function getAmountInWords() {
	var amount = $("[name=ttlTotalAmount]").val();
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "getAmountInWords?amount=" + amount,
		dataType : 'json',				
		success : function(data) {
			$('#amountWords').html(data);
		}
		});
}

function setDate(data) {
	 $("[name=dateOfSupply]").val($(data).val());
	 $("[name=dateOfSupply]").focus();
	 $(data).focus();
}

$("[name=nameBill]").change(function() {
	var accountName = $("[name=nameBill]").val();
	
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "getAccountData?accountName=" + accountName,
			dataType : 'json',				
			success : function(data) {
				$("[name=nameBill").val(data.accountName);
				$("[name=nameShip").val(data.accountName);
				$("[name=addressBill").val(data.accountAddress);
				$("[name=addressShip").val(data.accountAddress);
				$("[name=gstinBill").val(data.gstin);
				$("[name=gstinShip").val(data.gstin);
				$("[name=stateBill").val(data.accountState);
				$("[name=stateShip").val(data.accountState);
				$("[name=gstinBill").focus();
				$("[name=gstinShip").focus();
				$("[name=gstinShip").blur();
			}
			});
});

$("#againstInvoicedropdown").change(function() {
	var invoiceNumber = $("#againstInvoicedropdown").val();
	
	if(invoiceNumber !== "Against Invoice") {
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "invoicedetails?invoiceNo=" + invoiceNumber,
			dataType : 'json',				
			success : function(data) {
				$("[name=invoiceDate").val(data.invoiceDate);
				$("[name=state").val(data.invoiceState);
				$("[name=reverseCharge").val(data.invoiceReverseCharge);
				$("[name=invoiceDate").blur();
			}
			});
		
	} 
	else {
		$("[name=invoiceDate").val("");
		$("[name=state").val("");
		$("[name=reverseCharge").val("");
		$("[name=invoiceDate").blur();
	}
});