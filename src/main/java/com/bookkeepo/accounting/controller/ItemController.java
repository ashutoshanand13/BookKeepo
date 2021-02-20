/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.ProductDetails;
import com.bookkeepo.accounting.util.CommonUtils;

/**
 * @author Yash Singh
 *
 */

@Controller
public class ItemController extends MasterController {

	@RequestMapping(value = { "/home/additem" }, method = RequestMethod.GET)
	public ModelAndView getItemPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("addItem");
		String user = request.getUserPrincipal().getName();
		Company company = CommonUtils.getSessionAttributes(request);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			List<ProductDetails> itemList = itemService.fetchAllItemsForItems(user, company);
			modelAndView.addObject("itemList", itemList);
			modelAndView.addObject("item", new ProductDetails());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/home/additem", method = RequestMethod.POST)
	public ModelAndView addItem(@Valid @ModelAttribute("item") ProductDetails item, BindingResult bindingResult,
			Principal principal,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("addItem");
		String user = principal.getName();
		Company company = CommonUtils.getSessionAttributes(request);
		item.setProductOwner(principal.getName());
		item.setProductCompanyDetails(company);
		itemService.saveProductItem(item);
		modelAndView.addObject("message", "Item Updated Successfully");
		modelAndView.addObject("item", new ProductDetails());
		modelAndView.addObject("itemList", itemService.fetchAllItemsForItems(user, company));
		return modelAndView;
	}

	@RequestMapping(value = { "/home/showitem" }, method = RequestMethod.GET)
	public ModelAndView showItem(Principal principal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user = principal.getName();
		Company company = CommonUtils.getSessionAttributes(request);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			modelAndView.addObject("itemList", itemService.fetchAllItems(user));	
			modelAndView.setViewName("itemData");
		}
		return modelAndView;
	}

}
