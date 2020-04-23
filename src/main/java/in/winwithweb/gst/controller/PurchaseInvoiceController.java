package in.winwithweb.gst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.model.InvoiceType;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.service.ItemService;
import in.winwithweb.gst.util.CommonUtils;

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
			Company newcompany = new Company("/home/addpurchaseinvoice");
			modelAndView.addObject("message", "Please update company details before creating an Invoice");
			modelAndView.addObject("company", newcompany);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromResource("/static/images/image-400x400.jpg"));
			modelAndView.setViewName("addCompany");
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
