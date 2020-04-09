/**
 * 
 */
package in.winwithweb.gst.util;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import in.winwithweb.gst.model.Company;
import in.winwithweb.gst.model.InvoiceSubType;
import in.winwithweb.gst.model.InvoiceType;
import in.winwithweb.gst.model.json.InvoicePageData;
import in.winwithweb.gst.model.json.ItemList;
import in.winwithweb.gst.model.sales.InvoiceAddressDetails;
import in.winwithweb.gst.model.sales.InvoiceBankDetails;
import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.model.sales.InvoiceOtherDetails;
import in.winwithweb.gst.model.sales.InvoiceProductDetails;

/**
 * @author Yash Singh
 *
 */
public class InvoiceUtil {

	private static float[] columnItemTableWidths = null;

	public static void updateInvoice(InvoiceDetails invoice, InvoicePageData salesInvoiceData, Company companyDetails) {

		List<InvoiceProductDetails> productList = new ArrayList<InvoiceProductDetails>();
		InvoiceBankDetails invoiceBankDetails = new InvoiceBankDetails();
		InvoiceAddressDetails invoiceAddressDetails = new InvoiceAddressDetails();

		if (invoice.getInvoiceType().equals(InvoiceType.Tax_Invoice.getType())
				|| invoice.getInvoiceType().equals(InvoiceType.Export_Invoice.getType())) {
			setTaxandExportInvoiceData(invoice, salesInvoiceData);
		} else if (invoice.getInvoiceType().equals(InvoiceType.Credit_Note.getType())
				|| invoice.getInvoiceType().equals(InvoiceType.Debit_Note.getType())) {
			setCreditandDebitNoteData(invoice, salesInvoiceData);
		} else if (invoice.getInvoiceType().equals(InvoiceType.Purchase_Invoice.getType())
				|| invoice.getInvoiceType().equals(InvoiceType.Purchase_Order.getType())) {
			setPurchasOrderandPurchasesInvoiceData(invoice, salesInvoiceData);
		}
		setCommonInvoiceData(invoice, salesInvoiceData, invoiceBankDetails);

		if (!(invoice.getInvoiceType().equals(InvoiceType.Purchase_Invoice.getType())
				|| invoice.getInvoiceType().equals(InvoiceType.Purchase_Order.getType()))) {
			setInvoiceAddressData(salesInvoiceData, invoiceAddressDetails);
			invoice.setInvoiceAddressDetails(invoiceAddressDetails);
		}

		invoice.setInvoiceCompanyDetails(companyDetails);
		if (salesInvoiceData.getGstinBill() != null) {
			if (companyDetails.getCompanyGstin().substring(0, 2)
					.equals(salesInvoiceData.getGstinBill().substring(0, 2))) {
				invoice.setInvoiceSubType(InvoiceSubType.INTRASTATE.getInvoiceSubType());
			} else {
				invoice.setInvoiceSubType(InvoiceSubType.INTERSTATE.getInvoiceSubType());
			}
		} else {
			if (companyDetails.getCompanyGstin().substring(0, 2)
					.equals(salesInvoiceData.getPartyGstin().substring(0, 2))) {
				invoice.setInvoiceSubType(InvoiceSubType.INTRASTATE.getInvoiceSubType());
			} else {
				invoice.setInvoiceSubType(InvoiceSubType.INTERSTATE.getInvoiceSubType());
			}
		}

		for (ItemList item : salesInvoiceData.getItemList()) {
			InvoiceProductDetails invoiceProductDetails = new InvoiceProductDetails();
			setinvoiceProductData(item, invoiceProductDetails);
			productList.add(invoiceProductDetails);
		}
		invoice.setInvoiceProductDetails(productList);
		
		invoice.setInvoiceUniqueKey(CommonUtils.getUniqueID());
	}

	private static void setInvoiceAddressData(InvoicePageData salesInvoiceData,
			InvoiceAddressDetails invoiceAddressDetails) {
		invoiceAddressDetails.setInvoiceBillerName(salesInvoiceData.getNameBill());
		invoiceAddressDetails.setInvoiceBillerAddressName(salesInvoiceData.getAddressBill());
		invoiceAddressDetails.setInvoiceBillerGst(salesInvoiceData.getGstinBill());
		invoiceAddressDetails.setInvoiceBillerState(salesInvoiceData.getStateBill());
		invoiceAddressDetails.setInvoicePartyName(salesInvoiceData.getNameShip());
		invoiceAddressDetails.setInvoicePartyAddressName(salesInvoiceData.getAddressShip());
		invoiceAddressDetails.setInvoicePartyGst(salesInvoiceData.getGstinShip());
		invoiceAddressDetails.setInvoicePartyState(salesInvoiceData.getStateShip());
	}

