package in.winwithweb.gst.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Accounts;
import in.winwithweb.gst.model.Company;

@Controller
public class CompanyController {
	
	@RequestMapping(value = { "/home/addcompany" }, method = RequestMethod.GET)
	public ModelAndView getAddCompanyPage() {
		ModelAndView modelAndView = new ModelAndView();
		Company company = new Company();
		modelAndView.addObject("company", company);
		modelAndView.setViewName("addCompany");
		return modelAndView;
	}
	
	@RequestMapping(value = "/home/addcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@Valid @ModelAttribute("Company") Accounts account, BindingResult bindingResult, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = new Company();
		modelAndView.addObject("company", company);
		modelAndView.setViewName("addCompany");
		return modelAndView;
	}
	
}
