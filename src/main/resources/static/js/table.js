 const $tableID = $('#table');
 const $tableBOSID = $('#tableBOS');
 const $invoiceTable = $('#invoiceTable');
 
 const $BTN = $('#export-btn');
 const $EXPORT = $('#export');
 var $tblrows = $("#itemTable tbody tr");
 
 //For Invoice pages only
 var ttlAmount = [];
 var ttlQty = [];
 var ttlTaxableValue = [];
 var ttlCgst = [];
 var ttlIgst = [];
 var ttlSgst = [];
 var ttlTotalAmount = [];
 var discount = [];
 
 // For payment and receipt only
 var ttlPayment = [];
 var invoiceList = [];
 var dropdown = []
 
 var shippingType ='';
 var tableIndex = 0;

 
 var controllerMap = { salesInvoice: "/home/submitInvoice", exportInvoice: "/home/submitInvoice", debitNote:"/home/submitInvoice", creditNote:"/home/submitInvoice" , purchaseOrder:"/home/submitInvoice"  , purchaseInvoice:"/home/submitInvoice", billOfSupply:"/home/submitInvoice", retailInvoice:"/home/submitInvoice"};
 var fileMap = { salesInvoice: "Tax_Invoice", exportInvoice: "Export_Invoice", debitNote:"Debit_Note", creditNote:"Credit_Note", purchaseOrder:"Purchase_Order", purchaseInvoice:"Purchase_Invoice", billOfSupply:"Bill_Of_Supply", retailInvoice:"Retail_Invoice" };
 
 var gstCodeStateMap = {"01":"JAMMU & KASHMIR","02":"HIMACHAL PRADESH	","03":"PUNJAB","04":"CHANDIGARH","05":"UTTARAKHAND","06":"DELHI","08":"RAJASTHAN","09":"UTTAR PRADESH","10":"BIHAR","11":"SIKKIM","12":"ARUNACHAL PRADESH","13":"NAGALAND","14":"MANIPUR","15":"MIZORAM","16":"TRIPURA","17":"MEGHLAYA","18":"ASSAM","19":"WEST BENGAL","20":"JHARKHAND","21":"ODISHA","22":"CHATTISGARH","22":"MADHYA PRADESH","24":"GUJARAT","25":"DAMAN AND DIU","26":"DADRA AND NAGAR HAVELI","27":"MAHARASHTRA",        "28":"ANDHRA PRADESH (Old)","29":"KARNATAKA","30":"GOA","31":"LAKSHWADEEP","32":"KERALA","33":"TAMIL NADU","34":"PUDUCHERRY","35":"ANDAMAN AND NICOBAR ISLANDS","36":"TELANGANA","37":"ANDHRA PRADESH (New)","38":"LADAKH","-1":"OTHER" }
 
 var gstRegex = /^([0-9]{2}[a-zA-Z]{4}([a-zA-Z]{1}|[0-9]{1})[0-9]{4}[a-zA-Z]{1}([a-zA-Z]|[0-9]){3}){0,15}$/;
 
 const newTr = '<tr>            <td class="pt-3-half"><input type="text" id="srNo" name="excluded:skip" placeholder="Sr No" readonly="readonly"></td>            <td class="pt-3-half"><input list="data" id="productDesc" class="form-control" placeholder="Product Description" required="required" autocomplete="off"></td>            <td class="pt-3-half"><input type="text" id="hsnCode" name="excluded:skip" placeholder="HSN Code"></td>            <td class="pt-3-half"><input type="text" id="uom" name="excluded:skip" placeholder="UOM"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="qty" name="excluded:skip" placeholder="QTY" required="required"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="rate" name="excluded:skip" placeholder="Rate" required="required"></td>            <td class="pt-3-half"><input type="text" id="amount" name="excluded:skip" placeholder="Amount" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="discount" name="excluded:skip" placeholder="Discount" required="required"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="gstRate" name="excluded:skip" placeholder="GST Rate" required="required"></td>            <td class="pt-3-half"><input type="text" id="taxableValue" name="excluded:skip" placeholder="Taxable Value" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="cgst" name="excluded:skip" placeholder="CGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="sgst" name="excluded:skip" placeholder="SGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="igst" name="excluded:skip" placeholder="IGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="totalAmount" name="excluded:skip" placeholder="Total Amount" readonly="readonly"></td>			<td>			<figure style="display:flex;">              <span class="table-add"><img class="autoResizeImage" style="margin-right: 2px;" src="/images/add.png" alt=""></span>              <span class="table-remove"><img class="autoResizeImage" style="margin-left: 2px;" src="/images/remove.png" alt=""></span>              </figure>            </td>          </tr>';
 
 const newBOSTr = '<tr>            <td class="pt-3-half"><input type="text" id="srNo" name="excluded:skip" placeholder="Sr No" readonly="readonly"></td>            <td class="pt-3-half"><input list="data" id="productDesc" class="form-control" placeholder="Product Description" required="required" autocomplete="off"></td>            <td class="pt-3-half"><input type="text" id="hsnCode" name="excluded:skip" placeholder="HSN Code"></td>            <td class="pt-3-half"><input type="text" id="uom" name="excluded:skip" placeholder="UOM"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="qty" name="excluded:skip" placeholder="QTY" required="required"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="rate" name="excluded:skip" placeholder="Rate" required="required"></td>            <td class="pt-3-half"><input type="text" id="amount" name="excluded:skip" placeholder="Amount" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" onfocus="(this.type="number")" onblur="(this.type="text")" min="0" id="discount" name="excluded:skip" placeholder="Discount" required="required"></td>            <td class="pt-3-half"><input type="text" id="totalAmount" name="excluded:skip" placeholder="Total Amount" readonly="readonly"></td>			<td>			<figure style="display:flex;">              <span class="table-add"><img class="autoResizeImage" style="margin-right: 2px;" src="/images/add.png" alt=""></span>              <span class="table-remove"><img class="autoResizeImage" style="margin-left: 2px;" src="/images/remove.png" alt=""></span>              </figure>            </td>          </tr>';
 
 $tableID.on('click', '.table-remove', function () {
if ($tableID.find('tbody tr').length !== 1) {
   $(this).parents('tr').detach();
   
   resetValues();
   setValues();
}
else
	{
	createConfirmationMessageModal("Cannot Remove Last Row");
	}
 });
 
 $tableID.on('click', '.table-add', function () {
	 $(this).closest('tr').after(newTr);
	   resetValues();
	   setValues();
	   updateTableColumn(false);
	  });
	  

 $tableBOSID.on('click', '.table-add', function () {
	 $(this).closest('tr').after(newBOSTr);
	   resetBOSValues();
	   setBOSValues();
	  });
 
 
 $tableBOSID.on('click', '.table-remove', function () {
	 if ($tableBOSID.find('tbody tr').length !== 1) {
	    $(this).parents('tr').detach();
	       
	    setBOSValues();
	    resetBOSValues();
	 }
	 else
	 	{
		 createConfirmationMessageModal("Cannot Remove Last Row");
	 	}
	  });

