/**
 * 
 */
package in.winwithweb.gst.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.sales.InvoiceProductDetails;
import in.winwithweb.gst.service.ItemService;

/**
 * @author Yash Singh
 *
 */

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping(value = { "/home/additem" }, method = RequestMethod.GET)
	public ModelAndView getItemPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String user=request.getUserPrincipal().getName();
		modelAndView.addObject("itemList", itemService.findByProductOwner(user));
		modelAndView.addObject("item", new InvoiceProductDetails());
		
		modelAndView.setViewName("addItem");
		return modelAndView;
	}
	
	@RequestMapping(value = "/home/additem", method = RequestMethod.POST)
	public ModelAndView addCompany(@Valid @ModelAttribute("item") InvoiceProductDetails item, BindingResult bindingResult,
			Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		String user=principal.getName();
		InvoiceProductDetails isItemExists = itemService.findByProductDescription(item.getProductDescription(), user);
		
		if(isItemExists != null) {
			isItemExists.setProductDescription(item.getProductDescription());
			isItemExists.setProductHnscode(item.getProductHnscode());
			isItemExists.setProductUom(item.getProductUom());
			isItemExists.setProductQuantity(item.getProductQuantity());
			isItemExists.setProductRate(item.getProductRate());
			isItemExists.setProductGstRate(item.getProductGstRate());
			isItemExists.setProductDiscount(item.getProductDiscount());
			itemService.saveItem(isItemExists);
			modelAndView.addObject("message", "Item Updated Successfully");
			modelAndView.addObject("item", item);
		} 
		else {
			item.setProductOwner(user);
			itemService.saveItem(item);
			modelAndView.addObject("message", "Item Added Successfully");
			modelAndView.addObject("item", new InvoiceProductDetails());
		}
		modelAndView.addObject("itemList", itemService.findByProductOwner(user));
		modelAndView.setViewName("addItem");
		return modelAndView;
	}
	
}
