
package in.winwithweb.gst.model.json;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoicePageData {

	@SerializedName("invoiceNo")
	@Expose
	private String invoiceNo;
	@SerializedName("poNo")
	@Expose
	private String poNo;
	@SerializedName("invoiceDate")
	@Expose
	private String invoiceDate;
	@SerializedName("poDate")
	@Expose
	private String poDate;
	@SerializedName("dateOfSupply")
	@Expose
	private String dateOfSupply;
	@SerializedName("transportMode")
	@Expose
	private String transportMode;
	@SerializedName("placeOfSupply")
	@Expose
	private String placeOfSupply;
	@SerializedName("vehicleNo")
	@Expose
	private String vehicleNo;
	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("reverseCharge")
	@Expose
	private String reverseCharge;
	@SerializedName("againstInvoice")
	@Expose
	private String againstInvoice;
	@SerializedName("issueDate")
	@Expose
	private String issueDate;
	@SerializedName("nameBill")
	@Expose
	private String nameBill;
	@SerializedName("nameShip")
	@Expose
	private String nameShip;
	@SerializedName("addressBill")
	@Expose
	private String addressBill;
	@SerializedName("addressShip")
	@Expose
	private String addressShip;
	@SerializedName("gstinBill")
	@Expose
	private String gstinBill;
	@SerializedName("gstinShip")
	@Expose
	private String gstinShip;
	@SerializedName("stateBill")
	@Expose
	private String stateBill;
	@SerializedName("stateShip")
	@Expose
	private String stateShip;
	@SerializedName("itemList")
	@Expose
	private List<ItemList> itemList = null;
	@SerializedName("ttlQty")
	@Expose
	private String ttlQty;
	@SerializedName("ttlAmount")
	@Expose
	private String ttlAmount;
	@SerializedName("ttlTaxableValue")
	@Expose
	private String ttlTaxableValue;
	@SerializedName("ttlCgst")
	@Expose
	private String ttlCgst;
	@SerializedName("ttlSgst")
	@Expose
	private String ttlSgst;
	@SerializedName("ttlIgst")
	@Expose
	private String ttlIgst;
	@SerializedName("ttlTotalAmount")
	@Expose
	private String ttlTotalAmount;
	@SerializedName("totalAmountBeforeTax")
	@Expose
	private String totalAmountBeforeTax;
	@SerializedName("totalAddIGst")
	@Expose
	private String totalAddIGst;
	@SerializedName("totalAddSGst")
	@Expose
	private String totalAddSGst;
	@SerializedName("totalAddCGst")
	@Expose
	private String totalAddCGst;
	@SerializedName("totalTaxAmount")
	@Expose
	private String totalTaxAmount;
	@SerializedName("totalAmountAfterTax")
	@Expose
	private String totalAmountAfterTax;
	@SerializedName("gstReverseCharge")
	@Expose
	private String gstReverseCharge;
	@SerializedName("bankAccountNumber")
	@Expose
	private String bankAccountNumber;
	@SerializedName("bankifsc")
	@Expose
	private String bankifsc;
	@SerializedName("termsConditions")
	@Expose
	private String termsConditions;
	@SerializedName("partyName")
	@Expose
	private String partyName;
	@SerializedName("partyAddress")
	@Expose
	private String partyAddress;
	@SerializedName("partyDate")
	@Expose
	private String partyDate;
	@SerializedName("partyGstin")
	@Expose
	private String partyGstin;
	@SerializedName("partyState")
	@Expose
	private String partyState;

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getDateOfSupply() {
		return dateOfSupply;
	}

	public void setDateOfSupply(String dateOfSupply) {
		this.dateOfSupply = dateOfSupply;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getPlaceOfSupply() {
		return placeOfSupply;
	}

	public void setPlaceOfSupply(String placeOfSupply) {
		this.placeOfSupply = placeOfSupply;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReverseCharge() {
		return reverseCharge;
	}

	public void setReverseCharge(String reverseCharge) {
		this.reverseCharge = reverseCharge;
	}

	public String getNameBill() {
		return nameBill;
	}

	public void setNameBill(String nameBill) {
		this.nameBill = nameBill;
	}

	public String getNameShip() {
		return nameShip;
	}

	public void setNameShip(String nameShip) {
		this.nameShip = nameShip;
	}

	public String getAddressBill() {
		return addressBill;
	}

	public void setAddressBill(String addressBill) {
		this.addressBill = addressBill;
	}

	public String getAddressShip() {
		return addressShip;
	}

	public void setAddressShip(String addressShip) {
		this.addressShip = addressShip;
	}

	public String getGstinBill() {
		return gstinBill;
	}

	public void setGstinBill(String gstinBill) {
		this.gstinBill = gstinBill;
	}

	public String getGstinShip() {
		return gstinShip;
	}

	public void setGstinShip(String gstinShip) {
		this.gstinShip = gstinShip;
	}

	public String getStateBill() {
		return stateBill;
	}

	public void setStateBill(String stateBill) {
		this.stateBill = stateBill;
	}

	public String getStateShip() {
		return stateShip;
	}

	public void setStateShip(String stateShip) {
		this.stateShip = stateShip;
	}

	public List<ItemList> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemList> itemList) {
		this.itemList = itemList;
	}

	public String getTtlQty() {
		return ttlQty;
	}

	public void setTtlQty(String ttlQty) {
		this.ttlQty = ttlQty;
	}

	public String getTtlAmount() {
		return ttlAmount;
	}

	public void setTtlAmount(String ttlAmount) {
		this.ttlAmount = ttlAmount;
	}

	public String getTtlTaxableValue() {
		return ttlTaxableValue;
	}

	public void setTtlTaxableValue(String ttlTaxableValue) {
		this.ttlTaxableValue = ttlTaxableValue;
	}

	public String getTtlCgst() {
		return ttlCgst;
	}

	public void setTtlCgst(String ttlCgst) {
		this.ttlCgst = ttlCgst;
	}

	public String getTtlSgst() {
		return ttlSgst;
	}

	public void setTtlSgst(String ttlSgst) {
		this.ttlSgst = ttlSgst;
	}

	public String getTtlIgst() {
		return ttlIgst;
	}

	public void setTtlIgst(String ttlIgst) {
		this.ttlIgst = ttlIgst;
	}

	public String getTtlTotalAmount() {
		return ttlTotalAmount;
	}

	public void setTtlTotalAmount(String ttlTotalAmount) {
		this.ttlTotalAmount = ttlTotalAmount;
	}

	public String getTotalAmountBeforeTax() {
		return totalAmountBeforeTax;
	}

	public void setTotalAmountBeforeTax(String totalAmountBeforeTax) {
		this.totalAmountBeforeTax = totalAmountBeforeTax;
	}

	public String getTotalAddIGst() {
		return totalAddIGst;
	}

	public void setTotalAddIGst(String totalAddIGst) {
		this.totalAddIGst = totalAddIGst;
	}

	public String getTotalAddSGst() {
		return totalAddSGst;
	}

	public void setTotalAddSGst(String totalAddSGst) {
		this.totalAddSGst = totalAddSGst;
	}

	public String getTotalAddCGst() {
		return totalAddCGst;
	}

	public void setTotalAddCGst(String totalAddCGst) {
		this.totalAddCGst = totalAddCGst;
	}

	public String getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(String totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public String getTotalAmountAfterTax() {
		return totalAmountAfterTax;
	}

	public void setTotalAmountAfterTax(String totalAmountAfterTax) {
		this.totalAmountAfterTax = totalAmountAfterTax;
	}

	public String getGstReverseCharge() {
		return gstReverseCharge;
	}

	public void setGstReverseCharge(String gstReverseCharge) {
		this.gstReverseCharge = gstReverseCharge;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankifsc() {
		return bankifsc;
	}

	public void setBankifsc(String bankifsc) {
		this.bankifsc = bankifsc;
	}

	public String getTermsConditions() {
		return termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

	/**
	 * @return the againstInvoice
	 */
	public String getAgainstInvoice() {
		return againstInvoice;
	}

	/**
	 * @param againstInvoice the againstInvoice to set
	 */
	public void setAgainstInvoice(String againstInvoice) {
		this.againstInvoice = againstInvoice;
	}


	/**
	 * @return the issueDate
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName the partyName to set
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/**
	 * @return the partyAddress
	 */
	public String getPartyAddress() {
		return partyAddress;
	}

	/**
	 * @param partyAddress the partyAddress to set
	 */
	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}

	/**
	 * @return the partyDate
	 */
	public String getPartyDate() {
		return partyDate;
	}

	/**
	 * @param partyDate the partyDate to set
	 */
	public void setPartyDate(String partyDate) {
		this.partyDate = partyDate;
	}

	/**
	 * @return the partyGstin
	 */
	public String getPartyGstin() {
		return partyGstin;
	}

	/**
	 * @param partyGstin the partyGstin to set
	 */
	public void setPartyGstin(String partyGstin) {
		this.partyGstin = partyGstin;
	}

	/**
	 * @return the partyState
	 */
	public String getPartyState() {
		return partyState;
	}

	/**
	 * @param partyState the partyState to set
	 */
	public void setPartyState(String partyState) {
		this.partyState = partyState;
	}

	@Override
	public String toString() {
		return "Example [invoiceNo=" + invoiceNo + ", poNo=" + poNo + ", invoiceDate=" + invoiceDate + ", poDate="
				+ poDate + ", dateOfSupply=" + dateOfSupply + ", transportMode=" + transportMode + ", placeOfSupply="
				+ placeOfSupply + ", vehicleNo=" + vehicleNo + ", state=" + state + ", reverseCharge=" + reverseCharge
				+ ", nameBill=" + nameBill + ", nameShip=" + nameShip + ", addressBill=" + addressBill
				+ ", addressShip=" + addressShip + ", gstinBill=" + gstinBill + ", gstinShip=" + gstinShip
				+ ", stateBill=" + stateBill + ", stateShip=" + stateShip + ", itemList=" + itemList + ", ttlQty="
				+ ttlQty + ", ttlAmount=" + ttlAmount + ", ttlTaxableValue=" + ttlTaxableValue + ", ttlCgst=" + ttlCgst
				+ ", ttlSgst=" + ttlSgst + ", ttlIgst=" + ttlIgst + ", ttlTotalAmount=" + ttlTotalAmount
				+ ", totalAmountBeforeTax=" + totalAmountBeforeTax + ", totalAddIGst=" + totalAddIGst
				+ ", totalAddSGst=" + totalAddSGst + ", totalAddCGst=" + totalAddCGst + ", totalTaxAmount="
				+ totalTaxAmount + ", totalAmountAfterTax=" + totalAmountAfterTax + ", gstReverseCharge="
				+ gstReverseCharge + ", bankAccountNumber=" + bankAccountNumber + ", bankifsc=" + bankifsc
				+ ", termsConditions=" + termsConditions + "]";
	}

}