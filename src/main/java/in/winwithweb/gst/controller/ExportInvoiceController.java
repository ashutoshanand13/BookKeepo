/**
 * 
 */
package in.winwithweb.gst.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import in.winwithweb.gst.model.Accounts;
import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.model.InvoiceType;
import in.winwithweb.gst.model.json.InvoicePageData;
import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.service.AccountService;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.service.InvoiceService;
import in.winwithweb.gst.service.ItemService;
import in.winwithweb.gst.util.CommonUtils;
import in.winwithweb.gst.util.InvoiceUtil;

/**
 * @author Yash Singh
 *
 */
@Controller
public class ExportInvoiceController {
	
	@Autowired
	CompanyDetailsService companyDetailsService;
	
	@Autowired
	Gson gson;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ItemService itemService;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

	@RequestMapping(value = "/home/exportinvoice", method = RequestMethod.GET)
	public ModelAndView setupSales(HttpServletRequest request) {
		String user=request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		List<Accounts> account = accountService.fetchAccountNameForInvoice(user);
		if(company==null) {
			modelAndView.addObject("message", "Please update company details before creating an Invoice");
			modelAndView.addObject("company",new Company("/home/exportinvoice"));
			modelAndView.addObject("logoImage",CommonUtils.getImgfromResource("/static/images/image-400x400.jpg"));
			modelAndView.setViewName("addCompany");
		}
		else {
			modelAndView.addObject("accountList", account);
			modelAndView.addObject("company",company);
			byte[] encodeBase64 = Base64.getEncoder().encode(company.getCompanyLogo());
			String base64Encoded = null;
			try {
				base64Encoded = new String(encodeBase64);
			} catch (Exception e) {
				e.printStackTrace();
			}
			modelAndView.addObject("logoImage",base64Encoded);
			modelAndView.addObject("itemList", itemService.findByProductOwner(user));
			modelAndView.addObject("pageName", InvoiceType.Export_Invoice.getType());
			modelAndView.setViewName("exportInvoice");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/home/exportinvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public void setupExportInvoiceData(@RequestBody String exportInvoiceJson, Principal principal,
			HttpServletResponse response) throws IOException {
		InvoicePageData exportInvoiceData = null;
		try {
			exportInvoiceData = gson.fromJson(exportInvoiceJson, InvoicePageData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		InvoiceDetails invoice = new InvoiceDetails();
		invoice.setInvoiceType(InvoiceType.Export_Invoice.getType());
		invoice.setInvoiceOwner(principal.getName());

		Company companyDetails = companyDetailsService.findByUserName(principal.getName());

		InvoiceUtil.updateInvoice(invoice, exportInvoiceData, companyDetails);
		invoiceService.saveInvoice(invoice);

		ByteArrayOutputStream invoiceData = InvoiceUtil.createPDF(invoice);
		response.setContentType("application/pdf");
		
		Date date = new Date();
		String time = sdf.format(new Timestamp(date.getTime()));
		
		response.addHeader("Content-Disposition", "attachment; filename=invoice_"+invoice.getInvoiceNumber()+"_"+time+".pdf");
		response.setContentLength(invoiceData.size());

		OutputStream out = response.getOutputStream();
		invoiceData.writeTo(out);
		out.flush();
		
	}
	
}
