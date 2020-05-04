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

import com.bookkeepo.accounting.entity.BankDetails;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.service.BankService;
import com.bookkeepo.accounting.service.CompanyDetailsService;

/**
 * @author Ashutosh Anand
 *
 */

@Controller
public class BankController {

	@Autowired
	private BankService bankService;

	@Autowired
	CompanyDetailsService companyDetailsService;

	@RequestMapping(value = { "/home/addbank" }, method = RequestMethod.GET)
	public ModelAndView getBankPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("addBank");
		String user = request.getUserPrincipal().getName();
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			List<BankDetails> bankList = bankService.fetchCreatedBanks(user, company);
			modelAndView.addObject("bankList", bankList);
			modelAndView.addObject("bank", new BankDetails());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/home/addbank", method = RequestMethod.POST)
	public ModelAndView addBank(@Valid @ModelAttribute("bank") BankDetails bank, BindingResult bindingResult,
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView("addBank");
		String user = principal.getName();
		Company company = companyDetailsService.findByUserName(principal.getName());
		bank.setUserBankCreator(principal.getName());
		bank.setBankCompanyDetails(company);
		bankService.saveBank(bank);
		modelAndView.addObject("message", "Bank details added Successfully.");
		modelAndView.addObject("bank", new BankDetails());
		modelAndView.addObject("bankList", bankService.fetchCreatedBanks(user, company));
		return modelAndView;
	}

}
