package in.winwithweb.gst.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.service.CompanyDetailsService;

@Controller
public class CompanyController {
	
	@Autowired
	CompanyDetailsService companyDetailsService;
	
	@RequestMapping(value = { "/home/addcompany" }, method = RequestMethod.GET)
	public ModelAndView getAddCompanyPage(HttpServletRequest request) {
		String user=request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		modelAndView.addObject("company", company!= null ? company : new Company());
		modelAndView.setViewName("addCompany");
		return modelAndView;
	}
	
	@RequestMapping(value = "/home/addcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		company.setUserName(principal.getName());
		modelAndView.addObject("company", company);
		modelAndView.setViewName("addCompany");
		companyDetailsService.save(company);
		return modelAndView;
	}
	
}
