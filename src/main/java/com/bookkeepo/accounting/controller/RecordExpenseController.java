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
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
* @author Ashutosh Anand
*/

@Controller
public class RecordExpenseController extends MasterController {
	
	@RequestMapping(value = { "/home/recordexpense" }, method = RequestMethod.GET)
	public ModelAndView showPage(HttpServletRequest request,Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("expense", new RecordExpense());
		modelAndView.addObject("accountList", accountService.findByAccountOwnerAndAccountTypes
				(principal.getName(), Constants.expenseAccountTypes));
		modelAndView.setViewName("recordexpense");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/home/recordexpense" }, method = RequestMethod.POST)
	public ModelAndView addRecordExpense(@Valid @ModelAttribute("expense") RecordExpense recordexpense,
			BindingResult bindingResult,
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView("recordexpense");
		recordexpense.setRecordExpenseOwner(principal.getName());
		recordexpense.setExpenseDate(InvoiceUtil.reverseDate(recordexpense.getExpenseDate()));
		recordexpense.setExpenseWithAccountReference(accountService.findById(recordexpense.getExpenseWithAccountReference().getId()));
		expenseService.saveRecordExpenseEntry(recordexpense);
		modelAndView.addObject("message", "Record Expense Added Successfully");
		modelAndView.addObject("accountList", accountService.findByAccountOwnerAndAccountTypes
				(principal.getName(), Constants.expenseAccountTypes));
		modelAndView.addObject("expense", new RecordExpense());
		return modelAndView;
	}

}
