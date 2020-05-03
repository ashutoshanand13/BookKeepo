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
import com.bookkeepo.accounting.service.CompanyDetailsService;

/**
 * @author sachingoyal
 *
 */

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	CompanyDetailsService companyDetailsService;

	@RequestMapping(value = { "/home/addaccount" }, method = RequestMethod.GET)
	public ModelAndView getHomePage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("accountList", accountService.fetchAccountName(request.getUserPrincipal().getName()));
		modelAndView.addObject("account", new Accounts());
		modelAndView.setViewName("addaccount");
		return modelAndView;
	}

	@RequestMapping(value = "/home/addaccount", method = RequestMethod.POST)
	public ModelAndView addNewAccount(@Valid @ModelAttribute("account") Accounts account, BindingResult bindingResult,
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView();

		if (account.getId() == 0) {
			if (!account.getGstin().isEmpty()) {
				Accounts accountWithGstInExists = accountService.findAccountByGstin(account.getGstin(),
						principal.getName());

				if (accountWithGstInExists != null) {
					bindingResult.rejectValue("gstin", "gstin", "This GST number is already registered.");
				}
			}

			if (!account.getAccountPan().isEmpty()) {
				Accounts accountWithPanExists = accountService.findAccountByPan(account.getAccountPan(),
						principal.getName());
				if (accountWithPanExists != null) {
					bindingResult.rejectValue("accountPan", "accountPan", "This PAN is already registered.");
				}
			}
		}

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("account", account);
			modelAndView.setViewName("addaccount");
		} else {
			account.setAccountOwner(principal.getName());
			account.setAccountCompanyDetails(
					account.getId() == 0 ? companyDetailsService.findByUserName(principal.getName()) : null);
			accountService.saveAccount(account);
			modelAndView.addObject("message", "Account Updated Successfully");
			modelAndView.addObject("accountList", accountService.fetchAccountName(principal.getName()));
			modelAndView.addObject("account", new Accounts());
			modelAndView.setViewName("addaccount");
		}
		return modelAndView;
	}

}
