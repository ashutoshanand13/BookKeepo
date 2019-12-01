/**
 * 
 */
package in.winwithweb.gst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import in.winwithweb.gst.model.Payment;
import in.winwithweb.gst.model.Receipts;
import in.winwithweb.gst.service.AccountService;

/**
 * @author sachingoyal
 *
 */

@Controller
public class HomeController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView getAddAccount() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value = { "/addPayment" }, method = RequestMethod.GET)
	public ModelAndView addPayment() {
		ModelAndView modelAndView = new ModelAndView();
		Payment payment = new Payment();
		modelAndView.addObject("payment", payment);
		modelAndView.setViewName("addPayment");
		modelAndView.addObject("accountList", accountService.fetchAccountName());
		return modelAndView;
	}

	@RequestMapping(value = "/getGstinData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getSuperVisiorData(@RequestParam String accountNbr) {
		return new Gson().toJson(accountService.findAccountByAccountName(accountNbr));
	}

	@RequestMapping(value = { "/addReceipt" }, method = RequestMethod.GET)
	public ModelAndView getAddReceipt() {
		ModelAndView modelAndView = new ModelAndView();
		Receipts receipt = new Receipts();
		modelAndView.addObject("receipts", receipt);
		modelAndView.setViewName("addReceipt");
		modelAndView.addObject("accountList", accountService.fetchAccountName());
		return modelAndView;
	}

}
