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
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByUserName(user);
		if (company != null) {
			modelAndView.addObject("company", company);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
		} else {
			modelAndView.addObject("company", new Company());
			modelAndView.addObject("logoImage", CommonUtils.getImgfromResource("/static/images/image-400x400.jpg"));
		}

		modelAndView.setViewName("addCompany");
		return modelAndView;
	}

	@RequestMapping(value = "/home/addcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult,
			Principal principal, @RequestParam("companyLogo") MultipartFile companyLogo) {
		ModelAndView modelAndView = new ModelAndView();
		byte[] CompanyUploadedFile = null;
		company.setUserName(principal.getName());
		modelAndView.setViewName("addCompany");

		Company isDataExists = companyDetailsService.findByUserName(principal.getName());

		if (isDataExists == null) {
			try {
				CompanyUploadedFile = companyLogo.getBytes();
				company.setCompanyLogo(companyLogo.getBytes());
				modelAndView.addObject("message", "Company details added successfully!");
				modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(CompanyUploadedFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			companyDetailsService.save(company);
			if (company.getPageName() != null && !company.getPageName().trim().equals("")) {
				modelAndView.setViewName(company.getPageName());
			}
		} else {
			isDataExists.setCompanyAddress(company.getCompanyAddress());
			isDataExists.setCompanyEmail(company.getCompanyEmail());
			isDataExists.setCompanyGstin(company.getCompanyGstin());
			isDataExists.setCompanyBankAC(company.getCompanyBankAC());
			isDataExists.setCompanyBankIFSC(company.getCompanyBankIFSC());
			isDataExists.setCompanyBankTNC(company.getCompanyBankTNC());
			try {
				CompanyUploadedFile = companyLogo.getBytes();
				if (CompanyUploadedFile.length != 0) {
					isDataExists.setCompanyLogo(companyLogo.getBytes());
				} else {
					CompanyUploadedFile = isDataExists.getCompanyLogo();
					isDataExists.setCompanyLogo(isDataExists.getCompanyLogo());// Making changes if company page is
																				// submitted w/o changing anything
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			isDataExists.setCompanyName(company.getCompanyName());
			isDataExists.setCompanyState(company.getCompanyState());
			isDataExists.setCompanyTelephone(company.getCompanyTelephone());
			companyDetailsService.save(isDataExists);
			modelAndView.addObject("logoImage", CommonUtils.getImgfromByteArray(CompanyUploadedFile));
			modelAndView.addObject("message", "Company details updated successfully!");
		}

		modelAndView.addObject("company", companyDetailsService.findByUserName(principal.getName()));
		return modelAndView;
	}

}
