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

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.InvoiceData;
import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.InvoiceService;
import com.bookkeepo.accounting.service.ItemService;
import com.bookkeepo.accounting.util.CommonUtils;

/**
 * @author Yash Singh
 *
 */
@Controller
public class CreditNoteController {

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/home/creditnote", method = RequestMethod.GET)
	public ModelAndView setupSales(HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		List<InvoiceData> ownerInvoices = invoiceService.findByInvoiceOwnerAndInvoiceType(user,
				InvoiceType.Tax_Invoice.getType());
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else if (ownerInvoices.size() == 1) {
			modelAndView.setViewName("redirect:/home/salesinvoice/Please create tax invoice first!");
		} else {
			modelAndView.addObject("invoiceList", ownerInvoices);
			modelAndView.addObject("company", company);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			modelAndView.addObject("itemList", itemService.findByProductOwner(user));
			modelAndView.addObject("pageName", InvoiceType.Credit_Note.getType());
			modelAndView.addObject("InvoiceNbr",
					invoiceService.getInvoiceNumber(user, InvoiceType.Credit_Note.getType()));
			modelAndView.setViewName("creditNote");
		}
		return modelAndView;
	}
}
