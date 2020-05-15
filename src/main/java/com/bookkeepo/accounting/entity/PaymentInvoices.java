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
@Table(name = "payment_invoice_details")
public class PaymentInvoices {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_invoice_id")
	private int id;

	@Column(name = "payment_invoice_key")
	private String paymentInvoiceId;

	@Column(name = "payment_invoice_number")
	private String paymentInvoiceNumber;

	@Column(name = "payment_invoice_total_amount")
	private String paymentInvoiceTotalAmount;

	@Column(name = "payment_invoice_remaining_amount")
	private String paymentInvoiceRemainingAmount;

	@Column(name = "payment_invoice_amount")
	private String paymentInvoiceAmount;

	@Column(name = "payment_invoice_due_amount")
	private String paymentInvoiceDueAmount;

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
	 * @return the paymentInvoiceId
	 */
	public String getPaymentInvoiceId() {
		return paymentInvoiceId;
	}

	/**
	 * @param paymentInvoiceId the paymentInvoiceId to set
	 */
	public void setPaymentInvoiceId(String paymentInvoiceId) {
		this.paymentInvoiceId = paymentInvoiceId;
	}

	/**
	 * @return the paymentInvoiceNumber
	 */
	public String getPaymentInvoiceNumber() {
		return paymentInvoiceNumber;
	}

	/**
	 * @param paymentInvoiceNumber the paymentInvoiceNumber to set
	 */
	public void setPaymentInvoiceNumber(String paymentInvoiceNumber) {
		this.paymentInvoiceNumber = paymentInvoiceNumber;
	}

	/**
	 * @return the paymentInvoiceTotalAmount
	 */
	public String getPaymentInvoiceTotalAmount() {
		return paymentInvoiceTotalAmount;
	}

	/**
	 * @param paymentInvoiceTotalAmount the paymentInvoiceTotalAmount to set
	 */
	public void setPaymentInvoiceTotalAmount(String paymentInvoiceTotalAmount) {
		this.paymentInvoiceTotalAmount = paymentInvoiceTotalAmount;
	}

	/**
	 * @return the paymentInvoiceRemainingAmount
	 */
	public String getPaymentInvoiceRemainingAmount() {
		return paymentInvoiceRemainingAmount;
	}

	/**
	 * @param paymentInvoiceRemainingAmount the paymentInvoiceRemainingAmount to set
	 */
	public void setPaymentInvoiceRemainingAmount(String paymentInvoiceRemainingAmount) {
		this.paymentInvoiceRemainingAmount = paymentInvoiceRemainingAmount;
	}

	/**
	 * @return the paymentInvoiceAmount
	 */
	public String getPaymentInvoiceAmount() {
		return paymentInvoiceAmount;
	}

	/**
	 * @param paymentInvoiceAmount the paymentInvoiceAmount to set
	 */
	public void setPaymentInvoiceAmount(String paymentInvoiceAmount) {
		this.paymentInvoiceAmount = paymentInvoiceAmount;
	}

	/**
	 * @return the paymentInvoiceDueAmount
	 */
	public String getPaymentInvoiceDueAmount() {
		return paymentInvoiceDueAmount;
	}

	/**
	 * @param paymentInvoiceDueAmount the paymentInvoiceDueAmount to set
	 */
	public void setPaymentInvoiceDueAmount(String paymentInvoiceDueAmount) {
		this.paymentInvoiceDueAmount = paymentInvoiceDueAmount;
	}

}
