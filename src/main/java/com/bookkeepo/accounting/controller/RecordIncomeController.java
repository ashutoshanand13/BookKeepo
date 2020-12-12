package com.bookkeepo.accounting.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.RecordIncome;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
 * @author Ashutosh Anand
 */

@Controller
public class RecordIncomeController extends MasterController {

	@RequestMapping(value = { "/home/recordincome" }, method = RequestMethod.GET)
	public ModelAndView showPage(HttpServletRequest request, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("income", new RecordIncome());
		modelAndView.addObject("accountList",
				accountService.fetchAccountNameForExpenseAndIncome(principal.getName(), Constants.SUNDRY_DEBTOR));
		modelAndView.setViewName("recordincome");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/recordincome" }, method = RequestMethod.POST)
	public ModelAndView addIncome(@Valid @ModelAttribute("income") RecordIncome recordincome,
			BindingResult bindingResult, Principal principal,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("recordincome");
		Company company = CommonUtils.getSessionAttributes(request);
		recordincome.setRecordIncomeOwner(principal.getName());
		recordincome.setIncomeDate(InvoiceUtil.reverseDate(recordincome.getIncomeDate()));
		recordincome.setIncomeWithAccountReference(
				accountService.findById(recordincome.getIncomeWithAccountReference().getId()));
		
		if(recordincome.getIncomePayableWithAccountReference().getId() == 0) {
			Accounts account = new Accounts();
			account.setAccountName(recordincome.getIncomeWithAccountReference().getAccountName()+" Payable");
			account.setAccountType(Constants.SUNDRY_DEBTOR);
			account.setAccountState("-1");
			account.setAccountCompanyDetails(company);
			account.setAccountOwner(principal.getName());
			account = accountService.saveAccount(account);
			recordincome.setIncomePayableWithAccountReference(account);
		} else {
			recordincome.setIncomePayableWithAccountReference(
					accountService.findById(recordincome.getIncomePayableWithAccountReference().getId()));
		}
		
		incomeService.saveRecordIncomeEntry(recordincome);
		modelAndView.addObject("message", "Record Income Added Successfully");
		modelAndView.addObject("accountList",
				accountService.fetchAccountNameForExpenseAndIncome(principal.getName(), Constants.SUNDRY_DEBTOR));
		modelAndView.addObject("income", new RecordIncome());
		return modelAndView;
	}

}
