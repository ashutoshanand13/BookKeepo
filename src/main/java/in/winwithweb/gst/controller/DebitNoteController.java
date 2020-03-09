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
import java.util.ArrayList;
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

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.model.json.InvoicePageData;
import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.service.AccountService;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.service.InvoiceService;
import in.winwithweb.gst.util.CommonUtils;
import in.winwithweb.gst.util.InvoiceUtil;

/**
 * @author Yash Singh
 *
 */
@Controller
public class DebitNoteController {
	
	@Autowired
	CompanyDetailsService companyDetailsService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	Gson gson;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	
	@RequestMapping(value = "/home/debitnote", method = RequestMethod.GET)
	public ModelAndView setupSales(HttpServletRequest request) {
		String user=request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		List<String> account = accountService.fetchAccountName();
		List<String> ownerInvoices = new ArrayList<>();
		ownerInvoices.add("Against Invoice");
		ownerInvoices.addAll(invoiceService.findbyInvoiceOwnerType(user, "Tax Invoice"));
		if(company==null) {
			modelAndView.addObject("message", "Please update company details before creating an Invoice");
			modelAndView.addObject("company",new Company("debitNote"));
			modelAndView.addObject("logoImage",CommonUtils.getImgfromResource("/static/images/image-400x400.jpg"));
			modelAndView.setViewName("addCompany");
		}
		else if(ownerInvoices.size() == 1) {
			modelAndView.addObject("message", "Please add an Invoice");
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
			modelAndView.setViewName("salesInvoice");
		}
		else {
		modelAndView.addObject("accountList", account);
		modelAndView.addObject("invoiceList", ownerInvoices);
		modelAndView.addObject("company",company);
		byte[] encodeBase64 = Base64.getEncoder().encode(company.getCompanyLogo());
		String base64Encoded = null;
		try {
			base64Encoded = new String(encodeBase64);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("logoImage",base64Encoded);
		modelAndView.setViewName("debitNote");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/home/debitnote", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public void setupDebitNoteData(@RequestBody String debitNoteJson, Principal principal,
			HttpServletResponse response) throws IOException {
		InvoicePageData debitNoteData = null;
		try {
			debitNoteData = gson.fromJson(debitNoteJson, InvoicePageData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		InvoiceDetails invoice = new InvoiceDetails();
		invoice.setType("Debit Note");
		invoice.setInvoiceOwner(principal.getName());
		invoice.setInvoiceTotalAmountWords(CommonUtils.numberConverter(debitNoteData.getTtlTotalAmount()));

		Company companyDetails = companyDetailsService.findByUserName(principal.getName());

		InvoiceUtil.updateInvoice(invoice, debitNoteData, companyDetails);
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