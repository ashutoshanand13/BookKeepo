/**
 * 
 */
package in.winwithweb.gst.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import in.winwithweb.gst.util.CommonUtils;

/**
 * @author sachingoyal
 *
 */

@Controller
public class ExceptionHandling implements ErrorController {

	@Value("${email.from}")
	private String emailFrom;

	@Value("${email.to}")
	private String emailTo;

	@RequestMapping("/error")
	public String getErrorPath(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				CommonUtils.sendEmail(emailTo, emailFrom, "Exception Occured", "Page not found error occured");
				return "404";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				CommonUtils.sendEmail(emailTo, emailFrom, "Exception Occured", "Internal Server error occured");
				return "error";
			}
		}
		CommonUtils.sendEmail(emailTo, emailFrom, "Exception Occured", "Internal Server error occured");
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
