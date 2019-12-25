/**
 * 
 */
package in.winwithweb.gst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import in.winwithweb.gst.model.json.SalesInvoicePageData;
import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.service.InvoiceService;
import in.winwithweb.gst.util.InvoiceUtil;

/**
 * @author Yash Singh
 *
 */
@Controller
public class SalesInvoiceController {
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	Gson gson;
	
	
	@RequestMapping(value = "/home/salesinvoice", method = RequestMethod.POST)
	public String setupSalesInvoiceData(@RequestBody String salesInvoiceJson) {
		SalesInvoicePageData salesInvoiceData = gson.fromJson(salesInvoiceJson, SalesInvoicePageData.class);
		InvoiceDetails invoice = new InvoiceDetails();
		InvoiceUtil.updateInvoice(invoice,salesInvoiceData);
		invoiceService.saveInvoice(invoice);
		return "salesInvoice";
	}
	
}
