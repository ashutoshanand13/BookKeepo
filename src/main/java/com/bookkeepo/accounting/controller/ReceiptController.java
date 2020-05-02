/**
 * 
 */
package com.bookkeepo.accounting.controller;

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

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Receipts;
import com.bookkeepo.accounting.service.AccountService;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.ReceiptService;

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

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@RequestMapping(value = { "/home/addreceipt" }, method = RequestMethod.GET)
	public ModelAndView getAddReceipt(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user = request.getUserPrincipal().getName();
		List<Accounts> accountList = accountService.fetchAccountName(user);

		Receipts receipt = new Receipts();
		modelAndView.addObject("receipts", receipt);
		modelAndView.setViewName("addReceipt");
		modelAndView.addObject("accountList", accountList);
		return modelAndView;
	}

	@RequestMapping(value = "/home/addreceipt", method = RequestMethod.POST)
	public ModelAndView addNewReceipt(@Valid @ModelAttribute("receipts") Receipts receipt, BindingResult bindingResult,
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView();

		receipt.setReceiptOwner(principal.getName());
		receipt.setAccountRefNo(accountService.findById(receipt.getAccountRefNo().getId()));
		receipt.setReceiptCompanyDetails(
				receipt.getId() == 0 ? companyDetailsService.findByUserName(principal.getName()) : null);
		receiptService.saveAccount(receipt);
		List<Accounts> accountList = accountService.fetchAccountName(principal.getName());
		modelAndView.addObject("receipts", new Receipts());
		modelAndView.addObject("message", "Receipt Details Successfully Added");
		modelAndView.setViewName("addReceipt");
		modelAndView.addObject("accountList", accountList);
		return modelAndView;
	}

	@RequestMapping(value = { "/home/showreceipt" }, method = RequestMethod.GET)
	public ModelAndView showReceipt(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		String user = principal.getName();
		modelAndView.addObject("receiptList", receiptService.fetchAllReceipt(user));
		modelAndView.setViewName("receiptData");
		return modelAndView;
	}
}
