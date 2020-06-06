/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.User;
import com.bookkeepo.accounting.model.UserDetails;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;

/**
 * @author sachingoyal
 *
 */
@Controller
public class LoginController extends MasterController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String setup(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView setupform(ModelMap model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		if (error != null) {
			modelAndView.addObject("message", loginregistermessageproperties.getInCorrectLoginMessage());
		}

		if (logout != null) {
			modelAndView.addObject("message", loginregistermessageproperties.getLogoutSuccessMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					loginregistermessageproperties.getUserAlreadyExistsMessage());
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", loginregistermessageproperties.getRegistrationSuccessMessage());
			modelAndView.addObject("user", new User());
			modelAndView.addObject("message", loginregistermessageproperties.getRegistrationSuccessMessage());
			modelAndView.setViewName("login");
			emailservice.sendEmail(user, messageproperties.getEmailFrom(), messageproperties.getAcctactivationSubject(),
					messageproperties.getAcctactivationBody());

		}
		return modelAndView;
	}

	@RequestMapping(value = { "/accountactivation/{email}/{token}" })
	public ModelAndView view(@PathVariable("email") String email, @PathVariable("token") String token,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		User userExists = userService.findUserByEmail(email);
		if (userExists == null) {
			modelAndView.addObject("message", loginregistermessageproperties.getUserNotFoundMessage());
		} else {
			boolean isValidToken = CommonUtils.isValidToken(token, userExists.getToken());
			if (isValidToken) {
				userExists.setActive(1);
				userService.updateUser(userExists);
				modelAndView.addObject("message", loginregistermessageproperties.getSuccessAcctActivationMessage());
			} else {
				modelAndView.addObject("message", loginregistermessageproperties.getFailedAcctActivationMessage());
			}
		}
		return modelAndView;

	}

	@RequestMapping(value = "/signInError", method = RequestMethod.GET)
	public ModelAndView forgotPassword() {
		ModelAndView modelAndView = new ModelAndView();
		UserDetails user = new UserDetails();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("signInError");
		return modelAndView;
	}

	@RequestMapping(value = "/signInError", method = RequestMethod.POST)
	public ModelAndView forgotPassword(@Valid UserDetails user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		User userExists = userService.findUserByEmail(user.getEmail());
		modelAndView.addObject("user", user);
		modelAndView.setViewName("signInError");

		if (userExists == null) {
			modelAndView.addObject("user", user);
			modelAndView.addObject("message", loginregistermessageproperties.getUserNotFoundMessage());

		} else {
			modelAndView.addObject("user", new UserDetails());

			if (Constants.FORGET_PASSWORD_IDENTIFIER.equals(user.getTrouble())) {
				String newPassword = CommonUtils.generateCommonTextPassword();
				modelAndView.addObject("message", loginregistermessageproperties.getNewPasswordSentMessage());
				userExists.setPassword(bCryptPasswordEncoder.encode(newPassword));
				emailservice.forgetPassword(userExists, messageproperties.getEmailFrom(),
						messageproperties.getForgetPasswordSubject(), messageproperties.getForgetPasswordBody(),
						newPassword);
				userService.updateUser(userExists);
			} else {
				String newToken = CommonUtils.generateToken(userExists.getName());
				modelAndView.addObject("message", loginregistermessageproperties.getActivationLinkSentMessage());
				userExists.setToken(newToken);
				emailservice.sendEmail(userExists, messageproperties.getEmailFrom(),
						messageproperties.getAcctactivationSubject(), messageproperties.getAcctactivationBody());
				userService.updateUser(userExists);
			}
		}

		return modelAndView;
	}
}
