/**
 * 
 */
package in.winwithweb.gst.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.User;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.service.EmailService;
import in.winwithweb.gst.service.UserService;
import in.winwithweb.gst.util.CommonUtils;

/**
 * @author sachingoyal
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	CompanyDetailsService companyDetailsService;

	@Autowired
	EmailService emailservice;

	@Value("${email.from}")
	private String emailFrom;

	@Value("${email.subject.account.act}")
	private String actSubject;

	@Value("${email.body.account.act}")
	private String actBody;

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
			emailservice.sendEmail(user, emailFrom, actSubject, actBody);

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
			modelAndView.addObject("message", "Email is not registered with BookKeepo");
		} else {
			boolean isValidToken = CommonUtils.isValidToken(token, userExists.getToken());
			if (isValidToken) {
				userExists.setActive(1);
				userService.updateUser(userExists);
				modelAndView.addObject("message", "Your BookKeepo account is successfully Activated");
			} else {
				modelAndView.addObject("message",
						"Your account is not activated. Please connect with system administrator for more details.");
			}
		}
		return modelAndView;

	}
}
