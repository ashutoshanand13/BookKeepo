package com.bookkeepo.accounting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;

/**
 * @author Ashutosh Anand
 *
 */
@Controller
public class AccountListController extends MasterController{
	
	@RequestMapping(value = { "/home/accountlist" }, method = RequestMethod.GET)
	public ModelAndView getAccountListPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user = request.getUserPrincipal().getName();
		Company company = CommonUtils.getSessionAttributes(request);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			modelAndView.addObject("accountList",
					accountService.findAccounts(request.getUserPrincipal().getName(), company));
			modelAndView.addObject("accountLedgerType",accountLedgerService.findAllAccountType());
			modelAndView.addObject("stateMap",Constants.gstCodeStateMap);
			modelAndView.addObject("account", new Accounts());
			modelAndView.setViewName("accountList");
		}

		return modelAndView;
	}

}
