/**
 * 
 */
package in.winwithweb.gst.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.Reports;
import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.repository.InvoiceRepository;
import in.winwithweb.gst.util.CommonUtils;
import in.winwithweb.gst.util.InvoiceUtil;

/**
 * @author sachingoyal
 *
 */

@Service("reportService")
public class ReportService {

	private InvoiceRepository invoiceRepository;

	@Autowired
	public ReportService(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;

	}

	public List<InvoiceDetails> getReports(String name, Reports reports) {
		List<InvoiceDetails> invoiceDetails = invoiceRepository.findByInvoiceOwner(name);

		List<InvoiceDetails> reportsData = new ArrayList<>();

		for (InvoiceDetails invoice : invoiceDetails) {

			if (reports.getType().getType().equalsIgnoreCase(invoice.getType()) && InvoiceUtil
					.checkInvoiceType(invoice.getInvoiceType(), reports.getDocumentType().getDocumentValue())) {

				if (InvoiceUtil.isValidDate(reports.getStartDate(), reports.getEndDate(),
						CommonUtils.convertDateIntoFormat(invoice.getInvoiceDate()))) {
					reportsData.add(invoice);
				}
			}
		}
		return reportsData;
	}

}
