package in.winwithweb.gst.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.User;
import in.winwithweb.gst.model.UserDetails;
import in.winwithweb.gst.service.UserService;

@Controller
public class ChangePasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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

		if (userExists == null || !bCryptPasswordEncoder.matches(user.getOldPassword(), userExists.getPassword())) {

			modelAndView.addObject("message", "Current Password does not match");

		} else if (user.getOldPassword().equals(user.getNewPassword())) {
			modelAndView.addObject("message", "Please use the different password");

		} else {
			modelAndView.addObject("message", "Password successfully changed");

			userExists.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
			userService.saveUser(userExists);

		}

		return modelAndView;
	}

}
