/**
 * 
 */
package in.winwithweb.gst.util;

import java.util.ArrayList;
import java.util.List;

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
		
		for (ItemList item : salesInvoiceData.getItemList())
		{
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

}