	private static void setinvoiceProductData(ItemList item, InvoiceProductDetails invoiceProductDetails) {
		invoiceProductDetails.setProductDescription(item.getProductDesc());
		invoiceProductDetails.setProductHnscode(item.getHsnCode());
		invoiceProductDetails.setProductUom(item.getUom());
		invoiceProductDetails.setProductQuantity(item.getQty());
		invoiceProductDetails.setProductRate(item.getRate());
		invoiceProductDetails.setProductAmount(item.getAmount());
		invoiceProductDetails.setProductDiscount(item.getDiscount());
		invoiceProductDetails.setProductGstRate(item.getGstRate());
		invoiceProductDetails.setProductTaxValue(item.getTaxableValue());
		invoiceProductDetails.setProductCgst(item.getCgst());
		invoiceProductDetails.setProductSgst(item.getSgst());
		invoiceProductDetails.setProductIgst(item.getIgst());
		invoiceProductDetails.setProductTotalAmount(item.getTotalAmount());
	}

	private static void setCommonInvoiceData(InvoiceDetails invoice, InvoicePageData salesInvoiceData,
			InvoiceBankDetails invoiceBankDetails) {
		invoice.setInvoiceTotalAmountBeforeTax(salesInvoiceData.getTotalAmountBeforeTax());
		invoice.setInvoiceTotalAmountAfterTax(salesInvoiceData.getTotalAmountAfterTax());
		invoice.setInvoiceTaxAmount(salesInvoiceData.getTotalTaxAmount());
		invoice.setInvoiceIgstAmount(salesInvoiceData.getTotalAddIGst());
		invoice.setInvoiceSgstAmount(salesInvoiceData.getTotalAddSGst());
		invoice.setInvoiceCgstAmount(salesInvoiceData.getTotalAddCGst());
		invoice.setInvoiceTotalAmountBeforeTax(salesInvoiceData.getTotalAmountBeforeTax());
		invoice.setInvoiceTotalAmountAfterTax(salesInvoiceData.getTotalAmountAfterTax());
		invoice.setInvoiceTaxAmount(salesInvoiceData.getTotalAddIGst());

		invoiceBankDetails.setInvoiceBankAccount(salesInvoiceData.getBankAccountNumber());
		invoiceBankDetails.setInvoiceIfsCode(salesInvoiceData.getBankifsc());
		invoiceBankDetails.setInvoiceBankCondition(salesInvoiceData.getTermsConditions());
		invoice.setInvoiceBankDetails(invoiceBankDetails);
	}

	private static void setPurchasOrderandPurchasesInvoiceData(InvoiceDetails invoice,
			InvoicePageData salesInvoiceData) {

		InvoiceOtherDetails invoiceOtherDetails = invoice.getInvoiceOtherDetails();

		invoiceOtherDetails.setInvoicePartyName(salesInvoiceData.getPartyName());
		invoiceOtherDetails.setInvoicePartyAddress(salesInvoiceData.getPartyAddress());
		invoiceOtherDetails.setInvoicePartyState(salesInvoiceData.getPartyState());
		invoiceOtherDetails.setInvoicePartyGstin(salesInvoiceData.getPartyGstin());
		invoiceOtherDetails.setInvoicePartyState(salesInvoiceData.getPartyState());
		invoiceOtherDetails.setInvoicePartyDate(reverseDate(salesInvoiceData.getPartyDate()));
		
		invoice.setInvoicePoDate(reverseDate(salesInvoiceData.getPoDate()));
		invoice.setInvoicePoNumber(salesInvoiceData.getPoNo());
		invoice.setInvoiceTransportMode(salesInvoiceData.getTransportMode());
		invoice.setInvoiceVehicleNumber(salesInvoiceData.getVehicleNo());
		invoice.setInvoiceReverseCharge(salesInvoiceData.getReverseCharge());
		if (salesInvoiceData.getInvoiceNo() != null && !salesInvoiceData.getInvoiceNo().isEmpty()) {
			invoice.setInvoiceNumber(salesInvoiceData.getInvoiceNo());
		}
	}

