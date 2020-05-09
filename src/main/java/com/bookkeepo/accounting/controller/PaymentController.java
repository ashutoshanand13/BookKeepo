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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.BankDetails;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.Payment;
import com.bookkeepo.accounting.service.AccountService;
import com.bookkeepo.accounting.service.BankService;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.PaymentService;

/**
 * @author sachingoyal
 *
 */

@Controller
public class PaymentController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	CompanyDetailsService companyDetailsService;
	
	@Autowired
	private BankService bankService;

	@RequestMapping(value = { "/home/addpayment" }, method = RequestMethod.GET)
	public ModelAndView getPaymentScreen(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user = request.getUserPrincipal().getName();
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			makePageReadyforLoad(request, modelAndView, company);
		}
		return modelAndView;
	}

	@RequestMapping(value = { "/home/addpayment" }, method = RequestMethod.POST)
	public ModelAndView addNewPayment(@Valid @ModelAttribute("payment") Payment payment, @RequestParam(required = false) String bankId, BindingResult bindingResult,
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(principal.getName());
		payment.setPaymentOwner(principal.getName());
		payment.setAccountRefNo(accountService.findById(payment.getAccountRefNo().getId()));
		if(bankId != null) {
			BankDetails bankDetails = bankService.findById(Integer.valueOf(bankId));
			payment.setBankDetails(bankDetails);
		}
		payment.setPaymentCompanyDetails(company);
		paymentService.saveAccount(payment);
		List<Accounts> accountList = accountService.fetchAccountName(principal.getName(), company);
		modelAndView.addObject("payment", new Payment());
		modelAndView.addObject("message", "Payment Details Successfully Added");
		modelAndView.setViewName("addPayment");
		modelAndView.addObject("accountList", accountList);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param modelAndView
	 */
	protected void makePageReadyforLoad(HttpServletRequest request, ModelAndView modelAndView, Company company) {
		String user = request.getUserPrincipal().getName();
		List<Accounts> accountList = accountService.fetchAccountName(user, company);
		Payment payment = new Payment();
		modelAndView.addObject("payment", payment);
		modelAndView.setViewName("addPayment");
		modelAndView.addObject("accountList", accountList);
	}

	@RequestMapping(value = { "/home/showpayment" }, method = RequestMethod.GET)
	public ModelAndView showPayments(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		String user = principal.getName();
		modelAndView.addObject("paymentList", paymentService.fetchAllPayment(user));
		modelAndView.setViewName("paymentData");
		return modelAndView;
	}

}
