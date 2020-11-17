package com.bookkeepo.accounting.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.model.RecordContraCash;
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
* @author Ashutosh
*/

@Controller
public class RecordContraCashController extends MasterController {
	
	@RequestMapping(value = { "/home/recordcontracash" }, method = RequestMethod.GET)
	public ModelAndView showPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("contracash", new RecordContraCash());
		modelAndView.setViewName("recordcontracash");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/home/recordcontracash" }, method = RequestMethod.POST)
	public ModelAndView addRecordContraCash(@Valid @ModelAttribute("contracash") RecordContraCash contracash,
			BindingResult bindingResult,
			Principal principal,
			@RequestParam(required=false) String payFromBankId,
			@RequestParam(required=false) String payToBankId) {
		ModelAndView modelAndView = new ModelAndView("recordcontracash");
		contracash.setRecordContraOwner(principal.getName());
		contracash.setRecordContraDate(InvoiceUtil.reverseDate(contracash.getRecordContraDate()));
		if(payFromBankId != null) {
			String name = bankService.findById(Integer.valueOf(payFromBankId)).getUserBankAccount();
			Accounts bankDetails = accountService.findByAccountNameAndAccountType(name, Constants.DEFAULT_ACCOUNT_ON_BANK_CREATION);
			contracash.setBankDetailsFrom(bankDetails);
		}
		if(payToBankId!=null) {
			String name = bankService.findById(Integer.valueOf(payToBankId)).getUserBankAccount();
			Accounts bankDetails = accountService.findByAccountNameAndAccountType(name, Constants.DEFAULT_ACCOUNT_ON_BANK_CREATION);
			contracash.setBankDetailsTo(bankDetails);
		}
		contracashService.saveRecordContraCashEntry(contracash);
		modelAndView.addObject("message", "Record Contra Cash Added Successfully");
		modelAndView.addObject("contracash", new RecordContraCash());
		return modelAndView;
	}
	}
