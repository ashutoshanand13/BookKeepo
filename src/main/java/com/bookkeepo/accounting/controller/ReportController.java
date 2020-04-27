/**
 * 
 */
package com.bookkeepo.accounting.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.model.Reports;
import com.bookkeepo.accounting.model.ReportsData;
import com.bookkeepo.accounting.service.InvoiceService;
import com.bookkeepo.accounting.service.ReportService;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
 * @author sachingoyal
 *
 */

@Controller
public class ReportController {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	ReportService reportService;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

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
		List<ReportsData> reportData = reportService.getReports(principal.getName(), reports);
		modelAndView.addObject("reportsColumns", reports.getInvoiceType().getColums());
		modelAndView.addObject("reports", reportData);
		modelAndView.setViewName("reportsData");
		modelAndView.addObject("pagetitle", "Download " + reports.getInvoiceType().getType() + " ("
				+ reports.getInvoiceSubType().getInvoiceSubTypeValue() + ")");

		return modelAndView;
	}

	@RequestMapping(value = { "/home/showInvoice" }, method = RequestMethod.GET)
	public ModelAndView showInvoice(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("invoiceList", invoiceService.findByInvoiceOwner(request.getUserPrincipal().getName()));
		modelAndView.setViewName("showInvoice");
		return modelAndView;
	}

	@RequestMapping(value = { "/home/showInvoice/{id}" })
	public void view(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		InvoiceDetails invoice = invoiceService.findById(id);

		ByteArrayOutputStream invoiceData = InvoiceUtil.createPDF(invoice);

		response.setContentType("application/pdf");

		Date date = new Date();
		String time = sdf.format(new Timestamp(date.getTime()));

		response.addHeader("Content-Disposition", "attachment; filename=" + invoice.getInvoiceType() + "_"
				+ invoice.getInvoiceNumber() + "_" + time + ".pdf");
		response.setContentLength(invoiceData.size());

		OutputStream out = null;
		out = response.getOutputStream();
		invoiceData.writeTo(out);
		out.close();
		out.flush();

	}

}
