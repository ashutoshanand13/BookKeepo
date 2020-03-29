/**
 * 
 */
package in.winwithweb.gst.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.winwithweb.gst.model.Reports;
import in.winwithweb.gst.service.InvoiceService;
import in.winwithweb.gst.util.CommonUtils;

/**
 * @author sachingoyal
 *
 */

@Controller
public class ReportController {

	@Autowired
	InvoiceService invoiceService;

	@RequestMapping(value = { "/home/reports" }, method = RequestMethod.GET)
	public ModelAndView showReport(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("reports", new Reports());
		modelAndView.setViewName("reports");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/reports" }, method = RequestMethod.POST)
	public ModelAndView generateReport(@Valid Reports reports, HttpServletRequest request, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();

		if (CommonUtils.isPopulated(reports.getStartDate()) && CommonUtils.isPopulated(reports.getEndDate())
				&& !CommonUtils.isValidEndDate(reports.getStartDate(), reports.getEndDate())) {
			modelAndView.addObject("reports", reports);
			modelAndView.setViewName("reports");
			modelAndView.addObject("message", "End date should not be less the Start date");

			return modelAndView;

		}

		System.out.println(reports.getEndDate());

		System.out.println(reports.getStartDate());

		System.out.println(reports.getDocumentType().getDocumentType());

		System.out.println(reports.getType().getType());

		return modelAndView;
	}

}
