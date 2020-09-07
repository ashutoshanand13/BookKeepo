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
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.model.LedgerColumns;
import com.bookkeepo.accounting.model.LedgerInfo;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;
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
		String user = request.getUserPrincipal().getName();
		Company company = companyDetailsService.findByUserName(user);
		modelAndView.addObject("ledger", new LedgerInfo());
		modelAndView.addObject("accountList",
				accountService.fetchAccountName(request.getUserPrincipal().getName(), company));
		modelAndView.setViewName("ledger");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/ledger" }, method = RequestMethod.POST)
	public ModelAndView generateLedger(@Valid LedgerInfo ledger, HttpServletRequest request, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		String user = request.getUserPrincipal().getName();
		Company company = companyDetailsService.findByUserName(user);
		modelAndView.addObject("accountList",
				accountService.fetchAccountName(request.getUserPrincipal().getName(), company));
		if (CommonUtils.isPopulated(ledger.getStartDate()) && CommonUtils.isPopulated(ledger.getEndDate())
				&& !CommonUtils.isValidEndDate(ledger.getStartDate(), ledger.getEndDate())) {
			modelAndView.addObject("ledger", new LedgerInfo());
			modelAndView.setViewName("ledger");
			return modelAndView;
		}
		
		Map<Accounts, List<LedgerColumns>> ledgerMap = null;
		
		if(accountService.findById(Integer.valueOf(ledger.getAccountId())).getAccountType().equals(Constants.DEFAULT_ACCOUNT_ON_COMPANY_CREATION)) {
			
			ledgerMap = LedgerUtil.setUpCashlLedgers(ledger,company);
		} else {
			List<InvoiceDetails> invoices = invoiceService.findByInvoiceAccountDetailsAndInvoiceOwner(
					accountService.findById(Integer.valueOf(ledger.getAccountId())), user);
			ledgerMap = LedgerUtil.setUpLedgers(invoices, ledger);
		}
		modelAndView.addObject("ledger", ledger);
		modelAndView.addObject("ledgerMap", ledgerMap);
		modelAndView.setViewName("ledger");
		return modelAndView;

	}

}
