/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.model.json.InvoicePageData;
import com.bookkeepo.accounting.service.AccountService;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.InvoiceService;
import com.bookkeepo.accounting.util.InvoiceUtil;
import com.google.gson.Gson;

/**
 * @author sachingoyal
 *
 */
@Controller
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	CompanyDetailsService companyDetailsService;

	@Autowired
	AccountService accountService;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

	@Autowired
	Gson gson;

	@RequestMapping(value = "/home/submitInvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public void setupSalesInvoiceData(@RequestBody String salesInvoiceJson, Principal principal,
			HttpServletResponse response) throws IOException {
		InvoicePageData salesInvoiceData = null;
		try {
			salesInvoiceData = gson.fromJson(salesInvoiceJson, InvoicePageData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		InvoiceDetails invoice = new InvoiceDetails();
		invoice.setInvoiceType(salesInvoiceData.getPageName());
		invoice.setInvoiceOwner(principal.getName());

		Company companyDetails = companyDetailsService.findByUserName(principal.getName()).stream()
				.filter(c -> c.getCompanyActive() == 1).findFirst().get();

		if (salesInvoiceData.getAccountNo() != 0) {
			Accounts account = accountService.findById(salesInvoiceData.getAccountNo());
			invoice.setInvoiceAccountDetails(account);
		}

		InvoiceUtil.updateInvoice(invoice, salesInvoiceData, companyDetails);
		invoiceService.saveInvoice(invoice);

		ByteArrayOutputStream invoiceData = InvoiceUtil.createPDF(invoice);
		response.setContentType("application/pdf");

		Date date = new Date();
		String time = sdf.format(new Timestamp(date.getTime()));

		response.addHeader("Content-Disposition", "attachment; filename=" + invoice.getInvoiceType() + "_"
				+ invoice.getInvoiceNumber() + "_" + time + ".pdf");
		response.setContentLength(invoiceData.size());

		OutputStream out = response.getOutputStream();
		invoiceData.writeTo(out);
		out.flush();

	}

}
