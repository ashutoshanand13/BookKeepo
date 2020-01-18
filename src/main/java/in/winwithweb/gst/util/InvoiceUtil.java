/**
 * 
 */
package in.winwithweb.gst.util;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import in.winwithweb.gst.model.json.ItemList;
import in.winwithweb.gst.model.json.SalesInvoicePageData;
import in.winwithweb.gst.model.sales.InvoiceAddressDetails;
import in.winwithweb.gst.model.sales.InvoiceBankDetails;
import in.winwithweb.gst.model.sales.InvoiceCompanyDetails;
import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.model.sales.InvoiceProductDetails;

/**
 * @author yash Singh
 *
 */
public class InvoiceUtil {

	public static void updateInvoice(InvoiceDetails invoice, SalesInvoicePageData salesInvoiceData) {

		List<InvoiceProductDetails> productList = new ArrayList<InvoiceProductDetails>();
		InvoiceBankDetails invoiceBankDetails = new InvoiceBankDetails();
		InvoiceAddressDetails invoiceAddressDetails = new InvoiceAddressDetails();
		InvoiceCompanyDetails invoiceCompanyDetails = new InvoiceCompanyDetails();

		invoice.setInvoiceNumber(salesInvoiceData.getInvoiceNo());
		invoice.setInvoiceDate(salesInvoiceData.getInvoiceDate());
		invoice.setInvoiceDOS(salesInvoiceData.getDateOfSupply());
		invoice.setInvoicePOS(salesInvoiceData.getPlaceOfSupply());
		invoice.setInvoicePoDate(salesInvoiceData.getPoDate());
		invoice.setInvoiceState(salesInvoiceData.getState());
		invoice.setInvoicePoNumber(salesInvoiceData.getPoNo());
		invoice.setInvoiceTransportMode(salesInvoiceData.getTransportMode());
		invoice.setInvoiceVehicleNumber(salesInvoiceData.getVehicleNo());
		invoice.setInvoiceReverseCharge(salesInvoiceData.getReverseCharge());
		invoice.setInvoiceTotalAmountBeforeTax(salesInvoiceData.getTotalAmountBeforeTax());
		invoice.setInvoiceTotalAmountAfterTax(salesInvoiceData.getTotalAmountAfterTax());
		invoice.setInvoiceTaxAmount(salesInvoiceData.getTotalTaxAmount());
		invoice.setInvoiceGstAmount(salesInvoiceData.getTotalGst());

		invoiceBankDetails.setInvoiceBankAccount(salesInvoiceData.getBankAccountNumber());
		invoiceBankDetails.setInvoiceIfsCode(salesInvoiceData.getBankifsc());
		invoiceBankDetails.setInvoiceBankCondition(salesInvoiceData.getTermsConditions());
		invoice.setInvoiceBankDetails(invoiceBankDetails);

		invoiceAddressDetails.setInvoiceBillerName(salesInvoiceData.getNameBill());
		invoiceAddressDetails.setInvoiceBillerAddressName(salesInvoiceData.getAddressBill());
		invoiceAddressDetails.setInvoiceBillerGst(salesInvoiceData.getGstinBill());
		invoiceAddressDetails.setInvoiceBillerState(salesInvoiceData.getStateBill());
		invoiceAddressDetails.setInvoicePartyName(salesInvoiceData.getNameShip());
		invoiceAddressDetails.setInvoicePartyAddressName(salesInvoiceData.getAddressShip());
		invoiceAddressDetails.setInvoicePartyGst(salesInvoiceData.getGstinShip());
		invoiceAddressDetails.setInvoicePartyState(salesInvoiceData.getStateShip());
		invoice.setInvoiceAddressDetails(invoiceAddressDetails);

		invoiceCompanyDetails.setComanyName(salesInvoiceData.getCompanyNameHeader());
		invoiceCompanyDetails.setAddress(salesInvoiceData.getAddressHeader());
		invoiceCompanyDetails.setTelephone(salesInvoiceData.getTelephoneHeader());
		invoiceCompanyDetails.setCompanyGstin(salesInvoiceData.getGstinHeader());
		invoiceCompanyDetails.setCompanyEmail(salesInvoiceData.getEmailHeader());
		invoice.setInvoiceCompanyDetails(invoiceCompanyDetails);

		for (ItemList item : salesInvoiceData.getItemList()) {
			InvoiceProductDetails invoiceProductDetails = new InvoiceProductDetails();
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
			productList.add(invoiceProductDetails);
		}

		invoice.setInvoiceProductDetails(productList);

	}

