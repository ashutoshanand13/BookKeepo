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

import com.google.gson.Gson;

import in.winwithweb.gst.service.AccountService;
import in.winwithweb.gst.util.CommonUtils;

/**
 * @author sachingoyal
 *
 */

@Controller
public class AjaxController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/home/getGstinData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getSuperVisiorData(@RequestParam String accountNbr) {
		return new Gson().toJson(accountService.findAccountByAccountName(accountNbr));
	}
	
	@RequestMapping(value = "/home/getAmountInWords", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAmountInWords(@RequestParam String amount) {
		return new Gson().toJson(CommonUtils.numberConverter(amount));
	}

}
