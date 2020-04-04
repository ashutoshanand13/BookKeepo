/**
 * 
 */
package in.winwithweb.gst.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.Reports;
import in.winwithweb.gst.model.ReportsData;
import in.winwithweb.gst.model.Type;
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

	public List<ReportsData> getReports(String name, Reports reports) {
		List<InvoiceDetails> invoiceDetails = invoiceRepository.findByInvoiceOwner(name);

		List<ReportsData> reportsData = new ArrayList<>();

		for (InvoiceDetails invoice : invoiceDetails) {

			if (reports.getType().getType().equalsIgnoreCase(invoice.getType()) && InvoiceUtil
					.checkInvoiceType(invoice.getInvoiceType(), reports.getDocumentType().getDocumentValue())) {

				if (InvoiceUtil.isValidDate(reports.getStartDate(), reports.getEndDate(),
						CommonUtils.convertDateIntoFormat(invoice.getInvoiceDate()))) {
					ReportsData data = new ReportsData();
					data.setDocumenttype(CommonUtils.nullToEmpty(invoice.getType()));
					data.setDocumentNo(CommonUtils.nullToEmpty(invoice.getInvoiceNumber()));
					data.setDocumentDate(CommonUtils.nullToEmpty(invoice.getInvoiceDate()));
					data.setNameOfParty(CommonUtils.nullToEmpty(invoice.getInvoicePartyName()));
					data.setGstin(CommonUtils.nullToEmpty(invoice.getInvoicePartyGstin()));
					data.setTaxableVale(CommonUtils.nullToEmpty(invoice.getInvoiceTaxAmount()));
					data.setCgst(CommonUtils.nullToEmpty(invoice.getInvoiceCgstAmount()));
					data.setSgst(CommonUtils.nullToEmpty(invoice.getInvoiceSgstAmount()));
					data.setIgst(CommonUtils.nullToEmpty(invoice.getInvoiceIgstAmount()));
					data.setInvoiceAmount(CommonUtils.nullToEmpty(invoice.getInvoiceTotalAmountAfterTax()));

					if (reports.getType().equals(Type.Credit_Note) || reports.getType().equals(Type.Debit_Note)) {
						data.setInvoiceNo(CommonUtils.nullToEmpty(invoice.getInvoiceAgainstInvoice()));
						data.setInvoiceDate("");
					}

					if (reports.getType().equals(Type.Sales_Invoice) || reports.getType().equals(Type.Export_Invoice)) {
						data.setDateOfSupply(CommonUtils.nullToEmpty(invoice.getInvoiceDOS()));
						data.setPlaceOfSupply(CommonUtils.nullToEmpty(invoice.getInvoicePOS()));
					}

					reportsData.add(data);
				}
			}
		}
		return reportsData;
	}

}
