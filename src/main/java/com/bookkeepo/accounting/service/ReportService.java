/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.entity.InvoiceOtherDetails;
import com.bookkeepo.accounting.model.InvoiceSubType;
import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.model.Reports;
import com.bookkeepo.accounting.model.ReportsData;
import com.bookkeepo.accounting.repository.InvoiceRepository;
import com.bookkeepo.accounting.util.CommonUtils;
import com.bookkeepo.accounting.util.InvoiceUtil;

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
		List<InvoiceDetails> invoiceDetails = getInvoiceList(name, reports);

		List<ReportsData> reportsData = new ArrayList<>();

		for (InvoiceDetails invoice : invoiceDetails) {

			if (InvoiceUtil.isValidDate(reports.getStartDate(), reports.getEndDate(),
					CommonUtils.convertDateIntoFormat(invoice.getInvoiceDate()))) {
				ReportsData data = new ReportsData();
				data.setDocumenttype(CommonUtils.nullToEmpty(invoice.getInvoiceSubType()));
				data.setDocumentNo(CommonUtils.nullToEmpty(invoice.getInvoiceNumber()));
				data.setDocumentDate(CommonUtils.nullToEmpty(invoice.getInvoiceDate()));

				data.setNameOfParty(CommonUtils.nullToEmpty(invoice.getInvoiceAddressDetails().getInvoiceBillerName()));
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

				if (reports.getInvoiceType().equals(InvoiceType.Purchase_Invoice)
						|| reports.getInvoiceType().equals(InvoiceType.Purchase_Order)) {

					data.setNameOfParty(
							CommonUtils.nullToEmpty(invoice.getInvoiceAddressDetails().getInvoicePartyName()));
					data.setGstin(CommonUtils.nullToEmpty(invoice.getInvoiceAddressDetails().getInvoicePartyGst()));
				}

				reportsData.add(data);
			}

		}
		return reportsData;
	}

	private List<InvoiceDetails> getInvoiceList(String name, Reports reports) {
		return InvoiceSubType.BOTH.equals(reports.getInvoiceSubType())
				? invoiceRepository.findByInvoiceOwnerAndInvoiceType(name, reports.getInvoiceType().getType())
				: invoiceRepository.findByInvoiceOwnerAndInvoiceTypeAndInvoiceSubType(name,
						reports.getInvoiceType().getType(), reports.getInvoiceSubType().getInvoiceSubType());

	}

}
