/**
 * 
 */
package in.winwithweb.gst.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.model.json.InvoicePageData;
import in.winwithweb.gst.service.AccountService;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.util.CommonUtils;

/**
 * @author Yash Singh
 *
 */
@Controller
public class ExportInvoiceController {
	
	@Autowired
	CompanyDetailsService companyDetailsService;
	
	@Autowired
	Gson gson;
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/home/exportinvoice", method = RequestMethod.GET)
	public ModelAndView setupSales(HttpServletRequest request) {
		String user=request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		List<String> account = accountService.fetchAccountName();
		if(company==null) {
			modelAndView.addObject("message", "Please update company details before creating an Invoice");
			modelAndView.addObject("company",new Company("exportInvoice"));
			modelAndView.addObject("logoImage",CommonUtils.getImgfromResource("/static/images/image-400x400.jpg"));
			modelAndView.setViewName("addCompany");
		}
		else {
			modelAndView.addObject("accountList", account);
			modelAndView.addObject("company",company);
			byte[] encodeBase64 = Base64.getEncoder().encode(company.getCompanyLogo());
			String base64Encoded = null;
			try {
				base64Encoded = new String(encodeBase64);
			} catch (Exception e) {
				e.printStackTrace();
			}
			modelAndView.addObject("logoImage",base64Encoded);
			modelAndView.setViewName("exportInvoice");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/home/exportinvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public void setupExportInvoiceData(@RequestBody String exportInvoiceJson, Principal principal,
			HttpServletResponse response) throws IOException {
		InvoicePageData exportInvoiceData = null;
		try {
			exportInvoiceData = gson.fromJson(exportInvoiceJson, InvoicePageData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