function validateGST(val){
	removeTableColumnClass();
	updateTableColumn(true);
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
	var state = $("[name=stateBill]").val();
	
	if(gstValue !== "") {
		if (gstHeader.substring(0, 2) !== gstValue.substring(0, 2)) {
			disableColumns("Inter");
		} else {
			disableColumns("Intra");
		}
	} else if (state !== ""){
		if (gstHeader.substring(0, 2) !== getKeyByValue(state)) {
			disableColumns("Inter");
		} else {
			disableColumns("Intra");
		}
	}
	

	if (showAlert) {
		if(gstValue !== "") {
			if (gstHeader.substring(0, 2) === gstValue.substring(0, 2)) {
				setAlert("Intra-State Form")
			} else if(gstHeader !== "" && gstValue !== "") {
				setAlert("Inter-State Form")
			}
		} else if (state !== "") {
			if (gstHeader.substring(0, 2) === getKeyByValue(state)) {
				setAlert("Intra-State Form")
			} else if(gstHeader !== "" && getKeyByValue(state) !== "") {
				setAlert("Inter-State Form")
			}
		}
	}
}

function getKeyByValue(searchValue) {
	  return Object.keys(gstCodeStateMap).find(key => gstCodeStateMap[key] === searchValue);
	}

function disableColumns(subType) {
		if (subType === "Intra") {
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

function setAlert(message) {
	$('#alert_placeholder').html(
			'<div class="alert alert-info table-width fade-in"><span><center>'
					+ message + '</center></span></div>')
	$("#alert_placeholder").fadeTo(2000, 500).slideUp(500);
}

function submitHandler(e){
	e.preventDefault();
	}

 $BTN.on('click', function () {
	 
	 document.querySelector('form').addEventListener('submit', submitHandler);
   var json = '';
   var name = $(this).attr("name");
   var url = controllerMap[name];
   var fileName = fileMap[name];
   fileName = fileName+"_"+$("[name=invoiceNo]").val()+".pdf";

   var f = $("#form")[0];
   if(f.reportValidity()) {
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
						debugger;
						$('#overlay').fadeIn();
						
						$.ajax({
							url : url,
							async : true,
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
								f.reset();
								$('input').focus();
								$('input').blur();
								if(name==="billOfSupply") {
									setBOSValues();
								} else {
									setValues();
								}
								window.scrollTo(0, 0);
								$('#bankDiv').empty();
							}
						});
						$('#overlay').delay(500).fadeOut();
						$('#custom_alert').html(
								'<div class="alert alert-info text-center table-width fade-in" role="alert">'
								  +'Invoice successfully saved. View all invoices <a href="/home/showInvoice" class="alert-link">here</a>.'+
								  '</div>');
						$("#custom_alert").fadeTo(5000, 500).slideUp(500);
   }
 });

function setValues() {

	updateTableColumn(false);
	    $tableID.find('tbody tr').each(function (index) {
	    	
	        var $tblrow = $(this);

	        $tblrow.find("#srNo").val(index+1);
	        $tblrow.on('change', function () {
	        	var product = $tblrow.find("#productDesc").val();
	        	
	        	if(product !== "0") {
					$.ajax({
						type : "GET",
						contentType : "application/json",
						url : "/home/getItemData?itemId=" +product,
						dataType : 'json',
						async : false,
						success : function(data) {
							$tblrow.find("[id=gstRate]").val(parseFloat(data.productGstRate).toFixed(2));
							$tblrow.find("[id=hsnCode]").val(data.productHnscode);
							$tblrow.find("[id=uom]").val(data.productUom);
							$tblrow.find("[id=discount]").val(parseFloat(data.productDiscount).toFixed(2));
							$tblrow.find("[id=rate]").val(parseFloat(data.productRate).toFixed(2));
							$tblrow.find("[id=qty]").val(parseFloat(data.productQuantity).toFixed(2));
							$tblrow.find("[id=productDesc]").val(data.productDescription);
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
		if(arr[i] !== undefined){
	    	total += arr[i];
	}
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
        	if(this.width>2000 || this.height>2000){
        		alert("Image resolution should be within 2000px x 2000px");
        		if(!document.getElementById("companylogo").value){
        		document.getElementById('companylogopreview').src = "/images/image-400x400.jpg";
        		}
        		document.getElementById("companylogo").value='';
        	}else{
        		var input = $("#companylogo")[0];
        		readURL(input);
        	}
        };
        img.onerror = function() {
        	createConfirmationMessageModal( "Please upload valid image " + file.type);
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
		url : "/home/getAmountInWords?amount=" + amount,
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

$("[name=accountNo]").change(function() {
	var accountNo = $("[name=accountNo]").val();
	
	if(accountNo !== "0" && accountNo !== "-1") {
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/home/getAccountData?accountName=" + accountNo,
			dataType : 'json',				
			success : function(data) {
				$("[name=nameShip]").val(data.accountName);
				$("[name=addressBill]").val(data.accountAddress);
				$("[name=addressShip]").val(data.accountAddress);
				$("[name=gstinBill]").val(data.gstin);
				$("[name=gstinShip]").val(data.gstin);
				$("[name=stateBill]").val(gstCodeStateMap[data.accountState]);
				$("[name=stateShip]").val(gstCodeStateMap[data.accountState]);
				$("[name=partyAddress]").val(data.accountAddress);
				$("[name=partyState]").val(gstCodeStateMap[data.accountState]);
				$("[name=gstinBill]").focus();
				$("[name=gstinShip]").focus();
				$("[name=gstinShip]").blur();
				$("[name=gstinBill]").blur();
			}
			});
	} else {
		$("[name=nameShip]").val("");
		$("[name=addressBill]").val("");
		$("[name=addressShip]").val("");
		$("[name=gstinBill]").val("");
		$("[name=gstinShip]").val("");
		$("[name=stateBill]").val("");
		$("[name=stateShip]").val("");
		$("[name=partyAddress]").val("");
		$("[name=partyState]").val("");
		$("[name=gstinBill]").focus();
		$("[name=gstinShip]").focus();
		$("[name=gstinShip]").blur();
		$("[name=gstinBill]").blur();
		
		if(accountNo === "-1"){
			$("[name=accountNo]").val("0");
			window.open('/home/addaccount','_blank');
		}
	}

});

$("#againstInvoicedropdown").change(function() {
    var invoiceNumber = $("#againstInvoicedropdown").val();
   
    if(invoiceNumber !== "0") {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/home/invoicedetails?invoiceNo=" + invoiceNumber,
            dataType : 'json',               
            success : function(data) {
				$("[name=nameBill]").val(data.invoiceAccountDetails.accountName);
                $("[name=againstInvoiceDate]").val(data.invoiceDate);
                $("[name=state]").val(data.invoiceState);
                $("[name=reverseCharge]").val(data.invoiceReverseCharge);
                $("[name=linkedInvoiceNo]").val(data.invoiceNumber);
                $("[name=accountNo]").val(data.invoiceAccountDetails.id);
                $("[name=nameShip]").val(data.invoiceAccountDetails.accountName);
                $("[name=addressBill]").val(data.invoiceAccountDetails.accountAddress);
                $("[name=addressShip]").val(data.invoiceAccountDetails.accountAddress);
                $("[name=gstinBill]").val(data.invoiceAccountDetails.gstin);
                $("[name=gstinShip]").val(data.invoiceAccountDetails.gstin);
                $("[name=stateBill]").val(gstCodeStateMap[data.invoiceAccountDetails.accountState]);
                $("[name=stateShip]").val(gstCodeStateMap[data.invoiceAccountDetails.accountState]);
                $("[name=partyAddress]").val(data.invoiceAccountDetails.accountAddress);
                $("[name=partyState]").val(gstCodeStateMap[data.invoiceAccountDetails.accountState]);
				$("[name=gstinBill]").focus();
                $("[name=gstinShip]").focus();
                $("[name=gstinShip]").blur();
                $("[name=gstinBill]").blur();
                $("[name=againstInvoiceDate]").blur();
            }
            });
       
    }
    else {
        $("[name=againstInvoiceDate]").val("");
        $("[name=state]").val("");
        $("[name=accountNo]").val("");
        $("[name=reverseCharge]").val("");
        $("[name=linkedInvoiceNo]").val("");
        $("[name=nameShip]").val("");
        $("[name=addressBill]").val("");
        $("[name=addressShip]").val("");
        $("[name=gstinBill]").val("");
        $("[name=gstinShip]").val("");
        $("[name=stateBill]").val("");
        $("[name=stateShip]").val("");
        $("[name=partyAddress]").val("");
        $("[name=partyState]").val("");
		$("[name=nameBill]").val("");
        $("[name=againstInvoiceDate]").blur();
		$("[name=gstinBill]").focus();
        $("[name=gstinShip]").focus();
        $("[name=gstinShip]").blur();
        $("[name=gstinBill]").blur();
    }
});


function checkAccountName(value) {
	var accountName = $(value).val().trim();
	if(accountName !== "" && $("[name=id").val() === "0") {
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/home/getAccountName?accountName=" + accountName,
			dataType : 'json',				
			success : function(data) {
				if(data !== null) {
					$('#name_alert').html(
							'<div class="alert alert-info text-center table-width fade-in" role="alert">'
							  +'An account already exists with this name, do you still wish to continue with this name. It is suggested to choose a different name to avoid confusion.'+
							  '</div>')
					$("#name_alert");
				}
				else {
					$('#name_alert').empty();
				}
			}
			});
	}
}

function getAccountList() {
	$("[name=accountNo]").empty();
	$("[name=nameShip]").val("");
	$("[name=addressBill]").val("");
	$("[name=addressShip]").val("");
	$("[name=gstinBill]").val("");
	$("[name=gstinShip]").val("");
	$("[name=stateBill]").val("");
	$("[name=stateShip]").val("");
	$("[name=gstinBill]").focus();
	$("[name=gstinShip]").focus();
	$("[name=gstinShip]").blur();
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/home/getaccountlist",
		dataType : 'json',			
		async : false,
		success : function(data) {			
			$.each(data, function (i, item) {
			    $('[name=accountNo]').append($('<option>', { 
			        value: item.id,
			        text : item.accountName 
			    }));
			});
		}
		});
}


function setBOSValues() {

    $tableBOSID.find('tbody tr').each(function (index) {
    	
        var $tblrow = $(this);
        $tblrow.find("#srNo").val(index+1);
        $tblrow.on('change', function () {
        	var product = $tblrow.find("#productDesc").val();
        	
        	if(product !== "0") {
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "/home/getItemData?itemId=" +product,
					dataType : 'json',
					async : false,
					success : function(data) {
						$tblrow.find("[id=hsnCode]").val(data.productHnscode);
						$tblrow.find("[id=uom]").val(data.productUom);
						$tblrow.find("[id=discount]").val(parseFloat(data.productDiscount).toFixed(2));
						$tblrow.find("[id=rate]").val(parseFloat(data.productRate).toFixed(2));
						$tblrow.find("[id=qty]").val(parseFloat(data.productQuantity).toFixed(2));
						$tblrow.find("[id=productDesc]").val(data.productDescription);
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
            
            var totalAmount = amount - discount;
            if(!isNaN(totalAmount)) {
            	$tblrow.find('#totalAmount').val(totalAmount.toFixed(2));
            	ttlTotalAmount[$tblrow.find("#srNo").val()-1]=totalAmount;
            	$("[name=ttlTotalAmount]").val(getSum(ttlTotalAmount).toFixed(2));
            }
            
    	    $("[name=totalAmountAfterTax]").val(getSum(ttlTotalAmount).toFixed(2));
    	    if(parseInt($("[name=ttlTotalAmount]").val(),10)!==0 && $("[name=ttlTotalAmount]").val()!=="") {
    		    getAmountInWords();
    		    }
        }); 
    });    

}

function resetBOSValues() {

	 ttlAmount = [];
	 ttlQty = [];
	 ttlTotalAmount = [];
	 $tableBOSID.find('tbody tr').each(function (index) {
	        var $tblrow = $(this);
	        $tblrow.find("#srNo").val(index+1);
	        ttlQty[index]=checkValueNaN(parseFloat($tblrow.find("#qty").val()));
	        ttlAmount[index]=checkValueNaN(parseFloat($tblrow.find("#amount").val()));
	        ttlTotalAmount[index]=checkValueNaN(parseFloat($tblrow.find("#totalAmount").val()));
	    });
	    
	    $("[name=ttlQty]").val(getSum(ttlQty).toFixed(2));
	    $("[name=ttlAmount]").val(getSum(ttlAmount).toFixed(2));
	    $("[name=ttlTotalAmount]").val(getSum(ttlTotalAmount).toFixed(2));
	    
	    $("[name=totalAmountAfterTax]").val(getSum(ttlTotalAmount).toFixed(2));
	    
	    if(parseInt($("[name=ttlTotalAmount]").val(),10)!==0) {
	    getAmountInWords();
	    }
}

function updateCompany(active) {
	if(active === "1"){
		createYesCancelMessageModal("Do you want to update company?");
		$('#buttondata').click(function() { 
			var text = $(this).attr('value');
		       if(text) {
		    	   window.location ='/home/addcompany';
		       }
		        });
			
	} else {
		createConfirmationMessageModal("You can update only active company");
	}
}

function deleteCompany(active, key) {
	if(active == "1") {
		createConfirmationMessageModal('Active Company cannot be deleted');
	} else {
		var text;
		createYesCancelMessageModal("Are you sure you want to delete this Company. Please Note that by deleting a company you lose all data related to it.");
		$('#buttondata').click(function() { 
		       text = $(this).attr('value');
		       if(text) {
		    	   window.location ='/home/deletecompany/'+key;
		       }
		        });
	}
}

function activateCompany(key) {
	createYesCancelMessageModal("Are you sure you want to activate this Company.");
	$('#buttondata').click(function() { 
	       text = $(this).attr('value');
	       if(text) {
	    	   window.location ='/home/activatecompany/'+key;
	       }
	        });
}

function createConfirmationMessageModal(message) {
	$('#alert_placeholder').html(
			'<div class="modal fade" id="ConfirmationModal"><div class="modal-dialog modal-sm">  <div class="modal-content"><!-- Modal body --><div class="modal-body"><span style="font-size: 15px" class="glyphicon glyphicon-cog"></span>'+message+'</div><div class="modal-footer"><button type="button" class="btn btn-success btn-block bigbuttonwithoutmargins modalbuttoncolor" data-dismiss="modal" value="true">OK</button></div>');
	$('#ConfirmationModal').modal('show');
}

function createYesCancelMessageModal(message) {
	var text = false;
	$('#alert_placeholder').html(
			'<div class="modal fade" id="YesCancelModal"><div class="modal-dialog modal-sm">  <div class="modal-content"><!-- Modal body --><div class="modal-body"><span style="font-size: 15px" class="glyphicon glyphicon-cog"></span>'+message+'</div><div class="modal-footer"><button type="button" class="btn btn-success btn-block bigbuttonwithoutmargins modalbuttoncolor" data-dismiss="modal" id="buttondata" value="true">Yes</button><br/><button type="button" class="btn btn-danger btn-block bigbuttonwithoutmargins" data-dismiss="modal" id="buttondata" value="false">Cancel</button></div>');
	$('#YesCancelModal').modal('show');
}

$(function () {
    $("#companyaddress").on('keyup change', function (e) {
        $(this).val($(this).val().toUpperCase());
    });
});

$(function () {
    $("#companyname").on('keyup change', function (e) {
        $(this).val($(this).val().toUpperCase());
    });
});

$(function () {
    $("#companygstin").on('keyup change', function (e) {
        $(this).val($(this).val().toUpperCase());
    });
});

function getBankData(data) {
	var payment = $(data).val();
	if(payment === "Bank"){
		$('#bankDropdown').html('<select class="form-control" id="bankId" name="bankId" onclick="notSameBank()" onfocus="getBankList()" required="required"></select>');
		getBankList();
	} else {
		$('#bankDropdown').empty();
		$('#bankAlert').empty();
	}
}

function notSameBank(){
	    var val = $('[name="payFromBankId"]').val();
	    var val2 = $('[name="payToBankId"]').val();
	    $("select>option.hide").wrap('<span>');
	    if(val == val2){
	    	   $('button[name="submit"]').prop('disabled',true);
	    	   $('#sameBankAlert').html(
						'<div class="alert alert-info text-center" role="alert">'
						  +'You can not select same bank.'+
						  '</div>');
		} else {
	    	   $('button[name="submit"]').prop('disabled', false);
	    	   $('#sameBankAlert').empty();
		}   
	}

function getBankDataFrom(data) {
	var payment = $(data).val();
	if(payment === "Bank"){
		$('#bankDropdown1').html('<select class="form-control" id="bankId" name="payFromBankId"  onclick="notSameBank()" required="required"></select>');
		getBankListForContraCash();
	} else {
		$('#bankDropdown1').empty();
		$('#bankAlert1').empty();
	}
}

function getBankDataTo(data) {
	var payment = $(data).val();
	if(payment === "Bank"){
		$('#bankDropdown2').html('<select class="form-control" id="bankId" name="payToBankId" onclick="notSameBank()" required="required"></select>');
		getBankListForContraCash();
	} else {
		$('#bankDropdown2').empty();
		$('#bankAlert2').empty();
	}
}

function getBankListForContraCash() {
	$("#bankId").empty();
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/home/getbanklist",
		dataType : 'json',			
		async : false,
		success : function(data) {	
			if(data !== null) {
				$.each(data, function (i, item) {
				    $('select[name="payToBankId"]').append($('<option>', { 
				        value: item.id,
				        text : item.userBankAccount 
				    }));
				     $('select[name="payFromBankId"]').append($('<option>', { 
				        value: item.id,
				        text : item.userBankAccount 
				    }));
				});
			$('#bankAlert').empty();
			} else {
				$('#bankAlert').html(
						'<div class="alert alert-info text-center" role="alert">'
						  +'You have no bank account added. Please add one <a href="/home/addbank" class="alert-link">here</a>.'+
						  '</div>');
		}
		}
	});
	return notSameBank();
}

function getBankList() {
$("#bankId").empty();
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/home/getbanklist",
		dataType : 'json',			
		async : false,
		success : function(data) {	
			if(data !== null) {
				$.each(data, function (i, item) {
				    $('#bankId').append($('<option>', { 
				        value: item.id,
				        text : item.userBankAccount 
				    }));
				});
			$('#bankAlert').empty();
			} else {
				$('#bankAlert').html(
						'<div class="alert alert-info text-center" role="alert">'
						  +'You have no bank account added. Please add one <a href="/home/addbank" class="alert-link">here</a>.'+
						  '</div>');
		}
		}
	});
}


