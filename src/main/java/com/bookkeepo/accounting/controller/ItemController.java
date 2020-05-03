/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.ProductDetails;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.ItemService;

/**
 * @author Yash Singh
 *
 */

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	CompanyDetailsService companyDetailsService;

	@RequestMapping(value = { "/home/additem" }, method = RequestMethod.GET)
	public ModelAndView getItemPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("addItem");
		String user = request.getUserPrincipal().getName();
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			List<ProductDetails> itemList = itemService.fetchAllItemsForItems(user);
			modelAndView.addObject("itemList", itemList);
			modelAndView.addObject("item", new ProductDetails());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/home/additem", method = RequestMethod.POST)
	public ModelAndView addItem(@Valid @ModelAttribute("item") ProductDetails item, BindingResult bindingResult,
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView("addItem");
		String user = principal.getName();
		item.setProductOwner(principal.getName());
		item.setProductCompanyDetails(
				item.getId() == 0 ? companyDetailsService.findByUserName(principal.getName()) : null);
		itemService.saveProductItem(item);
		modelAndView.addObject("message", "Item Updated Successfully");
		modelAndView.addObject("item", new ProductDetails());
		modelAndView.addObject("itemList", itemService.fetchAllItemsForItems(user));
		return modelAndView;
	}

	@RequestMapping(value = { "/home/showitem" }, method = RequestMethod.GET)
	public ModelAndView showItem(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("itemData");
		String user = principal.getName();
		modelAndView.addObject("itemList", itemService.fetchAllItems(user));
		return modelAndView;
	}

}
