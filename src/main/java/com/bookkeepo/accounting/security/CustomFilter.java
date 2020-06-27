/**
 * 
 */
package com.bookkeepo.accounting.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

/**
 * @author Yash Singh
 *
 */
public class CustomFilter extends GenericFilterBean {

	List<String> urls = new ArrayList<String>();
	
	public CustomFilter() {
		urls.add("/home/salesinvoice");
		urls.add("/home/exportinvoice");
		urls.add("/home/creditnote");
		urls.add("/home/debitnote");
		urls.add("/home/addpurchaseorder");
		urls.add("/home/addpurchaseinvoice");
		urls.add("/home/billofsupply");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletReponse = (HttpServletResponse) response;

		if (urls.contains(httpServletRequest.getRequestURI())) {
			if (httpServletRequest.getSession().getAttribute("CompanyGSTIN").equals("menu_withoutCompanyGSTIN")) {
				httpServletReponse.sendRedirect("/error");
			}
		}

		chain.doFilter(request, response);

	}
}