function getInvoiceData(data) {
	var value = $(data).val();
	var account = $("#selectbasic").val();
	if(value === "Invoice Ref"){
		if(account !== "0") {
			$("#invoicePayment").show();
			$("#invoiceReceipt").show();
			$invoiceTable.find('tbody tr').each(function (index) {
			var $tblrow = $(this);
			getInvoiceList(account, $tblrow);
				
	        $tblrow.on('change', function () {
		        	var payment = parseFloat($tblrow.find("#paymentAmount").val());
		        	ttlPayment[index] = checkValueNaN(payment);
		        	var dueAmount = parseFloat($tblrow.find("#remainingAmount").val()) - payment;
		        	if(!isNaN(dueAmount)) {
		        		if(dueAmount < 0) {
		        			$tblrow.find("#paymentAmount").val("");
		        			createConfirmationMessageModal("Payment cannot be greater than remaining amount");
		        		} else {
		        			$tblrow.find("#dueAmount").val(dueAmount.toFixed(2));
		        		}
		        	}
				});
	        });
			if(dropdown.length === 0) {
				createConfirmationMessageModal("No Invoice(s) were found for this account");
				$('#paymentReference option:first').prop('selected', true);
				$('#receiptReference option:first').prop('selected', true);
				$("#invoicePayment").hide();
				$("#invoiceReceipt").hide();
				emptyTable();
			}
		} else {
			createConfirmationMessageModal("Please select an account");
			$('#paymentReference option:first').prop('selected', true);
			$('#receiptReference option:first').prop('selected', true);
		}
	} else {
		$("#invoicePayment").hide();
		$("#invoiceReceipt").hide();
		emptyTable();
	}
}

