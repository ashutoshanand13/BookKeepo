package com.bookkeepo.accounting.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "receipt_details")
public class Receipts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "receipt_id")
	private int id;

	@Column(name = "receipt_number", updatable = false)
	private int receiptNumber;

	@Column(name = "receipt_reference")
	@NotEmpty(message = "*Please provide the receipt reference")
	private String receiptReference;

	@Column(name = "receipt_date")
	@NotEmpty(message = "*Please provide the receipt date")
	private String receiptDate;

	@Column(name = "receipt_mode")
	@NotEmpty(message = "*Please provide the receipt mode")
	private String receiptMode;

	@Column(name = "receipt_amount")
	@NotEmpty(message = "*Please provide the receipt amount")
	private String receiptAmount;

	@Column(name = "receipt_description")
	private String receiptDescription;

	@Column(name = "receipt_owner", nullable = false, updatable = false)
	private String receiptOwner;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "receipt_creation_date", nullable = false, updatable = false)
	private Date receiptCreationDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_account_reference_no")
	private Accounts accountRefNo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_company_reference_no", updatable = false)
	private Company receiptCompanyDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_bank_reference_no", updatable = false)
	private BankDetails bankDetails;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_invoice_reference_no")
	private List<ReceiptInvoices> receiptInvoiceDetails;
	
	@Column(name = "receipt_deleted", nullable = false)
	private int receiptDeleted;

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
	 * @return the receiptNumber
	 */
	public int getReceiptNumber() {
		return receiptNumber;
	}

	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(int receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	/**
	 * @return the receiptAmount
	 */
	public String getReceiptAmount() {
		return receiptAmount;
	}

	/**
	 * @param receiptAmount the receiptAmount to set
	 */
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	/**
	 * @return the receiptReference
	 */
	public String getReceiptReference() {
		return receiptReference;
	}

	/**
	 * @param receiptReference the receiptReference to set
	 */
	public void setReceiptReference(String receiptReference) {
		this.receiptReference = receiptReference;
	}

	/**
	 * @return the receiptDate
	 */
	public String getReceiptDate() {
		return receiptDate;
	}

	/**
	 * @param receiptDate the receiptDate to set
	 */
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	/**
	 * @return the receiptMode
	 */
	public String getReceiptMode() {
		return receiptMode;
	}

	/**
	 * @param receiptMode the receiptMode to set
	 */
	public void setReceiptMode(String receiptMode) {
		this.receiptMode = receiptMode;
	}

	/**
	 * @return the receiptDescription
	 */
	public String getReceiptDescription() {
		return receiptDescription;
	}

	/**
	 * @param receiptDescription the receiptDescription to set
	 */
	public void setReceiptDescription(String receiptDescription) {
		this.receiptDescription = receiptDescription;
	}

	/**
	 * @return the receiptOwner
	 */
	public String getReceiptOwner() {
		return receiptOwner;
	}

	/**
	 * @param receiptOwner the receiptOwner to set
	 */
	public void setReceiptOwner(String receiptOwner) {
		this.receiptOwner = receiptOwner;
	}

	/**
	 * @return the receiptCreationDate
	 */
	public Date getReceiptCreationDate() {
		return receiptCreationDate;
	}

	/**
	 * @param receiptCreationDate the receiptCreationDate to set
	 */
	public void setReceiptCreationDate(Date receiptCreationDate) {
		this.receiptCreationDate = receiptCreationDate;
	}

	/**
	 * @return the accountRefNo
	 */
	public Accounts getAccountRefNo() {
		return accountRefNo;
	}

	/**
	 * @param accountRefNo the accountRefNo to set
	 */
	public void setAccountRefNo(Accounts accountRefNo) {
		this.accountRefNo = accountRefNo;
	}

	/**
	 * @return the receiptCompanyDetails
	 */
	public Company getReceiptCompanyDetails() {
		return receiptCompanyDetails;
	}

	/**
	 * @param receiptCompanyDetails the receiptCompanyDetails to set
	 */
	public void setReceiptCompanyDetails(Company receiptCompanyDetails) {
		this.receiptCompanyDetails = receiptCompanyDetails;
	}

	/**
	 * @return the bankDetails
	 */
	public BankDetails getBankDetails() {
		return bankDetails;
	}

	/**
	 * @param bankDetails the bankDetails to set
	 */
	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	/**
	 * @return the receiptInvoiceDetails
	 */
	public List<ReceiptInvoices> getReceiptInvoiceDetails() {
		return receiptInvoiceDetails;
	}

	/**
	 * @param receiptInvoiceDetails the receiptInvoiceDetails to set
	 */
	public void setReceiptInvoiceDetails(List<ReceiptInvoices> receiptInvoiceDetails) {
		this.receiptInvoiceDetails = receiptInvoiceDetails;
	}

	/**
	 * @return the receiptDeleted
	 */
	public int getReceiptDeleted() {
		return receiptDeleted;
	}

	/**
	 * @param receiptDeleted the receiptDeleted to set
	 */
	public void setReceiptDeleted(int receiptDeleted) {
		this.receiptDeleted = receiptDeleted;
	}

}
