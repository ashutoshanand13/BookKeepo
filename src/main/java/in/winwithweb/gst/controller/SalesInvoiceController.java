/**
 * 
 */
package in.winwithweb.gst.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public void setupSalesInvoiceData(@RequestBody String salesInvoiceJson, Principal principal,
			HttpServletResponse response) throws IOException {
		SalesInvoicePageData salesInvoiceData = null;
		try {
			salesInvoiceData = gson.fromJson(salesInvoiceJson, SalesInvoicePageData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		InvoiceDetails invoice = new InvoiceDetails();

		Company companyDetails = companyDetailsService.findByUserName(principal.getName());

		InvoiceUtil.updateInvoice(invoice, salesInvoiceData, companyDetails);
		invoiceService.saveInvoice(invoice);

		byte[] documentBody = InvoiceUtil.createPDF(invoice).toByteArray();
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=invoice.pdf");
		response.setContentLength(documentBody.length);

		OutputStream out = response.getOutputStream();
		InvoiceUtil.createPDF(invoice).writeTo(out);
		out.flush();

	}

	@RequestMapping(value = { "/home/showInvoice" }, method = RequestMethod.GET)
	public ModelAndView showInvoice() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("invoiceList", invoiceService.fetchAllInvoice());
		modelAndView.setViewName("showInvoice");
		return modelAndView;
	}

}
