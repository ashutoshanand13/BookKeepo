/**
 * 
 */
package in.winwithweb.gst.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import in.winwithweb.gst.model.Accounts;
import in.winwithweb.gst.model.Payment;
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
	public ModelAndView getHomePage() {
		ModelAndView modelAndView = new ModelAndView();
		Accounts account = new Accounts();
		modelAndView.addObject("account", account);
		modelAndView.setViewName("addaccount");
		return modelAndView;
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView addNewAccount(@Valid @ModelAttribute("account") Accounts account, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Accounts accountWithGstInExists = accountService.findAccountByGstin(account.getGstin());

		if (accountWithGstInExists != null) {
			bindingResult.rejectValue("gstin", "gstin", "This GST number is already registered.");
		}else if(account.getGstin().trim().length() != 15) {
			bindingResult.rejectValue("gstin", "gstin", "Please provide a valid GST number.");

		}

		Accounts accountWithPanExists = accountService.findAccountByPan(account.getAccountPan());
		if (accountWithPanExists != null) {
			bindingResult.rejectValue("accountPan", "accountPan", "This PAN is already registered.");
		}else if(account.getAccountPan().trim().length() != 10) {
			bindingResult.rejectValue("accountPan", "accountPan", "Please provide a valid PAN.");

		}

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("account", account);
			modelAndView.setViewName("addaccount");
		} else {
			accountService.saveAccount(account);
			modelAndView.addObject("message", "New Account Details Successfully Added");
			modelAndView.addObject("account", new Accounts());
			modelAndView.setViewName("addaccount");
		}
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
	
	@RequestMapping(value = { "/page1" }, method = RequestMethod.GET)
	public String getPage1() {
		return "page1";
	}
	
	@RequestMapping(value = { "/page2" }, method = RequestMethod.GET)
	public String getPage2() {
		return "page2";
	}
	
	@RequestMapping(value = { "/base" }, method = RequestMethod.GET)
	public String getBase() {
		return "base";
	}

}
