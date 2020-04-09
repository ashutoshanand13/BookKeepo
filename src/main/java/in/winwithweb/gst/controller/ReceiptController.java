/**
 * 
 */
package in.winwithweb.gst.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Receipts;
import in.winwithweb.gst.service.AccountService;
import in.winwithweb.gst.service.ReceiptService;

/**
 * @author sachingoyal
 *
 */

@Controller
public class ReceiptController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ReceiptService receiptService;

	@RequestMapping(value = { "/home/addreceipt" }, method = RequestMethod.GET)
	public ModelAndView getAddReceipt(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user=request.getUserPrincipal().getName();
		List<String> accountList = accountService.fetchAccountName(user);
		if(accountList != null) {
			accountList.add(0, "Select Account");
		}
		Receipts receipt = new Receipts();
		modelAndView.addObject("receipts", receipt);
		modelAndView.setViewName("addReceipt");
		modelAndView.addObject("accountList", accountList);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/home/addreceipt", method = RequestMethod.POST)
	public ModelAndView addNewReceipt(@Valid @ModelAttribute("receipts") Receipts receipt, BindingResult bindingResult, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		receipt.setReceiptOwner(principal.getName());
		receiptService.saveAccount(receipt);
		List<String> accountList = accountService.fetchAccountName(principal.getName());
		if(accountList != null) {
			accountList.add(0, "Select Account");
		}
		modelAndView.addObject("receipts", new Receipts());
		modelAndView.setViewName("addReceipt");
		modelAndView.addObject("accountList", accountList);
		return modelAndView;
	}

}
