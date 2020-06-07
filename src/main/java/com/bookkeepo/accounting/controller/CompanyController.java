package com.bookkeepo.accounting.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.ImageUtils;

@Controller
public class CompanyController extends MasterController {

	@RequestMapping(value = { "/home/addcompany" }, method = RequestMethod.GET)
	public ModelAndView getAddCompanyPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:/home/updatecompany");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/updatecompany" }, method = RequestMethod.GET)
	public ModelAndView getAddCompanyPageFromInvoice(HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			modelAndView.setViewName("redirect:/home/showProfile");
		} else {
			modelAndView.setViewName("addCompany");
			company.setCompanyStringLogo(CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			modelAndView.addObject("company", company);
			modelAndView.addObject("logoImage", company.getCompanyStringLogo());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/home/addcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult,
			Principal principal, @RequestParam("companyLogo") MultipartFile companyLogo) {
		ModelAndView modelAndView = new ModelAndView(
				CommonUtils.isPopulated(company.getPageName()) ? "redirect:" + company.getPageName() : "addCompany");
		try {
			if (companyLogo != null && !companyLogo.getOriginalFilename().isEmpty()) {
				company.setCompanyLogo(addresizedlogo(company, companyLogo));
				company.setCompanyStringLogo(CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			}
		} catch (IOException e) {
			company.setCompanyLogo(null);
		}
		company.setCompanyUniqueKey(CommonUtils.getUniqueID());
		company.setCompanyActive(1);
		companyDetailsService.save(company);
		if (!CommonUtils.isPopulated(company.getPageName())) {
			modelAndView.addObject("logoImage", company.getCompanyStringLogo());
			modelAndView.addObject("message", "Company details updated successfully!");
			modelAndView.addObject("company", company);
		}
		return modelAndView;
	}

	/**
	 * @param company
	 * @param companyLogo
	 * @throws IOException
	 */
	private byte[] addresizedlogo(Company company, MultipartFile companyLogo) throws IOException {

		return ImageUtils.convertToArray(ImageUtils.convertToImage(companyLogo), companyLogo.getContentType());
	}

}
