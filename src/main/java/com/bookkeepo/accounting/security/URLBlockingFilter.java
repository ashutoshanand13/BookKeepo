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
 * @author Akarsh
 *
 */
public class URLBlockingFilter extends GenericFilterBean {

	List<String> urls = new ArrayList<String>();
	
	public URLBlockingFilter() {
		urls.add("/home/ledger");
		urls.add("/home/invoiceledger");
		urls.add("/home/updatereceipt");
		urls.add("/home/updatepayment");
		urls.add("/home/recordexpense");
		urls.add("/home/recordincome");
		urls.add("/home/recordcontracash");
		urls.add("/home/trialBalanceReport");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletReponse = (HttpServletResponse) response;

		if (urls.contains(httpServletRequest.getRequestURI())) {
				httpServletReponse.sendRedirect("/error");
		}

		chain.doFilter(request, response);

	}
}