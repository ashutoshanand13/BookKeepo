
package com.bookkeepo.accounting.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemList {
	@SerializedName("srNo")
	@Expose
	private String srNo;
	@SerializedName("productDesc")
	@Expose
	private String productDesc;
	@SerializedName("hsnCode")
	@Expose
	private String hsnCode;
	@SerializedName("uom")
	@Expose
	private String uom;
	@SerializedName("qty")
	@Expose
	private String qty;
	@SerializedName("rate")
	@Expose
	private String rate;
	@SerializedName("amount")
	@Expose
	private String amount;
	@SerializedName("discount")
	@Expose
	private String discount;
	@SerializedName("gstRate")
	@Expose
	private String gstRate;
	@SerializedName("taxableValue")
	@Expose
	private String taxableValue;
	@SerializedName("cgst")
	@Expose
	private String cgst;
	@SerializedName("sgst")
	@Expose
	private String sgst;
	@SerializedName("igst")
	@Expose
	private String igst;
	@SerializedName("totalAmount")
	@Expose
	private String totalAmount;

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getGstRate() {
		return gstRate;
	}

	public void setGstRate(String gstRate) {
		this.gstRate = gstRate;
	}

	public String getTaxableValue() {
		return taxableValue;
	}

	public void setTaxableValue(String taxableValue) {
		this.taxableValue = taxableValue;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "ItemList [srNo=" + srNo + ", productDesc=" + productDesc + ", hsnCode=" + hsnCode + ", uom=" + uom
				+ ", qty=" + qty + ", rate=" + rate + ", amount=" + amount + ", discount=" + discount + ", gstRate="
				+ gstRate + ", taxableValue=" + taxableValue + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst
				+ ", totalAmount=" + totalAmount + "]";
	}

}
