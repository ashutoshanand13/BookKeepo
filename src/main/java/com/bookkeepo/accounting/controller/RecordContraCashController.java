package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.model.RecordContraCash;

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

}
