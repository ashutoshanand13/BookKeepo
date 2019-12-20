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
 const newTr = '<tr>            <td class="pt-3-half"><input type="text" id="srNo" name="excluded:skip" placeholder="Sr No" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="productDesc" name="excluded:skip" placeholder="Product Description"></td>            <td class="pt-3-half"><input type="text" id="hsnCode" name="excluded:skip" placeholder="HSN Code"></td>            <td class="pt-3-half"><input type="text" id="uom" name="excluded:skip" placeholder="UOM"></td>            <td class="pt-3-half"><input type="text" id="qty" name="excluded:skip" placeholder="QTY"></td>            <td class="pt-3-half"><input type="text" id="rate" name="excluded:skip" placeholder="Rate"></td>            <td class="pt-3-half"><input type="text" id="amount" name="excluded:skip" placeholder="Amount" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="discount" name="excluded:skip" placeholder="Discount"></td>            <td class="pt-3-half"><input type="text" id="gstRate" name="excluded:skip" placeholder="GST Rate"></td>            <td class="pt-3-half"><input type="text" id="taxableValue" name="excluded:skip" placeholder="Taxable Value" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="cgst" name="excluded:skip" placeholder="CGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="sgst" name="excluded:skip" placeholder="SGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="igst" name="excluded:skip" placeholder="IGST" readonly="readonly"></td>            <td class="pt-3-half"><input type="text" id="totalAmount" name="excluded:skip" placeholder="Total Amount" readonly="readonly"></td>            <td>              <span class="table-remove"><button type="button"                  class="btn btn-danger btn-rounded btn-sm my-0">Remove</button></span>            </td>          </tr>';

 $tableID.on('click', '.table-add', function () {
   $tableID.find('table').append(newTr);
   resetValues();
   setValues();
 });

 $tableID.on('click', '.table-remove', function () {
if ($tableID.find('tbody tr').length !== 1) {
   $(this).parents('tr').detach();
   var a = $tableID.find('tbody tr').length;
   
   resetValues();
   setValues();
}
else
	{
	alert("Cannot Remove Last Row");
	}
 });


 $BTN.on('click', () => {
   var json = '';
   
$('#tableJson table').map(function(i, table){
	   var $rows = $("#" +table.id).find('tr:not(:hidden)');
	   var newFormData = [];
	   jQuery( $("#" +table.id).find('tr:not(:first), tr:not(:last)')).each(function(i) {
	       var tb = jQuery(this);
	       var obj = {};
	       tb.find('input').each(function() {
	    	   if(this.id!=="")
	         obj[this.id] = this.value;
	       });
	       if(Object.keys(obj).length!==0)
	       newFormData.push(obj);
	     });
	   $('#itemList').val(JSON.stringify(newFormData));
});

// replace function used to remove extra "" while parsing.
var json = JSON.stringify($('#form').serializeJSON()).replace(/\\/g,"").replace("\"[","[").replace("]\"","]");

document.write(json);
 });
 
 
function setValues() {

	    $tableID.find('tbody tr').each(function (index) {
	    	
	        var $tblrow = $(this);

	        $tblrow.find("#srNo").val(index+1);
	        $tblrow.on('change', function () {
	            var qty = $tblrow.find("[id=qty]").val();
	            if(!isNaN(qty)){
	            	ttlQty[index]=parseInt(qty,10);
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
	        debugger;
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