	public static ByteArrayInputStream createPDF(InvoiceDetails invoice) {

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		Document doc = new Document();
		PdfWriter docWriter = null;

		DecimalFormat df = new DecimalFormat("0.00");

		try {

			// special font sizes
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0));
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 8);

			docWriter = PdfWriter.getInstance(doc, output);

			// document header attributes
			doc.addAuthor("betterThanZero");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("MySampleCode.com");
			doc.addTitle("Report with Column Headings");
			doc.setPageSize(PageSize.A4);

			// open document
			doc.open();

			// specify column widths
			float[] columnWidths = { 5f, 5f };
			float[] columnItemTableWidths = { 0.7f, 2.2f, 1f, 1f, 1f, 1f, 1.5f, 1.5f, 1f, 1.6f, 1.1f, 1.1f, 1.f, 1.3f };
			// create PDF table with the given widths
			PdfPTable table = new PdfPTable(columnWidths);
			PdfPTable itemTable = new PdfPTable(columnItemTableWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(90f);
			itemTable.setWidthPercentage(90f);

			insertCell(table, "", Element.ALIGN_LEFT, 1, bf12, 5, "#FFFFFF", 1f, 0.5f, 0f);
			insertCell(table, invoice.getInvoiceCompanyDetails().getComanyName(), Element.ALIGN_LEFT, 1, bf12, 1,
					"#FFFFFF", 0.5f, 1f, 0f);
			insertCell(table, invoice.getInvoiceCompanyDetails().getAddress(), Element.ALIGN_LEFT, 1, bf12, 1,
					"#FFFFFF", 0.5f, 1f, 0f);
			insertCell(table, invoice.getInvoiceCompanyDetails().getTelephone(), Element.ALIGN_LEFT, 1, bf12, 1,
					"#FFFFFF", 0.5f, 1f, 0f);
			insertCell(table, invoice.getInvoiceCompanyDetails().getCompanyGstin(), Element.ALIGN_LEFT, 1, bf12, 1,
					"#FFFFFF", 0.5f, 1f, 0f);
			insertCell(table, invoice.getInvoiceCompanyDetails().getCompanyEmail(), Element.ALIGN_LEFT, 1, bf12, 1,
					"#FFFFFF", 0.5f, 1f, 0f);

			insertCell(table, "", Element.ALIGN_LEFT, 2, bfBold12, 1, "#FFFFFF", 1f, 1f, 0f);

			insertCell(table, "Tax Invoice", Element.ALIGN_CENTER, 2, bfBold12, 1, "#BFD6E9", 1f, 1f, 30f);
			insertCell(table, "Invoice No: ", invoice.getInvoiceNumber(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
					"#FFFFFF", 1f, 0.5f);
			insertCell(table, "PO No: ", invoice.getInvoicePoNumber(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
					"#FFFFFF", 0.5f, 1f);
			insertCell(table, "Invoice Date: ", invoice.getInvoiceDate(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
					"#FFFFFF", 1f, 0.5f);
			insertCell(table, "PO Date: ", invoice.getInvoicePoDate(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
					"#FFFFFF", 0.5f, 1f);
			insertCell(table, "Date Of Supply: ", invoice.getInvoiceDOS(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
					"#FFFFFF", 1f, 0.5f);
			insertCell(table, "Transport Mode: ", invoice.getInvoiceTransportMode(), Element.ALIGN_LEFT, 1, bfBold12,
					bf12, 1, "#FFFFFF", 0.5f, 1f);
			insertCell(table, "Place Of Supply: ", invoice.getInvoicePOS(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1,
					"#FFFFFF", 1f, 0.5f);
			insertCell(table, "Vehicle No: ", invoice.getInvoiceVehicleNumber(), Element.ALIGN_LEFT, 1, bfBold12, bf12,
					1, "#FFFFFF", 0.5f, 1f);
			insertCell(table, "State: ", invoice.getInvoiceState(), Element.ALIGN_LEFT, 1, bfBold12, bf12, 1, "#FFFFFF",
					1f, 0.5f);
			insertCell(table, "Reverse Charge (Y/N): ", invoice.getInvoiceReverseCharge(), Element.ALIGN_LEFT, 1,
					bfBold12, bf12, 1, "#EEFF74", 0.5f, 1f);

			// merge the cells to create a footer for that section
			insertCell(table, "", Element.ALIGN_LEFT, 2, bfBold12, 1, "#FFFFFF", 1f, 1f, 0f);

			insertCell(table, "Bill to Party", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 1f, 0.5f, 0f);
			insertCell(table, "Ship to Party", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 1f, 0f);

			insertCell(table, "Name: ", invoice.getInvoiceAddressDetails().getInvoiceBillerName(), Element.ALIGN_LEFT,
					1, bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
			insertCell(table, "Name: ", invoice.getInvoiceAddressDetails().getInvoicePartyName(), Element.ALIGN_LEFT, 1,
					bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
			insertCell(table, "Address: ", invoice.getInvoiceAddressDetails().getInvoiceBillerAddressName(),
					Element.ALIGN_LEFT, 1, bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
			insertCell(table, "Address: ", invoice.getInvoiceAddressDetails().getInvoicePartyAddressName(),
					Element.ALIGN_LEFT, 1, bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
			insertCell(table, "GSTIN: ", invoice.getInvoiceAddressDetails().getInvoiceBillerGst(), Element.ALIGN_LEFT,
					1, bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
			insertCell(table, "GSTIN: ", invoice.getInvoiceAddressDetails().getInvoicePartyGst(), Element.ALIGN_LEFT, 1,
					bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
			insertCell(table, "State: ", invoice.getInvoiceAddressDetails().getInvoiceBillerState(), Element.ALIGN_LEFT,
					1, bfBold12, bf12, 1, "#FFFFFF", 1f, 0.5f);
			insertCell(table, "State: ", invoice.getInvoiceAddressDetails().getInvoicePartyState(), Element.ALIGN_LEFT,
					1, bfBold12, bf12, 1, "#FFFFFF", 0.5f, 1f);
			insertCell(table, "", Element.ALIGN_LEFT, 2, bfBold12, 1, "#FFFFFF", 1f, 1f, 0f);

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
			insertCell(itemTable, "CGST", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "SGST", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "IGST", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f, 30f);
			insertCell(itemTable, "Total Amount", Element.ALIGN_CENTER, 1, bfBold12, 1, "#BFD6E9", 0.5f, 1f, 30f);

			int count = 1;
			int totalQty = 0;
			double totalAmount = 0, totalTaxValue = 0, totalCgst = 0, totalSgst = 0, totalIgst = 0, ttlTotalAmount = 0;
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
				insertCell(itemTable, product.getProductGstRate(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
						0.5f, 0f);
				insertCell(itemTable, product.getProductTaxValue(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f,
						0.5f, 0f);
				insertCell(itemTable, product.getProductCgst(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f, 0.5f,
						0f);
				insertCell(itemTable, product.getProductSgst(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f, 0.5f,
						0f);
				insertCell(itemTable, product.getProductIgst(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF", 0.5f, 0.5f,
						0f);
				insertCell(itemTable, product.getProductTotalAmount(), Element.ALIGN_CENTER, 1, bf12, 1, "#FFFFFF",
						0.5f, 1f, 0f);
				totalQty = totalQty + Integer.valueOf(product.getProductQuantity());
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
			insertCell(itemTable, df.format(totalCgst), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
					30f);
			insertCell(itemTable, df.format(totalSgst), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
					30f);
			insertCell(itemTable, df.format(totalIgst), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
					30f);
			insertCell(itemTable, df.format(ttlTotalAmount), Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 1f,
					30f);

			insertCell(itemTable, "Total Amount in Words", Element.ALIGN_CENTER, 5, bfBold12, 1, "#BFD6E9", 1f, 0.5f,
					0f);
			insertCell(itemTable, "Total Amount before Tax", Element.ALIGN_CENTER, 8, bfBold12, 1, "#FFFFFF", 0.5f,
					0.5f, 0f);
			insertCell(itemTable, "", Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "", Element.ALIGN_CENTER, 5, bfBold12, 3, "#FFFFFF", 1f, 0.5f, 0f);
			insertCell(itemTable, "Add:IGST", Element.ALIGN_CENTER, 8, bfBold12, 1, "#92D14F", 0.5f, 0.5f, 0f);
			insertCell(itemTable, "", Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Total Tax Amount", Element.ALIGN_CENTER, 8, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f, 0f);
			insertCell(itemTable, "", Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Total Amount after tax", Element.ALIGN_CENTER, 8, bfBold12, 1, "#FFFFFF", 0.5f, 0.5f,
					0f);
			insertCell(itemTable, "", Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, "Bank Details", Element.ALIGN_CENTER, 4, bfBold12, 1, "#BFD6E9", 1f, 0.5f, 0f);
			insertCell(itemTable, "Common Seal", Element.ALIGN_CENTER, 3, bfBold12, 6, "#FFFFFF", 0.5f, 0.5f, 0f);
			insertCell(itemTable, "GST on Reverse Charge", Element.ALIGN_CENTER, 6, bfBold12, 1, "#BFD6E9", 0.5f, 0.5f,
					0f);
			insertCell(itemTable, "", Element.ALIGN_CENTER, 1, bfBold12, 1, "#FFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, invoice.getInvoiceBankDetails().getInvoiceBankAccount(), Element.ALIGN_CENTER, 4,
					bf12, 1, "#FFFFFFF", 1f, 0.5f, 0f);
			insertCell(itemTable,
					"Ceritified that the particulars given above are true and correct \n\n\n\n For Company Name \n\n\n\n Authorised Signatory",
					Element.ALIGN_CENTER, 7, bf12, 6, "#FFFFFFF", 0.5f, 1f, 0f);

			insertCell(itemTable, invoice.getInvoiceBankDetails().getInvoiceIfsCode(), Element.ALIGN_CENTER, 4, bf12, 1,
					"#FFFFFFF", 1f, 0.5f, 0f);
			insertCell(itemTable, "Bank", Element.ALIGN_CENTER, 4, bfBold12, 1, "#FFFFFFF", 1f, 0.5f, 0f);
			insertCell(itemTable, invoice.getInvoiceBankDetails().getInvoiceBankCondition(), Element.ALIGN_CENTER, 4,
					bf12, 4, "#FFFFFFF", 1f, 0.5f, 0f);

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
		return new ByteArrayInputStream(output.toByteArray());
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

}