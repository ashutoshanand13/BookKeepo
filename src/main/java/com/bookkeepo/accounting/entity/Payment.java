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
@Table(name = "payment_details")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private int id;

	@Column(name = "payment_number", updatable = false)
	private int paymentNumber;

	@Column(name = "payment_reference")
	@NotEmpty(message = "*Please provide the payment reference")
	private String paymentReference;

	@Column(name = "payment_date")
	@NotEmpty(message = "*Please provide the payment date")
	private String paymentDate;

	@Column(name = "payment_mode")
	@NotEmpty(message = "*Please provide the payment mode")
	private String paymentMode;

	@Column(name = "payment_amount")
	@NotEmpty(message = "*Please provide the Payment amount")
	private String paymentAmount;

	@Column(name = "payment_description")
	private String paymentDescription;

	@Column(name = "payment_owner", nullable = false, updatable = false)
	private String paymentOwner;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_creation_date", nullable = false, updatable = false)
	private Date paymentCreationDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_account_reference_no")
	private Accounts accountRefNo;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "payment_company_reference_no", updatable = false)
	private Company paymentCompanyDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_bank_reference_no", updatable = false)
	private BankDetails bankDetails;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_invoice_reference_no")
	private List<PaymentInvoices> paymentInvoiceDetails;
	
	@Column(name = "payment_deleted", nullable = false)
	private int paymentDeleted;

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
	 * @return the paymentNumber
	 */
	public int getPaymentNumber() {
		return paymentNumber;
	}

	/**
	 * @param paymentNumber the paymentNumber to set
	 */
	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	/**
	 * @return the paymentReference
	 */
	public String getPaymentReference() {
		return paymentReference;
	}

	/**
	 * @param paymentReference the paymentReference to set
	 */
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the paymentDescription
	 */
	public String getPaymentDescription() {
		return paymentDescription;
	}

	/**
	 * @param paymentDescription the paymentDescription to set
	 */
	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	/**
	 * @return the paymentAmount
	 */
	public String getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the paymentOwner
	 */
	public String getPaymentOwner() {
		return paymentOwner;
	}

	/**
	 * @param paymentOwner the paymentOwner to set
	 */
	public void setPaymentOwner(String paymentOwner) {
		this.paymentOwner = paymentOwner;
	}

	/**
	 * @return the paymentCreationDate
	 */
	public Date getPaymentCreationDate() {
		return paymentCreationDate;
	}

	/**
	 * @param paymentCreationDate the paymentCreationDate to set
	 */
	public void setPaymentCreationDate(Date paymentCreationDate) {
		this.paymentCreationDate = paymentCreationDate;
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
	 * @return the paymentCompanyDetails
	 */
	public Company getPaymentCompanyDetails() {
		return paymentCompanyDetails;
	}

	/**
	 * @param paymentCompanyDetails the paymentCompanyDetails to set
	 */
	public void setPaymentCompanyDetails(Company paymentCompanyDetails) {
		this.paymentCompanyDetails = paymentCompanyDetails;
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
	 * @return the paymentInvoiceDetails
	 */
	public List<PaymentInvoices> getPaymentInvoiceDetails() {
		return paymentInvoiceDetails;
	}

	/**
	 * @param paymentInvoiceDetails the paymentInvoiceDetails to set
	 */
	public void setPaymentInvoiceDetails(List<PaymentInvoices> paymentInvoiceDetails) {
		this.paymentInvoiceDetails = paymentInvoiceDetails;
	}

	/**
	 * @return the paymentDeleted
	 */
	public int getPaymentDeleted() {
		return paymentDeleted;
	}

	/**
	 * @param paymentDeleted the paymentDeleted to set
	 */
	public void setPaymentDeleted(int paymentDeleted) {
		this.paymentDeleted = paymentDeleted;
	}

}
