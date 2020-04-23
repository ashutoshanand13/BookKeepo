/**
 * 
 */
package in.winwithweb.gst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sachingoyal
 *
 */

@Controller
public class HomeController {

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView getAddAccount() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

}
