/**
 * 
 */
package in.winwithweb.gst.controller;

import java.io.ByteArrayInputStream;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.model.json.SalesInvoicePageData;
import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.service.CompanyDetailsService;
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
	CompanyDetailsService companyDetailsService;

	@Autowired
	Gson gson;

	@RequestMapping(value = "/home/salesinvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> setupSalesInvoiceData(@RequestBody String salesInvoiceJson,Principal principal) {
		SalesInvoicePageData salesInvoiceData = null;
		try {
			salesInvoiceData = gson.fromJson(salesInvoiceJson, SalesInvoicePageData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		InvoiceDetails invoice = new InvoiceDetails();
		
		Company companyDetails = companyDetailsService.findByUserName(principal.getName());

		
		InvoiceUtil.updateInvoice(invoice, salesInvoiceData,companyDetails);
		invoiceService.saveInvoice(invoice);

		ByteArrayInputStream bis = InvoiceUtil.createPDF(invoice);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "filename=invoice.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@RequestMapping(value = { "/home/showInvoice" }, method = RequestMethod.GET)
	public ModelAndView showInvoice() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("invoiceList", invoiceService.fetchAllInvoice());
		modelAndView.setViewName("showInvoice");
		return modelAndView;
	}

}
