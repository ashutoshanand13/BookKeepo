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

import com.bookkeepo.accounting.model.RecordExpense;
import com.bookkeepo.accounting.model.RecordIncome;
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
* @author Ashutosh Anand
*/

@Controller
public class RecordIncomeController extends MasterController {
	
	@RequestMapping(value = { "/home/recordincome" }, method = RequestMethod.GET)
	public ModelAndView showPage(HttpServletRequest request,Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("income", new RecordIncome());
		modelAndView.addObject("accountList", accountService.findByAccountOwnerAndAccountTypes
				(principal.getName(), Constants.incomeAccountTypes));
		modelAndView.setViewName("recordincome");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/home/recordincome" }, method = RequestMethod.POST)
	public ModelAndView addIncome(@Valid @ModelAttribute("income") RecordIncome recordincome,
			BindingResult bindingResult,
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView("recordincome");
		recordincome.setRecordIncomeOwner(principal.getName());
		recordincome.setIncomeDate(InvoiceUtil.reverseDate(recordincome.getIncomeDate()));
		recordincome.setIncomeWithAccountReference(accountService.findById(recordincome.getIncomeWithAccountReference().getId()));
		incomeService.saveRecordIncomeEntry(recordincome);
		modelAndView.addObject("message", "Record Income Added Successfully");
		modelAndView.addObject("accountList", accountService.findByAccountOwnerAndAccountTypes
				(principal.getName(), Constants.incomeAccountTypes));
		modelAndView.addObject("income", new RecordIncome());
		return modelAndView;
	}

}
