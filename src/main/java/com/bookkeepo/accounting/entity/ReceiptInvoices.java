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
@Table(name = "receipt_invoice_details")
public class ReceiptInvoices {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "receipt_invoice_id")
	private int id;

	@Column(name = "receipt_invoice_number")
	private String receiptInvoiceNumber;

	@Column(name = "receipt_invoice_total_amount")
	private String receiptInvoiceTotalAmount;

	@Column(name = "receipt_invoice_remaining_amount")
	private String receiptInvoiceRemainingAmount;

	@Column(name = "receipt_invoice_amount")
	private String receiptInvoiceAmount;

	@Column(name = "receipt_invoice_due_amount")
	private String receiptInvoiceDueAmount;

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
	 * @return the receiptInvoiceNumber
	 */
	public String getReceiptInvoiceNumber() {
		return receiptInvoiceNumber;
	}

	/**
	 * @param receiptInvoiceNumber the receiptInvoiceNumber to set
	 */
	public void setReceiptInvoiceNumber(String receiptInvoiceNumber) {
		this.receiptInvoiceNumber = receiptInvoiceNumber;
	}

	/**
	 * @return the receiptInvoiceTotalAmount
	 */
	public String getReceiptInvoiceTotalAmount() {
		return receiptInvoiceTotalAmount;
	}

	/**
	 * @param receiptInvoiceTotalAmount the receiptInvoiceTotalAmount to set
	 */
	public void setReceiptInvoiceTotalAmount(String receiptInvoiceTotalAmount) {
		this.receiptInvoiceTotalAmount = receiptInvoiceTotalAmount;
	}

	/**
	 * @return the receiptInvoiceRemainingAmount
	 */
	public String getReceiptInvoiceRemainingAmount() {
		return receiptInvoiceRemainingAmount;
	}

	/**
	 * @param receiptInvoiceRemainingAmount the receiptInvoiceRemainingAmount to set
	 */
	public void setReceiptInvoiceRemainingAmount(String receiptInvoiceRemainingAmount) {
		this.receiptInvoiceRemainingAmount = receiptInvoiceRemainingAmount;
	}

	/**
	 * @return the receiptInvoiceAmount
	 */
	public String getReceiptInvoiceAmount() {
		return receiptInvoiceAmount;
	}

	/**
	 * @param receiptInvoiceAmount the receiptInvoiceAmount to set
	 */
	public void setReceiptInvoiceAmount(String receiptInvoiceAmount) {
		this.receiptInvoiceAmount = receiptInvoiceAmount;
	}

	/**
	 * @return the receiptInvoiceDueAmount
	 */
	public String getReceiptInvoiceDueAmount() {
		return receiptInvoiceDueAmount;
	}

	/**
	 * @param receiptInvoiceDueAmount the receiptInvoiceDueAmount to set
	 */
	public void setReceiptInvoiceDueAmount(String receiptInvoiceDueAmount) {
		this.receiptInvoiceDueAmount = receiptInvoiceDueAmount;
	}

}
