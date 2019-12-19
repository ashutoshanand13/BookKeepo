
package in.winwithweb.gst.model.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesInvoicePageData {

    @SerializedName("companyNameHeader")
    @Expose
    private String companyNameHeader;
    @SerializedName("addressHeader")
    @Expose
    private String addressHeader;
    @SerializedName("telephoneHeader")
    @Expose
    private String telephoneHeader;
    @SerializedName("gstinHeader")
    @Expose
    private String gstinHeader;
    @SerializedName("emailHeader")
    @Expose
    private String emailHeader;
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
    @SerializedName("nameBill")
    @Expose
    private String nameBill;
    @SerializedName("nameShip")
    @Expose
    private String nameShip;
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

    public String getComapnyNameHeader() {
        return companyNameHeader;
    }

    public void setComapnyNameHeader(String comapnyNameHeader) {
        this.companyNameHeader = comapnyNameHeader;
    }

    public String getAddressHeader() {
        return addressHeader;
    }

    public void setAddressHeader(String addressHeader) {
        this.addressHeader = addressHeader;
    }

    public String getTelephoneHeader() {
        return telephoneHeader;
    }

    public void setTelephoneHeader(String telephoneHeader) {
        this.telephoneHeader = telephoneHeader;
    }

    public String getGstinHeader() {
        return gstinHeader;
    }

    public void setGstinHeader(String gstinHeader) {
        this.gstinHeader = gstinHeader;
    }

    public String getEmailHeader() {
        return emailHeader;
    }

    public void setEmailHeader(String emailHeader) {
        this.emailHeader = emailHeader;
    }

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

    public List<ItemList> getItem() {
        return itemList;
    }

    public void setItem(List<ItemList> item) {
        this.itemList = item;
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

	@Override
	public String toString() {
		return "Example [comapnyNameHeader=" + companyNameHeader + ", addressHeader=" + addressHeader
				+ ", telephoneHeader=" + telephoneHeader + ", gstinHeader=" + gstinHeader + ", emailHeader="
				+ emailHeader + ", invoiceNo=" + invoiceNo + ", poNo=" + poNo + ", invoiceDate=" + invoiceDate
				+ ", poDate=" + poDate + ", dateOfSupply=" + dateOfSupply + ", transportMode=" + transportMode
				+ ", placeOfSupply=" + placeOfSupply + ", vehicleNo=" + vehicleNo + ", state=" + state
				+ ", reverseCharge=" + reverseCharge + ", nameBill=" + nameBill + ", nameShip=" + nameShip
				+ ", addressShip=" + addressShip + ", gstinBill=" + gstinBill + ", gstinShip=" + gstinShip
				+ ", stateBill=" + stateBill + ", stateShip=" + stateShip + ", ttlQty=" + ttlQty + ", ttlAmount="
				+ ttlAmount + ", ttlTaxableValue=" + ttlTaxableValue + ", ttlCgst=" + ttlCgst + ", ttlSgst=" + ttlSgst
				+ ", ttlIgst=" + ttlIgst + ", ttlTotalAmount=" + ttlTotalAmount + "]";
	}

    
}
