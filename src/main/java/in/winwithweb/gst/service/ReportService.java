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
import in.winwithweb.gst.model.InvoiceType;
import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.model.sales.InvoiceOtherDetails;
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

			if (reports.getInvoiceType().getType().equalsIgnoreCase(invoice.getInvoiceType())
					&& InvoiceUtil.checkInvoiceType(invoice.getInvoiceSubType(),
							reports.getInvoiceSubType().getInvoiceSubTypeValue())) {

				if (InvoiceUtil.isValidDate(reports.getStartDate(), reports.getEndDate(),
						CommonUtils.convertDateIntoFormat(invoice.getInvoiceDate()))) {
					ReportsData data = new ReportsData();
					data.setDocumenttype(CommonUtils.nullToEmpty(invoice.getInvoiceSubType()));
					data.setDocumentNo(CommonUtils.nullToEmpty(invoice.getInvoiceNumber()));
					data.setDocumentDate(CommonUtils.nullToEmpty(invoice.getInvoiceDate()));

					data.setNameOfParty(
							CommonUtils.nullToEmpty(invoice.getInvoiceAddressDetails().getInvoiceBillerName()));
					data.setGstin(CommonUtils.nullToEmpty(invoice.getInvoiceAddressDetails().getInvoiceBillerGst()));
					data.setTaxableVale(CommonUtils.nullToEmpty(invoice.getInvoiceTaxAmount()));
					data.setCgst(CommonUtils.nullToEmpty(invoice.getInvoiceCgstAmount()));
					data.setSgst(CommonUtils.nullToEmpty(invoice.getInvoiceSgstAmount()));
					data.setIgst(CommonUtils.nullToEmpty(invoice.getInvoiceIgstAmount()));
					data.setInvoiceAmount(CommonUtils.nullToEmpty(invoice.getInvoiceTotalAmountAfterTax()));

					if (reports.getInvoiceType().equals(InvoiceType.Credit_Note)
							|| reports.getInvoiceType().equals(InvoiceType.Debit_Note)) {

						InvoiceOtherDetails invoiceOtherDetails = invoice.getInvoiceOtherDetails();

						data.setInvoiceNo(CommonUtils.nullToEmpty(invoiceOtherDetails.getLinkedInvoice() == null ? ""
								: invoiceOtherDetails.getLinkedInvoice()));
						data.setInvoiceDate(CommonUtils.nullToEmpty(invoiceOtherDetails.getLinkedInvoice() == null ? ""
								: invoiceOtherDetails.getLinkedInvoiceDate()));
					}

					if (reports.getInvoiceType().equals(InvoiceType.Export_Invoice)
							|| reports.getInvoiceType().equals(InvoiceType.Tax_Invoice)) {
						data.setDateOfSupply(CommonUtils.nullToEmpty(invoice.getInvoiceDOS()));
						data.setPlaceOfSupply(CommonUtils.nullToEmpty(invoice.getInvoicePOS()));
						data.setDocumenttype(reports.getInvoiceType().equals(InvoiceType.Export_Invoice)
								? CommonUtils.nullToEmpty(invoice.getInvoiceType())
								: CommonUtils.nullToEmpty(invoice.getInvoiceSubType()));

					}
					
					if(reports.getInvoiceType().equals(InvoiceType.Purchase_Invoice)
							|| reports.getInvoiceType().equals(InvoiceType.Purchase_Order)) {
						
						data.setDocumentNo(CommonUtils.nullToEmpty(invoice.getInvoiceDocumentNumber()));

						data.setNameOfParty(
								CommonUtils.nullToEmpty(invoice.getInvoiceOtherDetails().getInvoicePartyName()));
						data.setGstin(CommonUtils.nullToEmpty(invoice.getInvoiceOtherDetails().getInvoicePartyGstin()));						
					}

					reportsData.add(data);
				}
			}
		}
		return reportsData;
	}

}
