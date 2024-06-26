package com.bookkeepo.accounting.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "account_details")
public class Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private int id;

	@Column(name = "account_name")
	@NotEmpty(message = "*Please provide the account name")
	private String accountName;

	@Column(name = "account_owner", nullable = false, updatable = false)
	private String accountOwner;

	@Column(name = "account_type")
	@NotEmpty(message = "*Please provide the account type")
	private String accountType;

	@Column(name = "account_gstin")
	private String gstin;

	@Column(name = "account_pan")
	private String accountPan;

	@Column(name = "account_contact")
	private String accountContact;

	@Column(name = "account_address")
	private String accountAddress;

	@Column(name = "account_state")
	private String accountState;

	@Column(name = "account_pincode")
	private String accountPincode;
	
	@Column(name = "opening_balance_type")
	private String openingBalanceType;
	
	@Column(name = "opening_balance_ammount")
	private String openingBalanceAmount;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "account_creation_date", nullable = false, updatable = false)
	private Date accountCreationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "account_last_modified", nullable = false)
	private Date accountLastModified;

	@Column(name = "account_email")
	@Email(message = "*Please provide a valid Email")
	private String accountEmail;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "account_company_reference_no", updatable = false)
	private Company accountCompanyDetails;

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
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}

	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	/**
	 * @return the accountPan
	 */
	public String getAccountPan() {
		return accountPan;
	}

	/**
	 * @param accountPan the accountPan to set
	 */
	public void setAccountPan(String accountPan) {
		this.accountPan = accountPan;
	}

	/**
	 * @return the accountContact
	 */
	public String getAccountContact() {
		return accountContact;
	}

	/**
	 * @param accountContact the accountContact to set
	 */
	public void setAccountContact(String accountContact) {
		this.accountContact = accountContact;
	}

	/**
	 * @return the accountAddress
	 */
	public String getAccountAddress() {
		return accountAddress;
	}

	/**
	 * @param accountAddress the accountAddress to set
	 */
	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	/**
	 * @return the accountState
	 */
	public String getAccountState() {
		return accountState;
	}

	/**
	 * @param accountState the accountState to set
	 */
	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	/**
	 * @return the accountPincode
	 */
	public String getAccountPincode() {
		return accountPincode;
	}

	/**
	 * @param accountPincode the accountPincode to set
	 */
	public void setAccountPincode(String accountPincode) {
		this.accountPincode = accountPincode;
	}

	/**
	 * @return the accountEmail
	 */
	public String getAccountEmail() {
		return accountEmail;
	}

	/**
	 * @param accountEmail the accountEmail to set
	 */
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	/**
	 * @return the accountOwner
	 */
	public String getAccountOwner() {
		return accountOwner;
	}

	/**
	 * @param accountOwner the accountOwner to set
	 */
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	/**
	 * @return the accountCreationDate
	 */
	public Date getAccountCreationDate() {
		return accountCreationDate;
	}

	/**
	 * @param accountCreationDate the accountCreationDate to set
	 */
	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

	/**
	 * @return the accountLastModified
	 */
	public Date getAccountLastModified() {
		return accountLastModified;
	}

	/**
	 * @param accountLastModified the accountLastModified to set
	 */
	public void setAccountLastModified(Date accountLastModified) {
		this.accountLastModified = accountLastModified;
	}

	/**
	 * @return the accountCompanyDetails
	 */
	public Company getAccountCompanyDetails() {
		return accountCompanyDetails;
	}

	/**
	 * @param accountCompanyDetails the accountCompanyDetails to set
	 */
	public void setAccountCompanyDetails(Company accountCompanyDetails) {
		this.accountCompanyDetails = accountCompanyDetails;
	}

	/**
	 * @return the openingBalanceType
	 */
	public String getOpeningBalanceType() {
		return openingBalanceType;
	}

	/**
	 * @param openingBalanceType the openingBalanceType to set
	 */
	public void setOpeningBalanceType(String openingBalanceType) {
		this.openingBalanceType = openingBalanceType;
	}

	/**
	 * @return the openingBalanceAmount
	 */
	public String getOpeningBalanceAmount() {
		return openingBalanceAmount;
	}

	/**
	 * @param openingBalanceAmount the openingBalanceAmount to set
	 */
	public void setOpeningBalanceAmount(String openingBalanceAmount) {
		this.openingBalanceAmount = openingBalanceAmount;
	}

}
