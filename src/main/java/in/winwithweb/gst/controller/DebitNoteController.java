/**
 * 
 */
package in.winwithweb.gst.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.util.CommonUtils;

/**
 * @author Yash Singh
 *
 */
@Controller
public class DebitNoteController {
	
	@Autowired
	CompanyDetailsService companyDetailsService;
	
	@RequestMapping(value = "/home/debitnote", method = RequestMethod.GET)
	public ModelAndView setupSales(HttpServletRequest request) {
		String user=request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		if(company==null) {
			modelAndView.addObject("message", "Please update company details before creating an Invoice");
			modelAndView.addObject("company",new Company("debitNote"));
			modelAndView.addObject("logoImage",CommonUtils.getImgfromResource("/static/images/image-400x400.jpg"));
			modelAndView.setViewName("addCompany");
		}
		else {
		modelAndView.addObject("company",company);
		byte[] encodeBase64 = Base64.getEncoder().encode(company.getCompanyLogo());
		String base64Encoded = null;
		try {
			base64Encoded = new String(encodeBase64);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("logoImage",base64Encoded);
		modelAndView.setViewName("debitNote");
		}
		return modelAndView;
	}
}
