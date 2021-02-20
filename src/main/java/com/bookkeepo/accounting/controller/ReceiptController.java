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

import com.bookkeepo.accounting.dtos.AccountDto;
import com.bookkeepo.accounting.entity.BankDetails;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.ReceiptInvoices;
import com.bookkeepo.accounting.entity.Receipts;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
 * @author sachingoyal
 *
 */

@Configuration
@Controller
public class ReceiptController extends MasterController{

	@RequestMapping(value = { "/home/addreceipt" }, method = RequestMethod.GET)
	public ModelAndView getAddReceipt(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = CommonUtils.getSessionAttributes(request);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			makePageReadyforLoad(request, modelAndView, company);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/home/addreceipt", method = RequestMethod.POST)
	public ModelAndView addNewReceipt(@Valid @ModelAttribute("receipts") Receipts receipt, @RequestParam(required = false) String bankId, BindingResult bindingResult,
			Principal principal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = CommonUtils.getSessionAttributes(request);
		receipt.setReceiptOwner(principal.getName());
		receipt.setAccountRefNo(accountService.findById(receipt.getAccountRefNo().getId()));
		if(bankId != null) {
			BankDetails bankDetails = bankService.findById(Integer.valueOf(bankId));
			receipt.setBankDetails(bankDetails);
		}
		receipt.setReceiptCompanyDetails(company);
		checkReceiptInvoiceDetails(receipt);
		
		String formatDate = InvoiceUtil.reverseDate(receipt.getReceiptDate());
		receipt.setReceiptDate(formatDate);
		savePaidInvoices(receipt.getReceiptInvoiceDetails());
		receiptService.saveAccount(receipt);
		List<AccountDto> accountList = accountService.findAccounts(principal.getName(), company);
		modelAndView.addObject("receipts", new Receipts());
		modelAndView.addObject("message", "Receipt Details Successfully Added");
		modelAndView.setViewName("addReceipt");
		modelAndView.addObject("accountList", accountList);
		return modelAndView;
	}
	
	private void savePaidInvoices(List<ReceiptInvoices> receiptInvoiceDetails) {
		if(receiptInvoiceDetails != null) {
			for (ReceiptInvoices invoicePay : receiptInvoiceDetails) {
				invoiceService.updateInvoicePaidAmt(invoicePay.getReceiptInvoiceAmount(),
						Integer.valueOf(invoicePay.getReceiptInvoiceId()));
			}
		}
		
	}

	private void checkReceiptInvoiceDetails(@Valid Receipts receipt) {
		if (!receipt.getReceiptReference().equals("Invoice Ref")) {
			receipt.setReceiptInvoiceDetails(null);
		} else {
			List<ReceiptInvoices> temp = receipt.getReceiptInvoiceDetails();
			List<ReceiptInvoices> validPayment = temp.stream()
					.filter(e -> !e.getReceiptInvoiceAmount().equals("") && !e.getReceiptInvoiceDueAmount().equals(""))
					.collect(Collectors.toList());
			validPayment.stream()
					.peek(f -> f.setReceiptInvoiceNumber(
							invoiceService.findByInvoiceUniqueKey(f.getReceiptInvoiceNumber()).getInvoiceNumber()))
					.collect(Collectors.toList());

			receipt.setReceiptInvoiceDetails(validPayment);
		}

	}
	
	protected void makePageReadyforLoad(HttpServletRequest request, ModelAndView modelAndView, Company company) {
		String user = request.getUserPrincipal().getName();
		List<AccountDto> accountList = accountService.findAccounts(user, company);
		Receipts receipt = new Receipts();
		addReceiptInvoices(receipt);
		modelAndView.addObject("receipts", receipt);
		modelAndView.setViewName("addReceipt");
		modelAndView.addObject("accountList", accountList);
	}

	private void addReceiptInvoices(Receipts receipt) {
		List<ReceiptInvoices> invoiceList = new ArrayList<ReceiptInvoices>();
		for (int i = 0; i < Constants.INVOICE_REF_COUNT; i++) {
			invoiceList.add(new ReceiptInvoices());
		}
		receipt.setReceiptInvoiceDetails(invoiceList);

	}

	@RequestMapping(value = { "/home/showreceipt" }, method = RequestMethod.GET)
	public ModelAndView showReceipt(Principal principal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user = principal.getName();
		Company company = CommonUtils.getSessionAttributes(request);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			modelAndView.addObject("receiptList", receiptService.fetchAllReceipt(user, CommonUtils.getSessionAttributes(request)));
			modelAndView.setViewName("receiptData");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = { "/home/updatereceipt" }, method = RequestMethod.GET)
	public ModelAndView updatePayment(Principal principal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user = principal.getName();
		modelAndView.addObject("receiptList", receiptService.fetchAllReceipt(user, CommonUtils.getSessionAttributes(request)));
		modelAndView.setViewName("updateReceipt");
		return modelAndView;
	}

	
	@RequestMapping(value = { "/home/updatereceipt/{id}" })
	public ModelAndView updatePaymentKey(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {

		String user = request.getUserPrincipal().getName();
		Receipts receipt = receiptService.findByIdAndReceiptOwner(Integer.valueOf(id), user);
		receipt.setReceiptDeleted(1);
		if(receipt.getReceiptInvoiceDetails().size()>0) {
			for (ReceiptInvoices invoicePay : receipt.getReceiptInvoiceDetails()) {
				invoiceService.updateInvoicePaidAmt("-"+invoicePay.getReceiptInvoiceAmount(),
						Integer.valueOf(invoicePay.getReceiptInvoiceId()));
			}
		}
		receiptService.saveAccount(receipt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/home/updatereceipt");
		return modelAndView;
		
	}
}
