/**
 * 
 */
package com.bookkeepo.accounting.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sachingoyal
 *
 */

@Controller
public class ExceptionHandling implements ErrorController {

	@RequestMapping("/error")
	public String getErrorPath(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "404";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "error";
			}
		}
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
