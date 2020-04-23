/**
 * 
 */
package in.winwithweb.gst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.model.InvoiceType;
import in.winwithweb.gst.service.AccountService;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.service.InvoiceService;
import in.winwithweb.gst.service.ItemService;
import in.winwithweb.gst.util.CommonUtils;

/**
 * @author Yash Singh
 *
 */
@Controller
public class DebitNoteController {

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/home/debitnote", method = RequestMethod.GET)
	public ModelAndView setupSales(HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		List<String> ownerInvoices = invoiceService.findByInvoiceOwnerAndInvoiceType(user,
				InvoiceType.Tax_Invoice.getType());
		if (company == null) {
			modelAndView.setViewName("redirect:/home/updatecompany/debitNote");
		} else if (ownerInvoices.size() == 1) {
			modelAndView.setViewName("redirect:/home/salesinvoice/Please create tax invoice first!");
		} else {
			modelAndView.addObject("accountList", accountService.fetchAccountNameForInvoice(user));
			modelAndView.addObject("invoiceList", ownerInvoices);
			modelAndView.addObject("company", company);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			modelAndView.addObject("itemList", itemService.findByProductOwner(user));
			modelAndView.addObject("pageName", InvoiceType.Debit_Note.getType());
			modelAndView.setViewName("debitNote");
		}
		return modelAndView;
	}

}
