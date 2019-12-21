/**
 * 
 */
package in.winwithweb.gst.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.User;
import in.winwithweb.gst.service.UserService;

/**
 * @author sachingoyal
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String setup(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/home/salesinvoice", method = RequestMethod.GET)
	public String setupSales(ModelMap model) {
		return "salesInvoice";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView setupform(ModelMap model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		if (error != null) {
			modelAndView.addObject("message", "Invalid Email or Password!");
		}

		if (logout != null) {
			modelAndView.addObject("message", "Logout Successfully!");
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
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.addObject("message", "Registration Successful");
			modelAndView.setViewName("login");

		}
		return modelAndView;
	}

}
