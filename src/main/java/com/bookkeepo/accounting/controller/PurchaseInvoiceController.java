package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.ItemService;
import com.bookkeepo.accounting.util.CommonUtils;

/**
 * 
 * @author Ashutosh
 *
 */

@Controller
public class PurchaseInvoiceController {

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = { "/home/addpurchaseinvoice" }, method = RequestMethod.GET)
	public ModelAndView getPurchaseInvoicePage(HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/updatecompany/addpurchaseinvoice");
		} else {
			modelAndView.addObject("company", company);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			modelAndView.addObject("itemList", itemService.findByProductOwner(user));
			modelAndView.addObject("pageName", InvoiceType.Purchase_Invoice.getType());
			modelAndView.setViewName("addPurchaseInvoice");
		}
		return modelAndView;
	}

}