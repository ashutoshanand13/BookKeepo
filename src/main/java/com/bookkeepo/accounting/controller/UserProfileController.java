package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.User;
import com.bookkeepo.accounting.model.UserDetails;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.UserService;

@Configuration
@Controller
public class UserProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CompanyDetailsService companyDetailsService;

	@RequestMapping(value = { "/home/changePassword" }, method = RequestMethod.GET)
	public ModelAndView getChangePasswordPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("changePassword");
		modelAndView.addObject("userDetails", new UserDetails());
		return modelAndView;
	}

	@RequestMapping(value = "/home/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@Valid UserDetails user, BindingResult bindingResult,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User userExists = userService.findUserByEmail(request.getUserPrincipal().getName());
		modelAndView.setViewName("changePassword");

		modelAndView.addObject("userDetails", user);

		if (userExists == null || !bCryptPasswordEncoder.matches(user.getOldPassword(), userExists.getPassword())) {

			modelAndView.addObject("message", "Current Password does not match");

		} else if (user.getOldPassword().equals(user.getNewPassword())) {
			modelAndView.addObject("message", "Please use the different password");

		} else {
			modelAndView.addObject("message", "Password successfully changed");

			userExists.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
			userService.updateUser(userExists);

		}

		return modelAndView;
	}

	@RequestMapping(value = { "/home/showProfile" }, method = RequestMethod.GET)
	public ModelAndView getAddAccount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(request.getUserPrincipal().getName());
		modelAndView.addObject("companyList", company);
		modelAndView.setViewName("showProfile");
		return modelAndView;
	}

}
