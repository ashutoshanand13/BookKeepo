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
import in.winwithweb.gst.util.CommonUtils;

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
		modelAndView.addObject("userDetails", user);

		if (userExists == null || !bCryptPasswordEncoder.matches(user.getOldPassword(), userExists.getPassword())) {

			modelAndView.addObject("message", "Current Password does not match");

		} else if (user.getOldPassword().equals(user.getNewPassword())) {
			modelAndView.addObject("message", "Please use the different password");

		} else {
			modelAndView.addObject("message", "Password successfully changed");

			userExists.setPassword(user.getNewPassword());
			userService.saveUser(userExists);

		}

		return modelAndView;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ModelAndView forgotPassword() {
		ModelAndView modelAndView = new ModelAndView();
		UserDetails user = new UserDetails();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("forgotPassword");
		return modelAndView;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ModelAndView forgotPassword(@Valid UserDetails user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		User userExists = userService.findUserByEmail(user.getEmail());
		modelAndView.addObject("user", user);
		modelAndView.setViewName("forgotPassword");

		if (userExists == null) {
			modelAndView.addObject("message", "This email is not register with BookKeepo !");

		} else {
			String newPassword = CommonUtils.getUniqueID();
			modelAndView.addObject("message", "New Password sent on your email.");

			CommonUtils.sendEmail(userExists.getEmail(), "New Password", "Your new password " + newPassword);
			userExists.setPassword(newPassword);
			userService.saveUser(userExists);
		}

		return modelAndView;
	}

}
