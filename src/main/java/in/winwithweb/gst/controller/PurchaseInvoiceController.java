package in.winwithweb.gst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @author Ashutosh
 *
 */

public class PurchaseInvoiceController {

	@RequestMapping(value = { "/home/addPurchaseInvoice" }, method = RequestMethod.GET)
	public ModelAndView getItemPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		//String user=request.getUserPrincipal().getName();
		//modelAndView.addObject("itemList", itemService.findByProductOwner(user));
		//modelAndView.addObject("item", new InvoiceProductDetails());
		
		modelAndView.setViewName("addPurchaseInvoice");
		return modelAndView;
	}
	
}
