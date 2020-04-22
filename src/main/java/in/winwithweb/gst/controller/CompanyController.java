package in.winwithweb.gst.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.service.CompanyDetailsService;
import in.winwithweb.gst.util.CommonUtils;

@Controller
public class CompanyController {

	@Autowired
	CompanyDetailsService companyDetailsService;

	@RequestMapping(value = { "/home/addcompany" }, method = RequestMethod.GET)
	public ModelAndView getAddCompanyPage(HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		ModelAndView modelAndView = new ModelAndView("addCompany");
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			company = new Company(user, CommonUtils.getImgfromResource("/static/images/image-400x400.jpg"));
		} else {
			company.setCompanyStringLogo(CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
		}
		modelAndView.addObject("company", company);
		modelAndView.addObject("logoImage", company.getCompanyStringLogo());
		return modelAndView;
	}

	@RequestMapping(value = "/home/addcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult,
			Principal principal, @RequestParam("companyLogo") MultipartFile companyLogo) {
		ModelAndView modelAndView = new ModelAndView(
				CommonUtils.isPopulated(company.getPageName()) ? "redirect:" + company.getPageName() : "addCompany");
		try {
			if (companyLogo != null && CommonUtils.isPopulated(companyLogo.getOriginalFilename())) {
				company.setCompanyLogo(companyLogo.getBytes());
				company.setCompanyStringLogo(CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			} else {
				company.setCompanyLogo(CommonUtils.getByteArrayfromImage(company.getCompanyStringLogo()));
			}
		} catch (IOException e) {
		}

		companyDetailsService.save(company);

		modelAndView.addObject("logoImage", company.getCompanyStringLogo());
		modelAndView.addObject("message", "Company details updated successfully!");
		modelAndView.addObject("company", company);

		return modelAndView;
	}

}
