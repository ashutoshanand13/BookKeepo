package com.bookkeepo.accounting.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.User;
import com.bookkeepo.accounting.model.UserDetails;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.Constants;
import com.bookkeepo.accounting.util.ImageUtils;

@Controller
public class UserProfileController extends MasterController {

	@RequestMapping(value = { "/home/changePassword" }, method = RequestMethod.GET)
	public ModelAndView getChangePasswordPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("changePassword");
		modelAndView.addObject("userDetails", new UserDetails());
		return modelAndView;
	}

	@RequestMapping(value = "/home/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@Valid UserDetails user, BindingResult bindingResult,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User userExists = userService.findUserByEmail(request.getUserPrincipal().getName());
		modelAndView.setViewName("changePassword");

		modelAndView.addObject("userDetails", user);

		if (userExists == null || !bCryptPasswordEncoder.matches(user.getOldPassword(), userExists.getPassword())) {

			modelAndView.addObject("message", "Current Password does not match");

		} else if (user.getOldPassword().equals(user.getNewPassword())) {
			modelAndView.addObject("message", "Please use the different password");

		} else {
			modelAndView.addObject("message", "Password successfully changed");

			userExists.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
			userService.updateUser(userExists);

		}

		return modelAndView;
	}

	@RequestMapping(value = { "/home/showProfile" }, method = RequestMethod.GET)
	public ModelAndView getAddAccount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		List<Company> companyList = companyDetailsService.fetchAllCompanies(request.getUserPrincipal().getName());
		modelAndView.addObject("companyList", companyList);
		modelAndView.addObject("company", new Company());
		modelAndView.setViewName("showProfile");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/deletecompany/{uniqueKey}" })
	public ModelAndView deleteCompany(@PathVariable("uniqueKey") String uniqueKey, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyDetailsService.findByCompanyUniqueKey(uniqueKey);
		if(company.getCompanyActive()!=1) {
		company.setCompanyDeleted(1);
		company.setCompanyActive(0);
		companyDetailsService.save(company);
		}
		modelAndView.setViewName("redirect:/home/showProfile");

		return modelAndView;
	}

	@RequestMapping(value = { "/home/activatecompany/{uniqueKey}" })
	public ModelAndView activateCompany(@PathVariable("uniqueKey") String uniqueKey, HttpServletRequest request,
			HttpServletResponse response, Principal principal) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		List<Company> companyList = companyDetailsService.fetchAllCompanies(principal.getName());
		for (Company com : companyList) {
			if (uniqueKey.equals(com.getCompanyUniqueKey())) {
				com.setCompanyActive(1);
				String menuToDisplay = CommonUtils.isPopulated(com.getCompanyGstin()) 
						? "menu_withCompanyGSTIN":"menu_withoutCompanyGSTIN";
				CommonUtils.setSessionAttributes(session, menuToDisplay, com);
			} else {
				com.setCompanyActive(0);
			}
		}

		companyDetailsService.saveAll(companyList);
		modelAndView.setViewName("redirect:/home/showProfile");

		return modelAndView;
	}

	@RequestMapping(value = { "/home/addnewcompany" }, method = RequestMethod.POST)
	public ModelAndView addNewCompany(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult,
			Principal principal, @RequestParam("companyLogo") MultipartFile companyLogo, HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		List<Company> companyRecord = companyDetailsService.fetchAllCompanies(principal.getName());
		Accounts account = new Accounts();
		account.setAccountName(Constants.DEFAULT_ACCOUNT_ON_COMPANY_CREATION);
		if (companyRecord == null || companyRecord.size() == 0) {
			company.setCompanyActive(1);
		}

		try {
			if (companyLogo != null && CommonUtils.isPopulated(companyLogo.getOriginalFilename())) {
				company.setCompanyLogo(addresizedlogo(company, companyLogo));
				company.setCompanyStringLogo(CommonUtils.getImgfromByteArray(company.getCompanyLogo()));
			}
		} catch (IOException e) {
			company.setCompanyLogo(null);
		}

		company.setCompanyUniqueKey(CommonUtils.getUniqueID());
		company.setUserName(principal.getName());
		account.setAccountOwner(principal.getName());
		account.setAccountType(Constants.DEFAULT_ACCOUNT_ON_COMPANY_CREATION);
		Company companyforAccount= companyDetailsService.save(company);
		account.setAccountCompanyDetails(companyforAccount);
		accountService.saveAccount(account);
		
		modelAndView.addObject("message", "New Company Added Successfully. ");
		modelAndView.setViewName("redirect:/home/showProfile");
		if(company.getCompanyActive()==1) {
		String menuToDisplay = CommonUtils.isPopulated(company.getCompanyGstin()) 
				? "menu_withCompanyGSTIN":"menu_withoutCompanyGSTIN";
		CommonUtils.setSessionAttributes(session, menuToDisplay, company);
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
