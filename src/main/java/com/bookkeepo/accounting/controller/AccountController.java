/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.security.Principal;

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
import com.bookkeepo.accounting.service.AccountService;

/**
 * @author sachingoyal
 *
 */

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = { "/home/addaccount" }, method = RequestMethod.GET)
	public ModelAndView getHomePage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("accountList", accountService.fetchAccountName(request.getUserPrincipal().getName()));
		modelAndView.addObject("account", new Accounts());
		modelAndView.setViewName("addaccount");
		return modelAndView;
	}

	@RequestMapping(value = "/home/addaccount", method = RequestMethod.POST)
	public ModelAndView addNewAccount(@Valid @ModelAttribute("account") Accounts account, BindingResult bindingResult, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		Accounts accountWithGstInExists = accountService.findAccountByGstin(account.getGstin(),principal.getName());

		if(accountService.findById(account.getId()) == null) {
			if (accountWithGstInExists != null) {
				bindingResult.rejectValue("gstin", "gstin", "This GST number is already registered.");
			} else if (account.getGstin().trim().length() != 15) {
				bindingResult.rejectValue("gstin", "gstin", "Please provide a valid GST number.");
			}
	
			Accounts accountWithPanExists = accountService.findAccountByPan(account.getAccountPan(), principal.getName());
			if (accountWithPanExists != null) {
				bindingResult.rejectValue("accountPan", "accountPan", "This PAN is already registered.");
			} else if (account.getAccountPan().trim().length() != 10) {
				bindingResult.rejectValue("accountPan", "accountPan", "Please provide a valid PAN.");
	
			}
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("account", account);
			modelAndView.setViewName("addaccount");
		} else {
			account.setAccountOwner(principal.getName());
			accountService.saveAccount(account);
			modelAndView.addObject("message", "Account Updated Successfully");
			modelAndView.addObject("accountList", accountService.fetchAccountName(principal.getName()));
			modelAndView.addObject("account", new Accounts());
			modelAndView.setViewName("addaccount");
		}
		return modelAndView;
	}

}