function getInvoiceList(account, row) {

	$(row).find("#invoiceDropdown").empty();
	$(row).find('input').val("");
	if(dropdown.length === 0) {
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/home/getinvoicelist?accountName="+account,
			dataType : 'json',			
			async : false,
			success : function(data) {	
				if(data !== null) {
					dropdown = data;
					$.each(data, function (i, item) {
						$(row).find("#invoiceDropdown").append($('<option>', { 
					        value: item.invoiceUniqueKey,
					        text : item.invoiceNumber 
					    }));
					});
				} 
			}
		});
	} else {
		$.each(dropdown, function (i, item) {
			$(row).find("#invoiceDropdown").append($('<option>', { 
		        value: item.invoiceUniqueKey,
		        text : item.invoiceNumber 
		    }));
		});
	}
}

function emptyTable() {
	ttlPayment = [];
	invoiceList = [];
	dropdown = [];
	$invoiceTable.find('tbody tr').each(function (index) {
		var $tblrow = $(this);
		$tblrow.find('input').val("");
        });
}

function setInvoice(data) {
	
	var invoiceKey = $(data).val();
	var index = $(data).parent().parent().index();

	if(invoiceKey !== "0") {
		if(!invoiceList.includes(invoiceKey)) {
    		$.ajax({
    			type : "GET",
    			contentType : "application/json",
    			url : "/home/getinvoice?key="+invoiceKey,
    			dataType : 'json',			
    			async : true,
    			success : function(val) {
    				invoiceList[index] = val.invoiceUniqueKey;
    				$(data).parent().parent().find("#invoiceId").val(val.id);
    				$(data).parent().parent().find("#invoiceAmount").val(val.invoiceTotalAmountAfterTax);
    				var remaining = parseFloat(val.invoiceTotalAmountAfterTax) - parseFloat(val.invoicePaidAmt);
    				$(data).parent().parent().find("#remainingAmount").val(parseFloat(remaining).toFixed(2))
    			}
    		});
		} else {
			createConfirmationMessageModal("Each invoice can be selected only once");
			$(data).find('option:first').prop('selected', true);
		}
	} else {
		ttlPayment[index] = 0;
		invoiceList[index] = "";
		$(data).parent().parent().find("#invoiceId").val("");
		$(data).parent().parent().find("#invoiceAmount").val("");
		$(data).parent().parent().find("#remainingAmount").val("");
		$(data).parent().parent().find("#paymentAmount").val("");
		$(data).parent().parent().find("#dueAmount").val("");
	}
}

