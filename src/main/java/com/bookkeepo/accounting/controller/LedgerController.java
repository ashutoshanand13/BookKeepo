/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.model.LedgerColumns;
import com.bookkeepo.accounting.model.LedgerInfo;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.LedgerUtil;

/**
 * @author Yash Singh
 *
 */
@Controller
public class LedgerController extends MasterController {
	
	@RequestMapping(value = { "/home/ledger" }, method = RequestMethod.GET)
	public ModelAndView showLedger(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ledger", new LedgerInfo());
		modelAndView.addObject("accountLedgerType",accountLedgerService.findAllAccountType());
		modelAndView.setViewName("ledger");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/home/ledger" }, method = RequestMethod.POST)
	public ModelAndView generateLedger(@Valid LedgerInfo ledger, HttpServletRequest request, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		if (CommonUtils.isPopulated(ledger.getStartDate()) && CommonUtils.isPopulated(ledger.getEndDate())
				&& !CommonUtils.isValidEndDate(ledger.getStartDate(), ledger.getEndDate())) {
		modelAndView.addObject("ledger", new LedgerInfo());
		modelAndView.addObject("accountLedgerType",accountLedgerService.findAllAccountType());
		modelAndView.setViewName("ledger");
		return modelAndView;
		}
		
		List<InvoiceDetails> invoices = invoiceService.findByAccountTypeAndAccountOwner(principal.getName(), ledger.getAccountType());
		Map<Accounts, List<LedgerColumns>> ledgerMap = LedgerUtil.setUpLedgers(invoices, ledger);
		modelAndView.addObject("ledger", ledger);
		modelAndView.addObject("ledgerMap", ledgerMap);
		modelAndView.addObject("accountLedgerType",accountLedgerService.findAllAccountType());
		modelAndView.setViewName("ledger");
		return modelAndView;
		
	}

}