	private static void setCreditandDebitNoteData(InvoiceDetails invoice, InvoicePageData salesInvoiceData) {
		invoice.setInvoiceDate(reverseDate(salesInvoiceData.getInvoiceDate()));
		invoice.setInvoiceState(salesInvoiceData.getState());
		invoice.setInvoiceReverseCharge(salesInvoiceData.getReverseCharge());
		invoice.setInvoiceNumber(salesInvoiceData.getDocumentNumber());
		invoice.setInvoiceIssueDate(reverseDate(salesInvoiceData.getIssueDate()));
	}

	private static void setTaxandExportInvoiceData(InvoiceDetails invoice, InvoicePageData salesInvoiceData) {
		invoice.setInvoiceNumber(salesInvoiceData.getInvoiceNo());
		invoice.setInvoiceDate(reverseDate(salesInvoiceData.getInvoiceDate()));
		invoice.setInvoiceDOS(reverseDate(salesInvoiceData.getDateOfSupply()));
		invoice.setInvoicePOS(salesInvoiceData.getPlaceOfSupply());
		invoice.setInvoicePoDate(reverseDate(salesInvoiceData.getPoDate()));
		invoice.setInvoiceState(salesInvoiceData.getState());
		invoice.setInvoicePoNumber(salesInvoiceData.getPoNo());
		invoice.setInvoiceTransportMode(salesInvoiceData.getTransportMode());
		invoice.setInvoiceVehicleNumber(salesInvoiceData.getVehicleNo());
		invoice.setInvoiceReverseCharge(salesInvoiceData.getReverseCharge());
	}

