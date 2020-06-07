/**
 * 
 */
package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.util.CommonUtils;

/**
 * @author Yash Singh
 *
 */
@Controller
public class SalesInvoiceController extends MasterController {

	@RequestMapping(value = "/home/salesinvoice", method = RequestMethod.GET)
	public ModelAndView setupSales(HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			modelAndView.addObject("company", company);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			modelAndView.addObject("itemList", itemService.findByProductOwner(user));
			modelAndView.addObject("pageName", InvoiceType.Tax_Invoice.getType());
			modelAndView.addObject("InvoiceNbr",
					invoiceService.getInvoiceNumber(user, InvoiceType.Tax_Invoice.getType()));

			modelAndView.setViewName("salesInvoice");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/home/salesinvoice/{message}", method = RequestMethod.GET)
	public ModelAndView generateTaxInvoiceBeforeNote(@PathVariable("message") String message,
			HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView("salesInvoice");
		modelAndView.addObject("message", message);
		Company company = companyDetailsService.findByUserName(user);
		modelAndView.addObject("company", company);
		modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
		modelAndView.addObject("itemList", itemService.findByProductOwner(user));
		modelAndView.addObject("pageName", InvoiceType.Tax_Invoice.getType());
		modelAndView.addObject("InvoiceNbr",
				invoiceService.getInvoiceNumber(user, InvoiceType.Tax_Invoice.getType()));
		modelAndView.setViewName("salesInvoice");
		return modelAndView;
	}

}
