package com.bookkeepo.accounting.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "company_details")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_description")
	private String companyDesc;

	@Column(name = "company_active", nullable = false)
	private int companyActive;

	@Column(name = "company_Deleted", nullable = false)
	private int companyDeleted;

	@Column(name = "company_gstin")
	private String companyGstin;

	@Column(name = "company_state")
	private String companyState;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "company_email")
	private String companyEmail;

	@Column(name = "company_telephone")
	private String companyTelephone;

	@Column(name = "company_bank_ac")
	private String companyBankAC;

	@Column(name = "company_bank_ifsc")
	private String companyBankIFSC;

	@Column(name = "company_bank_tnc")
	private String companyBankTNC;

	@Column(name = "company_key", nullable = false)
	private String companyUniqueKey;

	@Lob
	@Column(name = "company_logo", length = 2097152)
	private byte[] companyLogo;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "company_creation_date", nullable = false, updatable = false)
	private Date companyCreationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "company_last_modified", nullable = false)
	private Date companyLastModified;

	@Transient
	private String pageName;

	@Transient
	private String companyStringLogo;

	public Company() {

	}

	public Company(String userName, String companyStringLogo) {
		this.userName = userName;
		this.companyStringLogo = companyStringLogo;

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

	public Company(String pageName) {
		this.pageName = pageName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the companyDesc
	 */
	public String getCompanyDesc() {
		return companyDesc;
	}

	/**
	 * @param companyDesc the companyDesc to set
	 */
	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	/**
	 * @return the companyActive
	 */
	public int getCompanyActive() {
		return companyActive;
	}

	/**
	 * @param companyActive the companyActive to set
	 */
	public void setCompanyActive(int companyActive) {
		this.companyActive = companyActive;
	}

	/**
	 * @return the companyDeleted
	 */
	public int getCompanyDeleted() {
		return companyDeleted;
	}

	/**
	 * @param companyDeleted the companyDeleted to set
	 */
	public void setCompanyDeleted(int companyDeleted) {
		this.companyDeleted = companyDeleted;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyGstin
	 */
	public String getCompanyGstin() {
		return companyGstin;
	}

	/**
	 * @param companyGstin the companyGstin to set
	 */
	public void setCompanyGstin(String companyGstin) {
		this.companyGstin = companyGstin;
	}

	/**
	 * @return the companyState
	 */
	public String getCompanyState() {
		return companyState;
	}

	/**
	 * @param companyState the companyState to set
	 */
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}

	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}

	/**
	 * @param companyEmail the companyEmail to set
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	/**
	 * @return the companyTelephone
	 */
	public String getCompanyTelephone() {
		return companyTelephone;
	}

	/**
	 * @param companyTelephone the companyTelephone to set
	 */
	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}

	/**
	 * @return the companyLogo
	 */
	public byte[] getCompanyLogo() {
		return companyLogo;
	}

	/**
	 * @param companyLogo the companyLogo to set
	 */
	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}

	/**
	 * @return the companyBankAC
	 */
	public String getCompanyBankAC() {
		return companyBankAC;
	}

	/**
	 * @param companyBankAC the companyBankAC to set
	 */
	public void setCompanyBankAC(String companyBankAC) {
		this.companyBankAC = companyBankAC;
	}

	/**
	 * @return the companyBankIFSC
	 */
	public String getCompanyBankIFSC() {
		return companyBankIFSC;
	}

	/**
	 * @param companyBankIFSC the companyBankIFSC to set
	 */
	public void setCompanyBankIFSC(String companyBankIFSC) {
		this.companyBankIFSC = companyBankIFSC;
	}

	/**
	 * @return the companyBankTNC
	 */
	public String getCompanyBankTNC() {
		return companyBankTNC;
	}

	/**
	 * @param companyBankTNC the companyBankTNC to set
	 */
	public void setCompanyBankTNC(String companyBankTNC) {
		this.companyBankTNC = companyBankTNC;
	}

	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return the companyStringLogo
	 */
	public String getCompanyStringLogo() {
		return companyStringLogo;
	}

	/**
	 * @param companyStringLogo the companyStringLogo to set
	 */
	public void setCompanyStringLogo(String companyStringLogo) {
		this.companyStringLogo = companyStringLogo;
	}

	/**
	 * @return the companyCreationDate
	 */
	public Date getCompanyCreationDate() {
		return companyCreationDate;
	}

	/**
	 * @param companyCreationDate the companyCreationDate to set
	 */
	public void setCompanyCreationDate(Date companyCreationDate) {
		this.companyCreationDate = companyCreationDate;
	}

	/**
	 * @return the companyLastModified
	 */
	public Date getCompanyLastModified() {
		return companyLastModified;
	}

	/**
	 * @param companyLastModified the companyLastModified to set
	 */
	public void setCompanyLastModified(Date companyLastModified) {
		this.companyLastModified = companyLastModified;
	}

	/**
	 * @return the companyUniqueKey
	 */
	public String getCompanyUniqueKey() {
		return companyUniqueKey;
	}

	/**
	 * @param companyUniqueKey the companyUniqueKey to set
	 */
	public void setCompanyUniqueKey(String companyUniqueKey) {
		this.companyUniqueKey = companyUniqueKey;
	}

}