	public static ByteArrayOutputStream createPDF(InvoiceDetails invoice) {

		ByteArrayOutputStream output = new ByteArrayOutputStream();

		Document doc = new Document();
		PdfWriter docWriter = null;
		boolean isIntraState = false;

		DecimalFormat df = new DecimalFormat("0.00");

		try {

			// special font sizes
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0));
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 8);

			docWriter = PdfWriter.getInstance(doc, output);

			// document header attributes
			doc.addAuthor(invoice.getInvoiceOwner());
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("BookKeepo.com");
			doc.addTitle("Invoice");
			doc.setPageSize(PageSize.A4);

			// open document
			doc.open();

			// specify column widths
			float[] columnWidthHeader = { 3f, 4f, 3f };
			float[] columnWidths = { 5f, 2.5f, 2.5f };
			if (InvoiceSubType.INTRASTATE.getInvoiceSubType().equals(invoice.getInvoiceSubType())) {
				isIntraState = true;
			}
			if (isIntraState) {
				columnItemTableWidths = new float[] { 0.7f, 2.2f, 1f, 1f, 1f, 1f, 1.5f, 1.5f, 1f, 1.6f, 1.1f, 1.1f,
						1.3f };
			} else {
				columnItemTableWidths = new float[] { 0.7f, 2.2f, 1f, 1f, 1f, 1f, 1.5f, 1.5f, 1f, 1.6f, 1.f, 1.3f };
			}
			// create PDF table with the given widths

			Paragraph header = new Paragraph();
			header.add(invoice.getInvoiceType() + " - " + invoice.getInvoiceType() + "\n\n");
			header.setAlignment(Element.ALIGN_CENTER);

			PdfPTable tableHeader = new PdfPTable(columnWidthHeader);
			PdfPTable table = new PdfPTable(columnWidths);
			PdfPTable itemTable = new PdfPTable(columnItemTableWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(90f);
			itemTable.setWidthPercentage(90f);
			tableHeader.setWidthPercentage(90f);

			addHeader(invoice, bfBold12, tableHeader);

			insertCell(table, "", Element.ALIGN_LEFT, 3, bfBold12, 1, "#FFFFFF", 1f, 1f, 0f);

			insertCell(table, invoice.getInvoiceType(), Element.ALIGN_CENTER, 3, bfBold12, 1, "#BFD6E9", 1f, 1f, 30f);

			if (invoice.getInvoiceType().equals(InvoiceType.Tax_Invoice.getType())
					|| invoice.getInvoiceType().equals(InvoiceType.Export_Invoice.getType())) {
				insertCell(table, "Invoice No: ", invoice.getInvoiceNumber(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
						"#FFFFFF", 1f, 0.5f);
				insertCell(table, "PO No: ", invoice.getInvoicePoNumber(), Element.ALIGN_LEFT, 2, bfBold12, bf12, 1,
						"#FFFFFF", 0.5f, 1f);
				insertCell(table, "Invoice Date: ", invoice.getInvoiceDate(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
						"#FFFFFF", 1f, 0.5f);
				insertCell(table, "PO Date: ", invoice.getInvoicePoDate(), Element.ALIGN_LEFT, 2, bfBold12, bf12, 1,
						"#FFFFFF", 0.5f, 1f);
				insertCell(table, "Date Of Supply: ", invoice.getInvoiceDOS(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
						"#FFFFFF", 1f, 0.5f);
				insertCell(table, "Transport Mode: ", invoice.getInvoiceTransportMode(), Element.ALIGN_LEFT, 2,
						bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "Place Of Supply: ", invoice.getInvoicePOS(), Element.ALIGN_LEFT, 1, bfBold12, bf12,
						1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "Vehicle No: ", invoice.getInvoiceVehicleNumber(), Element.ALIGN_LEFT, 2, bfBold12,
						bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "State: ", invoice.getInvoiceState(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
						"#FFFFFF", 1f, 0.5f);
				insertCell(table, "Reverse Charge (Y/N): ", invoice.getInvoiceReverseCharge(), Element.ALIGN_LEFT, 2,
						bfBold12, bf12, 1, "#EEFF74", 0.5f, 1f);
			} else if (invoice.getInvoiceType().equals(InvoiceType.Credit_Note.getType())
					|| invoice.getInvoiceType().equals(InvoiceType.Debit_Note.getType())) {
				insertCell(table, "Document No: ", invoice.getInvoiceNumber(), Element.ALIGN_LEFT, 1, bfBold12,
						bf12, 1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "Against Invoice: ",
						invoice.getInvoiceOtherDetails().getLinkedInvoice(), Element.ALIGN_LEFT, 2,
						bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "Issue Date: ", invoice.getInvoiceIssueDate(), Element.ALIGN_LEFT, 1, bfBold12, bf12,
						1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "Invoice Date: ", invoice.getInvoiceDate(), Element.ALIGN_LEFT, 2, bfBold12, bf12, 1,
						"#FFFFFF", 0.5f, 1f);
				insertCell(table, "State: ", invoice.getInvoiceState(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
						"#FFFFFF", 1f, 0.5f);
				insertCell(table, "Reverse Charge (Y/N): ", invoice.getInvoiceReverseCharge(), Element.ALIGN_LEFT, 2,
						bfBold12, bf12, 1, "#EEFF74", 0.5f, 1f);
			} else if (invoice.getInvoiceType().equals(InvoiceType.Purchase_Invoice.getType())
					|| invoice.getInvoiceType().equals(InvoiceType.Purchase_Order.getType())) {
				insertCell(table, "Party Details", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 1f, 0.5f, 0f);

				InvoiceOtherDetails invoiceOtherDetails = invoice.getInvoiceOtherDetails();

				if (invoice.getInvoiceType().equals(InvoiceType.Purchase_Order.getType())) {
					insertCell(table, "PO Details", Element.ALIGN_CENTER, 2, bfBold12, 1, "#BFD6E9", 0.5f, 1f, 0f);
					insertCell(table, "Name: ", invoiceOtherDetails.getInvoicePartyName(), Element.ALIGN_LEFT, 1,
							bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
					insertCell(table, "PO No: ", invoice.getInvoicePoNumber(), Element.ALIGN_LEFT, 2, bfBold12, bf12, 1,
							"#FFFFFF", 0.5f, 1f);
					insertCell(table, "Address: ", invoiceOtherDetails.getInvoicePartyAddress(), Element.ALIGN_LEFT, 1,
							bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
					insertCell(table, "PO Date: ", invoice.getInvoicePoDate(), Element.ALIGN_LEFT, 2, bfBold12, bf12, 1,
							"#FFFFFF", 0.5f, 1f);
				} else {
					insertCell(table, "Invoice Details", Element.ALIGN_CENTER, 2, bfBold12, 1, "#BFD6E9", 0.5f, 1f, 0f);
					insertCell(table, "Name: ", invoiceOtherDetails.getInvoicePartyName(), Element.ALIGN_LEFT, 1,
							bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
					insertCell(table, "Invoice No: ", invoice.getInvoiceNumber(), Element.ALIGN_LEFT, 2, bfBold12, bf12,
							1, "#FFFFFF", 1f, 0.5f);
					insertCell(table, "Address: ", invoiceOtherDetails.getInvoicePartyAddress(), Element.ALIGN_LEFT, 1,
							bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
					insertCell(table, "PO No: ", invoice.getInvoicePoNumber(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
							"#FFFFFF", 0.5f, 1f);
					insertCell(table, "PO Date: ", invoice.getInvoicePoDate(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
							"#FFFFFF", 0.5f, 1f);
				}
				insertCell(table, "Date: ", invoiceOtherDetails.getInvoicePartyDate(), Element.ALIGN_LEFT, 1, bfBold12,
						bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "Transport Mode: ", invoice.getInvoiceTransportMode(), Element.ALIGN_LEFT, 2,
						bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "GSTIN: ", invoiceOtherDetails.getInvoicePartyGstin(), Element.ALIGN_LEFT, 1,
						bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "Vehicle No: ", invoice.getInvoiceVehicleNumber(), Element.ALIGN_LEFT, 2, bfBold12,
						bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "State: ", invoiceOtherDetails.getInvoicePartyState(), Element.ALIGN_LEFT, 1,
						bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "Reverse Charge (Y/N): ", invoice.getInvoiceReverseCharge(), Element.ALIGN_LEFT, 2,
						bfBold12, bf12, 1, "#EEFF74", 0.5f, 1f);

			}

			// merge the cells to create a footer for that section
			insertCell(table, "", Element.ALIGN_LEFT, 3, bfBold12, 1, "#FFFFFF", 1f, 1f, 0f);

			if (!(invoice.getInvoiceType().equals(InvoiceType.Purchase_Invoice.getType())
					|| invoice.getInvoiceType().equals(InvoiceType.Purchase_Order.getType()))) {
				insertCell(table, "Bill to Party", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 1f, 0.5f, 0f);
				insertCell(table, "Ship to Party", Element.ALIGN_CENTER, 2, bfBold12, 1, "#BFD6E9", 0.5f, 1f, 0f);

				insertCell(table, "Name: ", invoice.getInvoiceAddressDetails().getInvoiceBillerName(),
						Element.ALIGN_LEFT, 1, bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "Name: ", invoice.getInvoiceAddressDetails().getInvoicePartyName(),
						Element.ALIGN_LEFT, 2, bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "Address: ", invoice.getInvoiceAddressDetails().getInvoiceBillerAddressName(),
						Element.ALIGN_LEFT, 1, bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "Address: ", invoice.getInvoiceAddressDetails().getInvoicePartyAddressName(),
						Element.ALIGN_LEFT, 2, bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "GSTIN: ", invoice.getInvoiceAddressDetails().getInvoiceBillerGst(),
						Element.ALIGN_LEFT, 1, bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "GSTIN: ", invoice.getInvoiceAddressDetails().getInvoicePartyGst(),
						Element.ALIGN_LEFT, 2, bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "State: ", invoice.getInvoiceAddressDetails().getInvoiceBillerState(),
						Element.ALIGN_LEFT, 1, bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
				insertCell(table, "State: ", invoice.getInvoiceAddressDetails().getInvoicePartyState(),
						Element.ALIGN_LEFT, 2, bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
				insertCell(table, "", Element.ALIGN_LEFT, 2, bfBold12, 1, "#FFFFFF", 1f, 1f, 0f);
			}

			insertCell(itemTable, "Sr. No.", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 1f, 0.5f, 30f);
			insertCell(itemTable, "Product Description", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f,
					30f);
			insertCell(itemTable, "HSN Code", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "UOM", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "Qty", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "Rate", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "Amount", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "Discount", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "GST Rate", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "Taxable Value", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			if (isIntraState) {
				insertCell(itemTable, "CGST", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
				insertCell(itemTable, "SGST", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			} else {
				insertCell(itemTable, "IGST", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			}
			insertCell(itemTable, "Total Amount", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 1f, 30f);

			int count = 1;
			double totalAmount = 0, totalTaxValue = 0, totalCgst = 0, totalSgst = 0, totalIgst = 0, ttlTotalAmount = 0,
					totalQty = 0;
			for (InvoiceProductDetails product : invoice.getInvoiceProductDetails()) {
				insertCell(itemTable, String.valueOf(count), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 1f, 0.5f, 0f);
				insertCell(itemTable, product.getProductDescription(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF",
						0.5f, 0.5f, 0f);
				insertCell(itemTable, product.getProductHnscode(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
						0.5f, 0f);
				insertCell(itemTable, product.getProductUom(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f, 0.5f,
						0f);
				insertCell(itemTable, product.getProductQuantity(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
						0.5f, 0f);
				insertCell(itemTable, product.getProductRate(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f, 0.5f,
						0f);
				insertCell(itemTable, product.getProductAmount(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
						0.5f, 0f);
				insertCell(itemTable, product.getProductDiscount(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
						0.5f, 0f);
				insertCell(itemTable, product.getProductGstRate() + "%", Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF",
						0.5f, 0.5f, 0f);
				insertCell(itemTable, product.getProductTaxValue(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
						0.5f, 0f);
				if (isIntraState) {
					insertCell(itemTable, product.getProductCgst(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
							0.5f, 0f);
					insertCell(itemTable, product.getProductSgst(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
							0.5f, 0f);
				} else {
					insertCell(itemTable, product.getProductIgst(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
							0.5f, 0f);
				}
				insertCell(itemTable, product.getProductTotalAmount(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF",
						0.5f, 1f, 0f);
				totalQty = totalQty + Double.valueOf(product.getProductQuantity());
				totalAmount = totalAmount + Double.valueOf(product.getProductAmount());
				totalTaxValue = totalTaxValue + Double.valueOf(product.getProductTaxValue());
				totalCgst = totalCgst + Double.valueOf(product.getProductCgst());
				totalSgst = totalSgst + Double.valueOf(product.getProductSgst());
				totalIgst = totalIgst + Double.valueOf(product.getProductIgst());
				ttlTotalAmount = ttlTotalAmount + Double.valueOf(product.getProductTotalAmount());
				count++;
			}

			insertCell(itemTable, "Total", Element.ALIGN_CENTER, 4, bfBold12, 1, "#FFFFFF", 1f, 0.5f, 30f);
			insertCell(itemTable, String.valueOf(totalQty), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
					30f);
			insertCell(itemTable, "", Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f, 30f);
			insertCell(itemTable, df.format(totalAmount), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
					30f);
			insertCell(itemTable, "", Element.ALIGN_CENTER, 2, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f, 30f);
			insertCell(itemTable, df.format(totalTaxValue), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
					30f);
			if (isIntraState) {
				insertCell(itemTable, df.format(totalCgst), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
						30f);
				insertCell(itemTable, df.format(totalSgst), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
						30f);
			} else {
				insertCell(itemTable, df.format(totalIgst), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
						30f);
			}
			insertCell(itemTable, df.format(ttlTotalAmount), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 1f,
					30f);

			insertCell(itemTable, "Total Amount in Words", Element.ALIGN_CENTER, 5, bfBold12, 1, "#BFD6E9", 1f, 0.5f,
					0f);
			insertCell(itemTable, "Total Amount before Tax", Element.ALIGN_CENTER, isIntraState ? 7 : 6, bfBold12, 1,
					"#FFFFFF", 0.5f, 0.5f, 0f);
			insertCell(itemTable, invoice.getInvoiceTotalAmountBeforeTax(), Element.ALIGN_CENTER, 1, bfBold12, 1,
					"#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, invoice.getInvoiceTotalAmountWords(), Element.ALIGN_CENTER, 5, bf12, 5, "#FFFFFF", 1f,
					0.5f, 0f);
			insertCell(itemTable, "Add:IGST", Element.ALIGN_CENTER, isIntraState ? 7 : 6, bfBold12, 1, "#92D14F", 0.5f,
					0.5f, 0f);
			insertCell(itemTable, isIntraState ? "" : invoice.getInvoiceIgstAmount(), Element.ALIGN_CENTER, 1, bfBold12,
					1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Add:SGST", Element.ALIGN_CENTER, isIntraState ? 7 : 6, bfBold12, 1, "#92D14F", 0.5f,
					0.5f, 0f);
			insertCell(itemTable, isIntraState ? invoice.getInvoiceSgstAmount() : "", Element.ALIGN_CENTER, 1, bfBold12,
					1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Add:CGST", Element.ALIGN_CENTER, isIntraState ? 7 : 6, bfBold12, 1, "#92D14F", 0.5f,
					0.5f, 0f);
			insertCell(itemTable, isIntraState ? invoice.getInvoiceCgstAmount() : "", Element.ALIGN_CENTER, 1, bfBold12,
					1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Total Tax Amount", Element.ALIGN_CENTER, isIntraState ? 7 : 6, bfBold12, 1,
					"#FFFFFF", 0.5f, 0.5f, 0f);
			insertCell(itemTable, invoice.getInvoiceIgstAmount(), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f,
					1f, 0f);

			insertCell(itemTable, "Total Amount after tax", Element.ALIGN_CENTER, isIntraState ? 7 : 6, bfBold12, 1,
					"#FFFFFF", 0.5f, 0.5f, 0f);
			insertCell(itemTable, invoice.getInvoiceTotalAmountAfterTax(), Element.ALIGN_CENTER, 1, bfBold12, 1,
					"#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Bank Details", Element.ALIGN_CENTER, 4, bfBold12, 1, "#BFD6E9", 1f, 0.5f, 0f);
			insertCell(itemTable, "Common Seal", Element.ALIGN_CENTER, 3, bfBold12, 6, "#FFFFFF", 0.5f, 0.5f, 0f);
			insertCell(itemTable, "GST on Reverse Charge", Element.ALIGN_CENTER, isIntraState ? 5 : 4, bfBold12, 1,
					"#BFD6E9", 0.5f, 0.5f, 0f);
			insertCell(itemTable, "", Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Bank A/c: ", invoice.getInvoiceBankDetails().getInvoiceBankAccount(),
					Element.ALIGN_LEFT, 4, bfBold12, bf12, 1, "#FFFFFFF", 1f, 0.5f);
			insertCell(itemTable,
					"Ceritified that the particulars given above are true and correct \n\n\n\n For "
							+ invoice.getInvoiceCompanyDetails().getCompanyName() + " \n\n\n\n Authorised Signatory",
					Element.ALIGN_CENTER, isIntraState ? 6 : 5, bf12, 6, "#FFFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Bank A/c: ", invoice.getInvoiceBankDetails().getInvoiceIfsCode(), Element.ALIGN_LEFT,
					4, bfBold12, bf12, 1, "#FFFFFFF", 1f, 0.5f);
			insertCell(itemTable, "Bank", Element.ALIGN_CENTER, 4, bfBold12, 1, "#FFFFFFF", 1f, 0.5f, 0f);
			insertCell(itemTable, invoice.getInvoiceBankDetails().getInvoiceBankCondition(), Element.ALIGN_CENTER, 4,
					bf12, 4, "#FFFFFFF", 1f, 0.5f, 0f);

			doc.add(header);
			doc.add(tableHeader);
			doc.add(table);
			doc.add(itemTable);

		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null) {
				// close the document
				doc.close();
			}
			if (docWriter != null) {
				// close the writer
				docWriter.close();
			}
		}
		return output;
	}

	/**
	 * @param invoice
	 * @param bfBold12
	 * @param tableHeader
	 * @throws BadElementException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private static void addHeader(InvoiceDetails invoice, Font bfBold12, PdfPTable tableHeader)
			throws BadElementException, MalformedURLException, IOException {
		Image img = Image.getInstance(invoice.getInvoiceCompanyDetails().getCompanyLogo());
		img.scaleAbsolute(120f, 50f);

		PdfPCell cell = new PdfPCell(img);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(1);
		cell.setRowspan(6);
		cell.setBorderWidthLeft(1f);
		cell.setBorderWidthRight(0f);
		cell.setMinimumHeight(0f);
		cell.setBackgroundColor(new BaseColor(Color.decode("#FFFFFF").getRGB()));
		tableHeader.addCell(cell);

		String companyColumn = invoice.getInvoiceCompanyDetails().getCompanyName() + "\n\n"
				+ invoice.getInvoiceCompanyDetails().getCompanyAddress() + "\n\nTel: "
				+ invoice.getInvoiceCompanyDetails().getCompanyTelephone() + "\nGSTIN: "
				+ invoice.getInvoiceCompanyDetails().getCompanyGstin() + "\n";

		PdfPCell cell2 = new PdfPCell(
				new Phrase(companyColumn.trim(), new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD)));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell2.setColspan(1);
		cell2.setRowspan(6);
		cell2.setBorderWidthLeft(0f);
		cell2.setBorderWidthRight(0f);
		cell2.setMinimumHeight(0f);
		cell2.setBackgroundColor(new BaseColor(Color.decode("#FFFFFF").getRGB()));
		tableHeader.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Phrase("Original for Receipient", bfBold12));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell3.setColspan(1);
		cell3.setRowspan(6);
		cell3.setBorderWidthLeft(0f);
		cell3.setBorderWidthRight(1f);
		cell3.setMinimumHeight(0f);
		cell3.setBackgroundColor(new BaseColor(Color.decode("#FFFFFF").getRGB()));
		tableHeader.addCell(cell3);
	}

	private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font, int rowspan,
			String color, float cellLeftBorder, float cellRightBorder, float cellHeight) {

		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		// set the cell alignment
		cell.setHorizontalAlignment(align);
		if (text.contains("Common"))
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		// set the cell column span and row span in case you want to merge two or more
		// cells
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		cell.setBorderWidthLeft(cellLeftBorder);
		cell.setBorderWidthRight(cellRightBorder);
		if (cellHeight != 0f) {
			cell.setMinimumHeight(cellHeight);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		} else {
			cell.setMinimumHeight(cellHeight);
		}
		// in case there is no text and you wan to create an empty row
		cell.setBackgroundColor(new BaseColor(Color.decode(color).getRGB()));
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(10f);
		}
		// add the call to the table
		table.addCell(cell);

	}

	private static void insertCell(PdfPTable table, String label, String text, int align, int colspan, Font fontLabel,
			Font fontText, int rowspan, String color, float cellLeftBorder, float cellRightBorder) {

		// create a new cell with the specified Text and Font
		Phrase phrase = new Phrase();
		phrase.add(new Chunk(label, fontLabel));
		phrase.add(new Chunk(text, fontText));
		PdfPCell cell = new PdfPCell(phrase);
		// set the cell alignment
		cell.setHorizontalAlignment(align);
		// set the cell column span and row span in case you want to merge two or more
		// cells
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		cell.setBorderWidthLeft(cellLeftBorder);
		cell.setBorderWidthRight(cellRightBorder);
		// in case there is no text and you wan to create an empty row
		cell.setBackgroundColor(new BaseColor(Color.decode(color).getRGB()));
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(10f);
		}
		// add the call to the table
		table.addCell(cell);
	}

	private static String reverseDate(String date) {
		if (date.contains("-")) {
			String[] dateArr = date.split("-");
			List<String> listOfDate = Arrays.asList(dateArr);
			Collections.reverse(listOfDate);
			String[] reversed = listOfDate.toArray(dateArr);
			date = String.join("-", reversed);

		}
		return date;
	}

	public static boolean checkInvoiceType(String invoiceType, String reportInvoiceType) {
		boolean isIncluded = false;
		if (reportInvoiceType.contains(invoiceType)) {
			isIncluded = true;
		}
		return isIncluded;
	}

	public static boolean isValidDate(String startDate, String endDate, String invoiveDate) {
		boolean isValid = false;

		if (!CommonUtils.isPopulated(invoiveDate)) {
			isValid = true;
		} else if (!CommonUtils.isPopulated(startDate) && !CommonUtils.isPopulated(endDate)) {
			isValid = true;
		} else if (!CommonUtils.isPopulated(startDate) && CommonUtils.isValidEndDate(invoiveDate, endDate)) {
			isValid = true;
		} else if (!CommonUtils.isPopulated(endDate) && CommonUtils.isValidEndDate(startDate, invoiveDate)) {
			isValid = true;
		} else if (CommonUtils.isPopulated(startDate) && CommonUtils.isPopulated(endDate)
				&& CommonUtils.isValidDate(startDate, endDate, invoiveDate)) {
			isValid = true;
		}
		return isValid;
	}
}
