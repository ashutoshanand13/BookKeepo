package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.util.CommonUtils;

/**
 * 
 * @author Ashutosh
 *
 */

@Controller
public class PurchaseInvoiceController extends MasterController {

	@RequestMapping(value = { "/home/addpurchaseinvoice" }, method = RequestMethod.GET)
	public ModelAndView getPurchaseInvoicePage(HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = CommonUtils.getSessionAttributes(request);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			modelAndView.addObject("company", company);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			modelAndView.addObject("itemList", itemService.findByProductOwner(user));
			modelAndView.addObject("pageName", InvoiceType.Purchase_Invoice.getType());
			modelAndView.addObject("InvoiceNbr",
					invoiceService.getInvoiceNumber(user, InvoiceType.Purchase_Invoice.getType()));
			modelAndView.setViewName("addPurchaseInvoice");
		}
		return modelAndView;
	}

}
