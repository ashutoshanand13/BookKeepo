/**
 * 
 */
package in.winwithweb.gst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Payment;
import in.winwithweb.gst.service.AccountService;

/**
 * @author sachingoyal
 *
 */

@Controller
public class PaymentController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = { "/home/addpayment" }, method = RequestMethod.GET)
	public ModelAndView addPayment(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user=request.getUserPrincipal().getName();
		List<String> accountList = accountService.fetchAccountName(user);
		if(accountList != null) {
			accountList.add(0, "Select Account");
		}
		Payment payment = new Payment();
		modelAndView.addObject("payment", payment);
		modelAndView.setViewName("addPayment");
		modelAndView.addObject("accountList", accountList);
		return modelAndView;
	}	

}
