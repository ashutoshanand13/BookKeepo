const $tableID1 = $('#tableJson');
const $tableID = $('#table');
 const $BTN = $('#export-btn');
 const $EXPORT = $('#export');

 const newTr = '<tr>  <td class="pt-3-half" contenteditable="true">Example</td>  <td class="pt-3-half" contenteditable="true">Example</td>  <td class="pt-3-half" contenteditable="true">Example</td>  <td class="pt-3-half" contenteditable="true">Example</td>  <td class="pt-3-half" contenteditable="true">Example</td>  <td>    <span class="table-remove"><button type="button" class="btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light">Remove</button></span>  </td></tr>';

 $tableID.on('click', '.table-add', function () {
   $tableID.find('table').append(newTr);
 });

 $tableID.on('click', '.table-remove', function () {
if ($tableID.find('tbody tr').length !== 1) {
   $(this).parents('tr').detach();
}
else
	{
	alert("Cannot Remove Last Row");
	}
 });

 $tableID.on('click', '.table-up', function () {

   const $row = $(this).parents('tr');

   if ($row.index() === 1) {
     return;
   }

   $row.prev().before($row.get(0));
 });

 $tableID.on('click', '.table-down', function () {

   const $row = $(this).parents('tr');
   $row.next().after($row.get(0));
 });

 // A few jQuery helpers for exporting only
 jQuery.fn.pop = [].pop;
 jQuery.fn.shift = [].shift;

 $BTN.on('click', () => {
   var map = new Map();
   var json = '';
$('#tableJson table').map(function(i, table){
	   var $rows = $("#" +table.id).find('tr:not(:hidden)');
	   var newFormData = [];
	   jQuery( $("#" +table.id).find('tr:not(:first)')).each(function(i) {
	       var tb = jQuery(this);
	       var obj = {};
	       tb.find('input').each(function() {
	         obj[this.id] = this.value;
	       });
	       obj['row'] = i;
	       newFormData.push(obj);
	     });
	   map.set(table.id,newFormData);
});

var arr3 = [].concat(JSON.stringify( strMapToObj(map) ), JSON.stringify($('#form').serializeJSON()));
   // Output the result
document.write(arr3);
 });
 
 
 function strMapToObj(strMap) {
	  let obj = Object.create(null);
	  for (let [k,v] of strMap) {
	    // We don’t escape the key '__proto__'
	    // which can cause problems on older engines
	    obj[k] = v;
	  }
	  return obj;
	}
 
