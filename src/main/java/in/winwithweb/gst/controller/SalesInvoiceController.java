/**
 * 
 */
package in.winwithweb.gst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Accounts;
import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.model.InvoiceType;
import in.winwithweb.gst.service.AccountService;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.service.ItemService;
import in.winwithweb.gst.util.CommonUtils;

/**
 * @author Yash Singh
 *
 */
@Controller
public class SalesInvoiceController {

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/home/salesinvoice", method = RequestMethod.GET)
	public ModelAndView setupSales(HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		List<Accounts> account = accountService.fetchAccountNameForInvoice(user);
		if (company == null) {
			Company newcompany = new Company("/home/salesinvoice");
			modelAndView.addObject("message", "Please update company details before creating an Invoice");
			modelAndView.addObject("company", newcompany);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromResource("/static/images/image-400x400.jpg"));
			modelAndView.setViewName("addCompany");
		} else {
			modelAndView.addObject("accountList", account);
			modelAndView.addObject("company", company);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			modelAndView.addObject("itemList", itemService.findByProductOwner(user));
			modelAndView.addObject("pageName", InvoiceType.Tax_Invoice.getType());
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
		List<Accounts> account = accountService.fetchAccountNameForInvoice(user);
		modelAndView.addObject("accountList", account);
		modelAndView.addObject("company", company);
		modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
		modelAndView.addObject("itemList", itemService.findByProductOwner(user));
		modelAndView.addObject("pageName", InvoiceType.Tax_Invoice.getType());
		modelAndView.setViewName("salesInvoice");

		return modelAndView;
	}

}
