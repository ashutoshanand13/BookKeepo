/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.InvoiceService;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
 * @author sachingoyal
 *
 */

@Controller
public class HomeController {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	CompanyDetailsService companyDetailsService;

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView getAddAccount(HttpServletRequest request) {
		List<InvoiceDetails> invoiceList = invoiceService.findByInvoiceOwner(request.getUserPrincipal().getName());
		for (InvoiceDetails invoice : invoiceList) {
			if (invoice.getInvoiceCompanyDetails() != null) {
				invoice.setInvoiceAssoCompanyDetails(InvoiceUtil.getComanyDetails(invoice.getInvoiceCompanyDetails()));
				invoiceService.saveInvoice(invoice);
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

}
