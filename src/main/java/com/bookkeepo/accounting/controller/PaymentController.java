/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.BankDetails;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.Payment;
import com.bookkeepo.accounting.entity.PaymentInvoices;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
 * @author sachingoyal
 *
 */
@Configuration
@Controller
public class PaymentController extends MasterController {

	@RequestMapping(value = { "/home/addpayment" }, method = RequestMethod.GET)
	public ModelAndView getPaymentScreen(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user = request.getUserPrincipal().getName();

		Company company = CommonUtils.getSessionAttributes(request);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			makePageReadyforLoad(request, modelAndView, company);
		}
		return modelAndView;
	}

	@RequestMapping(value = { "/home/addpayment" }, method = RequestMethod.POST)
	public ModelAndView addNewPayment(@Valid @ModelAttribute("payment") Payment payment,
			@RequestParam(required = false) String bankId, BindingResult bindingResult, Principal principal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = CommonUtils.getSessionAttributes(request);
		payment.setPaymentOwner(principal.getName());
		payment.setAccountRefNo(accountService.findById(payment.getAccountRefNo().getId()));
		if (bankId != null) {
			BankDetails bankDetails = bankService.findById(Integer.valueOf(bankId));
			payment.setBankDetails(bankDetails);
		}
		payment.setPaymentCompanyDetails(company);
		checkPaymentInvoiceDetails(payment);
		String formatDate = InvoiceUtil.reverseDate(payment.getPaymentDate());
		payment.setPaymentDate(formatDate);
		paymentService.saveAccount(payment);
		List<Accounts> accountList = accountService.findAccounts(principal.getName(), company);
		Payment newPayment = new Payment();
		addPaymentInvoices(newPayment);
		modelAndView.addObject("payment", newPayment);
		modelAndView.addObject("message", "Payment Details Successfully Added");
		modelAndView.setViewName("addPayment");
		modelAndView.addObject("accountList", accountList);
		return modelAndView;
	}

	private void checkPaymentInvoiceDetails(@Valid Payment payment) {
		if (!payment.getPaymentReference().equals("Invoice Ref")) {
			payment.setPaymentInvoiceDetails(null);
		} else {
			List<PaymentInvoices> temp = payment.getPaymentInvoiceDetails();
			List<PaymentInvoices> validPayment = temp.stream()
					.filter(e -> !e.getPaymentInvoiceAmount().equals("") && !e.getPaymentInvoiceDueAmount().equals(""))
					.collect(Collectors.toList());
			validPayment.stream()
					.peek(f -> f.setPaymentInvoiceNumber(
							invoiceService.findByInvoiceUniqueKey(f.getPaymentInvoiceNumber()).getInvoiceNumber()))
					.collect(Collectors.toList());

			payment.setPaymentInvoiceDetails(validPayment);
		}

	}

	/**
	 * @param request
	 * @param modelAndView
	 */
	protected void makePageReadyforLoad(HttpServletRequest request, ModelAndView modelAndView, Company company) {
		String user = request.getUserPrincipal().getName();
		List<Accounts> accountList = accountService.findAccounts(user, company);
		Payment payment = new Payment();
		addPaymentInvoices(payment);
		modelAndView.addObject("payment", payment);
		modelAndView.setViewName("addPayment");
		modelAndView.addObject("accountList", accountList);
	}

	private void addPaymentInvoices(Payment payment) {
		List<PaymentInvoices> invoiceList = new ArrayList<PaymentInvoices>();
		for (int i = 0; i < Constants.INVOICE_REF_COUNT; i++) {
			invoiceList.add(new PaymentInvoices());
		}
		payment.setPaymentInvoiceDetails(invoiceList);

	}

	@RequestMapping(value = { "/home/showpayment" }, method = RequestMethod.GET)
	public ModelAndView showPayments(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		String user = principal.getName();
		modelAndView.addObject("paymentList", paymentService.fetchAllPayment(user));
		modelAndView.setViewName("paymentData");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/updatepayment" }, method = RequestMethod.GET)
	public ModelAndView updatePayment(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		String user = principal.getName();
		modelAndView.addObject("paymentList", paymentService.fetchAllPayment(user));
		modelAndView.setViewName("updatePayment");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/updatepayment/{id}" })
	public ModelAndView updatePaymentKey(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {

		String user = request.getUserPrincipal().getName();
		Payment payment = paymentService.findByIdAndPaymentOwner(Integer.valueOf(id), user);
		payment.setPaymentDeleted(1);
		paymentService.saveAccount(payment);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/home/updatepayment");
		return modelAndView;

	}
}