$("[name=saleType]").change(function() {
	getBankDataInvoice($("[name=saleType]"));
});

function getBankDataInvoice(data) {

	var saleType = $(data).val();
	if(saleType === "Bank"){
		$('#bankDiv').html('<select class="form-control" name="bankId" required="required"></select>');
		getBankListInvoice();
	} else {
		$('#bankDiv').empty();
	}
}

function getBankListInvoice() {

	$("[name=bankId]").empty();
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/home/getbanklist",
		dataType : 'json',			
		async : false,
		success : function(data) {	
			if(data !== null) {
				$.each(data, function (i, item) {
				    $('[name=bankId]').append($('<option>', { 
				        value: item.id,
				        text : item.userBankAccount 
				    }));
				});
			} else {
				createConfirmationMessageModal("Please add a bank first");
				$("[name=saleType]").find('option:first').prop('selected', true);
				$('#bankDiv').empty();
		}
		}
	});
}

function getAccountListPurchase() {
	$("[name=accountNo]").empty();
	$("[name=partyAddress]").val("");
	$("[name=gstinBill]").val("");
	$("[name=partyState]").val("");
	$("[name=gstinBill]").focus();
	$("[name=gstinBill]").blur()
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/home/getaccountlistpurchase",
		dataType : 'json',			
		async : false,
		success : function(data) {			
			$.each(data, function (i, item) {
			    $('[name=accountNo]').append($('<option>', { 
			        value: item.id,
			        text : item.accountName 
			    }));
			});
		}
		});
}