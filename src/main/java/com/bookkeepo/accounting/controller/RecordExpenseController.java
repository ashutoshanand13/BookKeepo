package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.model.RecordExpense;

/**
* @author Ashutosh
*/

@Controller
public class RecordExpenseController extends MasterController {
	
	@RequestMapping(value = { "/home/recordexpense" }, method = RequestMethod.GET)
	public ModelAndView showPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("expense", new RecordExpense());
		modelAndView.setViewName("recordexpense");
		return modelAndView;
	}

}
