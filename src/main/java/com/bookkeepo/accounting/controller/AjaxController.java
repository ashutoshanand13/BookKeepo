/**
 * 
 */
package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.service.AccountService;
import com.bookkeepo.accounting.service.InvoiceService;
import com.bookkeepo.accounting.service.ItemService;
import com.bookkeepo.accounting.util.CommonUtils;
import com.google.gson.Gson;

/**
 * @author sachingoyal
 *
 */

@Controller
public class AjaxController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ItemService itemService;

	@Autowired
	Gson gson;

	@RequestMapping(value = "/home/getAccountData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAccountData(@RequestParam int accountName, HttpServletRequest request) {
		return gson.toJson(accountService.findById(accountName));
	}

	@RequestMapping(value = "/home/getAmountInWords", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAmountInWords(@RequestParam String amount) {
		return gson.toJson(CommonUtils.numberConverter(amount));
	}

	@RequestMapping(value = "/home/imagebase64", method = RequestMethod.GET, produces = "text/plain")
	public @ResponseBody String getImageBase64() {
		return CommonUtils.getImgfromResource("/static/images/image-400x400.jpg");
	}

	@RequestMapping(value = "/home/invoicedetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getInvoiceDetails(@RequestParam String invoiceNo, HttpServletRequest request) {
		return gson.toJson(invoiceService.findByInvoiceNumberAndInvoiceOwnerAndInvoiceType(invoiceNo,
				InvoiceType.Tax_Invoice.getType(), request.getUserPrincipal().getName()));
	}

	@RequestMapping(value = "/home/getItemData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getItemDetails(@RequestParam int itemId, HttpServletRequest request) {
		return gson.toJson(itemService.findById(itemId));
	}

	@RequestMapping(value = "/home/invoiceunique", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getInvoice(@RequestParam String invoiceNo, @RequestParam String pageName,
			HttpServletRequest request) {
		return gson.toJson(invoiceService.findByInvoiceNumberAndInvoiceOwnerAndInvoiceType(invoiceNo, pageName,
				request.getUserPrincipal().getName()));
	}
	
	@RequestMapping(value = "/home/getAccountName", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAccountNameUnique(@RequestParam String accountName, HttpServletRequest request) {
		return gson.toJson(accountService.findByAccountOwnerAndAccountName(request.getUserPrincipal().getName(), accountName));
	}
	
	@RequestMapping(value = "/home/getaccountlist", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAccountList(HttpServletRequest request) {
		return gson.toJson(accountService.fetchAccountNameForInvoice(request.getUserPrincipal().getName()));
	}

}
