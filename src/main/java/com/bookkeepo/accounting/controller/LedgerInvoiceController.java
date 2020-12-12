/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.model.LedgerColumns;
import com.bookkeepo.accounting.model.LedgerInfo;
import com.bookkeepo.accounting.model.LedgerInvoiceInfo;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.LedgerUtil;

/**
 * @author Ashutosh Anand
 *
 */
@Controller
public class LedgerInvoiceController extends MasterController {

	@RequestMapping(value = { "/home/invoiceledger" }, method = RequestMethod.GET)
	public ModelAndView showLedger(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user = request.getUserPrincipal().getName();
		modelAndView.addObject("ledger", new LedgerInvoiceInfo());
		modelAndView.setViewName("invoiceledger");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/invoiceledger" }, method = RequestMethod.POST)
	public ModelAndView generateLedger(@Valid LedgerInvoiceInfo ledger, HttpServletRequest request, Principal principal)
			throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		String user = request.getUserPrincipal().getName();
		Company company = CommonUtils.getSessionAttributes(request);
		if (CommonUtils.isPopulated(ledger.getStartDate()) && CommonUtils.isPopulated(ledger.getEndDate())
				&& !CommonUtils.isValidEndDate(ledger.getStartDate(), ledger.getEndDate())) {
			modelAndView.addObject("ledger", new LedgerInfo());
			modelAndView.setViewName("ledger");
			return modelAndView;
		}
		Date startDate = CommonUtils.convertToDate(ledger.getStartDate());
		Date endDate = CommonUtils.convertToDate(ledger.getEndDate());
		endDate.setTime(endDate.getTime() + 86399000L);
		List<String> invoiceTypeData = new ArrayList<String>();
		if (!ledger.getInvoiceType().equals(InvoiceType.Purchase_Invoice.getType())) {
			invoiceTypeData.add(InvoiceType.Tax_Invoice.getType());
			invoiceTypeData.add(InvoiceType.Export_Invoice.getType());
			invoiceTypeData.add(InvoiceType.Retail_Invoice.getType());
			invoiceTypeData.add(InvoiceType.Bill_Supply.getType());
		} else {
			invoiceTypeData.add(ledger.getInvoiceType());
		}
		List<InvoiceDetails> invoiceData = invoiceService.findByStartEndDate(invoiceTypeData, user, startDate, endDate);

		List<LedgerColumns> ledgerData = LedgerUtil.ledgerforInvoices(invoiceData);

		modelAndView.addObject("ledger", ledger);
		modelAndView.addObject("ledgerData", ledgerData);
		modelAndView.setViewName("invoiceledger");
		return modelAndView;

	}

}
