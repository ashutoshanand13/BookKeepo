/**
 * 
 */
package com.bookkeepo.accounting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author sachingoyal
 *
 */
@Data
@Entity
@Table(name = "invoice_number")
public class InvoiceNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_number_id")
	private int id;

	@Column(name = "invoice_user_name")
	private String name;

	@Column(name = "invoice_number_tax_invoice")
	private int invoiceNumberTaxInvoice;

	@Column(name = "invoice_number_credit_note")
	private int invoiceNumberCreditNote;

	@Column(name = "invoice_number_debit_note")
	private int invoiceNumberDebitNote;

	@Column(name = "invoice_number_export_invoice")
	private int invoiceNumberExportInvoice;

	@Column(name = "invoice_number_purchase_order")
	private int invoiceNumberPurchaseOrder;

	@Column(name = "invoice_number_purchase_invoice")
	private int invoiceNumberPurchaseInvoice;

	@Column(name = "invoice_number_bill_of_supply")
	private int invoiceNumberBillSupply;

	@Column(name = "invoice_number_retail_invoice")
	private int invoiceNumberRetailInvoice;

	@Column(name = "invoice_number_default")
	private int invoiceNumberDefault;

	public InvoiceNumber() {

	}

	public InvoiceNumber(String user) {
		this.name = user;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the invoiceNumberTaxInvoice
	 */
	public int getInvoiceNumberTaxInvoice() {
		return invoiceNumberTaxInvoice;
	}

	/**
	 * @param invoiceNumberTaxInvoice the invoiceNumberTaxInvoice to set
	 */
	public void setInvoiceNumberTaxInvoice(int invoiceNumberTaxInvoice) {
		this.invoiceNumberTaxInvoice = invoiceNumberTaxInvoice;
	}

	/**
	 * @return the invoiceNumberCreditNote
	 */
	public int getInvoiceNumberCreditNote() {
		return invoiceNumberCreditNote;
	}

	/**
	 * @param invoiceNumberCreditNote the invoiceNumberCreditNote to set
	 */
	public void setInvoiceNumberCreditNote(int invoiceNumberCreditNote) {
		this.invoiceNumberCreditNote = invoiceNumberCreditNote;
	}

	/**
	 * @return the invoiceNumberDebitNote
	 */
	public int getInvoiceNumberDebitNote() {
		return invoiceNumberDebitNote;
	}

	/**
	 * @param invoiceNumberDebitNote the invoiceNumberDebitNote to set
	 */
	public void setInvoiceNumberDebitNote(int invoiceNumberDebitNote) {
		this.invoiceNumberDebitNote = invoiceNumberDebitNote;
	}

	/**
	 * @return the invoiceNumberExportInvoice
	 */
	public int getInvoiceNumberExportInvoice() {
		return invoiceNumberExportInvoice;
	}

	/**
	 * @param invoiceNumberExportInvoice the invoiceNumberExportInvoice to set
	 */
	public void setInvoiceNumberExportInvoice(int invoiceNumberExportInvoice) {
		this.invoiceNumberExportInvoice = invoiceNumberExportInvoice;
	}

	/**
	 * @return the invoiceNumberPurchaseOrder
	 */
	public int getInvoiceNumberPurchaseOrder() {
		return invoiceNumberPurchaseOrder;
	}

	/**
	 * @param invoiceNumberPurchaseOrder the invoiceNumberPurchaseOrder to set
	 */
	public void setInvoiceNumberPurchaseOrder(int invoiceNumberPurchaseOrder) {
		this.invoiceNumberPurchaseOrder = invoiceNumberPurchaseOrder;
	}

	/**
	 * @return the invoiceNumberPurchaseInvoice
	 */
	public int getInvoiceNumberPurchaseInvoice() {
		return invoiceNumberPurchaseInvoice;
	}

	/**
	 * @param invoiceNumberPurchaseInvoice the invoiceNumberPurchaseInvoice to set
	 */
	public void setInvoiceNumberPurchaseInvoice(int invoiceNumberPurchaseInvoice) {
		this.invoiceNumberPurchaseInvoice = invoiceNumberPurchaseInvoice;
	}

	/**
	 * @return the invoiceNumberBillSupply
	 */
	public int getInvoiceNumberBillSupply() {
		return invoiceNumberBillSupply;
	}

	/**
	 * @param invoiceNumberBillSupply the invoiceNumberBillSupply to set
	 */
	public void setInvoiceNumberBillSupply(int invoiceNumberBillSupply) {
		this.invoiceNumberBillSupply = invoiceNumberBillSupply;
	}

	/**
	 * @return the invoiceNumberRetailInvoice
	 */
	public int getInvoiceNumberRetailInvoice() {
		return invoiceNumberRetailInvoice;
	}

	/**
	 * @param invoiceNumberRetailInvoice the invoiceNumberRetailInvoice to set
	 */
	public void setInvoiceNumberRetailInvoice(int invoiceNumberRetailInvoice) {
		this.invoiceNumberRetailInvoice = invoiceNumberRetailInvoice;
	}

	/**
	 * @return the invoiceNumberDefault
	 */
	public int getInvoiceNumberDefault() {
		return invoiceNumberDefault;
	}

	/**
	 * @param invoiceNumberDefault the invoiceNumberDefault to set
	 */
	public void setInvoiceNumberDefault(int invoiceNumberDefault) {
		this.invoiceNumberDefault = invoiceNumberDefault;
	}
	
	
}
