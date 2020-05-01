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
@Table(name = "invoice_bank_details")
public class InvoiceBankDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_bank_id")
	private int id;

	@Column(name = "invoice_bank_account")
	private String invoiceBankAccount;

	@Column(name = "invoice_ifsc_account")
	private String invoiceIfsCode;

	@Column(name = "invoice_bank_info")
	private String invoiceBankInfo;

	@Column(name = "invoice_bank_conditions")
	private String invoiceBankCondition;

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
	 * @return the invoiceBankAccount
	 */
	public String getInvoiceBankAccount() {
		return invoiceBankAccount;
	}

	/**
	 * @param invoiceBankAccount the invoiceBankAccount to set
	 */
	public void setInvoiceBankAccount(String invoiceBankAccount) {
		this.invoiceBankAccount = invoiceBankAccount;
	}

	/**
	 * @return the invoiceIfsCode
	 */
	public String getInvoiceIfsCode() {
		return invoiceIfsCode;
	}

	/**
	 * @param invoiceIfsCode the invoiceIfsCode to set
	 */
	public void setInvoiceIfsCode(String invoiceIfsCode) {
		this.invoiceIfsCode = invoiceIfsCode;
	}

	/**
	 * @return the invoiceBankInfo
	 */
	public String getInvoiceBankInfo() {
		return invoiceBankInfo;
	}

	/**
	 * @param invoiceBankInfo the invoiceBankInfo to set
	 */
	public void setInvoiceBankInfo(String invoiceBankInfo) {
		this.invoiceBankInfo = invoiceBankInfo;
	}

	/**
	 * @return the invoiceBankCondition
	 */
	public String getInvoiceBankCondition() {
		return invoiceBankCondition;
	}

	/**
	 * @param invoiceBankCondition the invoiceBankCondition to set
	 */
	public void setInvoiceBankCondition(String invoiceBankCondition) {
		this.invoiceBankCondition = invoiceBankCondition;
	}

}
