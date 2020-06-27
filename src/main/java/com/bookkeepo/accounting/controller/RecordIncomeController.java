package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.model.RecordIncome;

/**
* @author Ashutosh
*/

@Controller
public class RecordIncomeController extends MasterController {
	
	@RequestMapping(value = { "/home/recordincome" }, method = RequestMethod.GET)
	public ModelAndView showPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("income", new RecordIncome());
		modelAndView.setViewName("recordincome");
		return modelAndView;
	}

}
