/**
 * 
 */
package com.bookkeepo.accounting.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.service.CompanyDetailsService;

/**
 * @author Ashutosh Anand
 *
 */
@Component
public class MySuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	protected CompanyDetailsService companyDetailsService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = auth.getName();
		Company company = companyDetailsService.findByUserName(user);
		if (company == null) {
			session.setAttribute("CompanyGSTIN", "menu_withoutCompanyGSTIN");
		} else {
			if (StringUtils.isEmpty(company.getCompanyGstin())) {
				session.setAttribute("CompanyGSTIN", "menu_withoutCompanyGSTIN");
			} else {
				session.setAttribute("CompanyGSTIN", "menu_withCompanyGSTIN");
			}
		}

	}
	
}
