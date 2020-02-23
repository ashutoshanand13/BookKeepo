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
	public @ResponseBody String getSuperVisiorGstinData(@RequestParam String accountNbr) {
		return new Gson().toJson(accountService.findGSTByAccountName(accountNbr));
	}
	
	@RequestMapping(value = "/home/getPanNumberData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getSuperVisiorPanData(@RequestParam String accountNbr) {
		return new Gson().toJson(accountService.findPanByAccountName(accountNbr));
	}
	
	@RequestMapping(value = "/home/getAmountInWords", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAmountInWords(@RequestParam String amount) {
		return new Gson().toJson(CommonUtils.numberConverter(amount));
	}
	
	@RequestMapping(value = "/home/accountdetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAccountDetails(@RequestParam String accountName) {
		return new Gson().toJson(accountService.findByAccountName(accountName));
	}

	@RequestMapping(value = "/home/imagebase64", method = RequestMethod.GET, produces = "text/plain")
	public @ResponseBody String getImageBase64() {
		return CommonUtils.getImgfromResource("/static/images/image-400x400.jpg");
	}
	
}
