/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.LedgerColumns;
import com.bookkeepo.accounting.model.LedgerInfo;
import com.bookkeepo.accounting.model.TrialBalanceCol;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.InvoiceUtil;
import com.bookkeepo.accounting.util.LedgerUtil;

/**
 * @author Akarsh
 *
 */

@Controller
public class TrialBalanceController extends MasterController{

	@RequestMapping(value = { "/home/trialBalanceReport" }, method = RequestMethod.GET)
	public ModelAndView showInvoice(Principal principal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = CommonUtils.getSessionAttributes(request);
		List<Accounts> accountList = accountService.findAccountList(principal.getName(), company);
		Map<Accounts, List<TrialBalanceCol>> ledgerMap = new HashMap();
		LedgerInfo ledger = new LedgerInfo();
		int financialYear = InvoiceUtil.getFiscalYear();
		String date ="-04-01";
		ledger.setStartDate(String.valueOf(financialYear)+date);
		ledger.setEndDate(String.valueOf(financialYear+1)+date);
		
		for ( Accounts account : accountList) {
				ledgerMap.putAll(LedgerUtil.trialBalanceLedger(account,
						ledger));
		}
		modelAndView.addObject("accountDetails",accountList);
		modelAndView.addObject("ledger",ledgerMap);
		modelAndView.setViewName("trialBalanceReport");
		return modelAndView;
	}
}
