/**
 * 
 */
package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.util.CommonUtils;

/**
 * @author sachingoyal
 *
 */

@Controller
public class HomeController extends MasterController {

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView getAddAccount(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String user = request.getUserPrincipal().getName();
		Company company = CommonUtils.getSessionAttributes(request);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			modelAndView.setViewName("home");
		}
		return modelAndView;
	}

}
