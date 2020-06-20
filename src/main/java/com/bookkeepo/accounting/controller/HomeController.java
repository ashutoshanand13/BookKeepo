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
import org.thymeleaf.util.StringUtils;

import com.bookkeepo.accounting.entity.Company;

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
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
			//modelAndView.addObject("CompanyGSTIN", "");
			session.setAttribute("CompanyGSTIN", "menu_withoutCompanyGSTIN");
		} else {
			modelAndView.setViewName("home");
			if(StringUtils.isEmpty(company.getCompanyGstin())) {
				session.setAttribute("CompanyGSTIN", "menu_withoutCompanyGSTIN");
			}
			else {
				session.setAttribute("CompanyGSTIN", "menu_withCompanyGSTIN");
			}
		}
		return modelAndView;
	}

}
