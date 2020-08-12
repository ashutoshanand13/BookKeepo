package com.bookkeepo.accounting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
* @author Ashutosh Anand
*/
@Data
@Entity
@Table(name = "record_contra_cash_details")
public class RecordContraCash {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "record_contracash_id")
	private int id;
	
	@Column(name = "payFrom")
	private String payFrom;
	
	@Column(name = "payFromBankId")
	private String payFromBankId;
	
	@Column(name = "payFromBank")
	private String payFromBank;
	
	@Column(name = "payTo")
	private String payTo;
	
	@Column(name = "payToBankId")
	private String payToBankId;
	
	@Column(name = "payToBank")
	private String payToBank;
	
	@Column(name = "record_contra_amount")
	private String recordContraAmount;
	
	@Column(name = "record_contra_date")
	private String recordContraDate;
	
	@Column(name = "record_contra_description")
	private String recordContraDescription;
	
	@Column(name = "record_contra_owner", nullable = false, updatable = false)
	private String recordContraOwner;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "record_contra_creation_date", nullable = false, updatable = false)
	private Date recordContraCreationDate;
	/**
	 * @return the payFrom
	 */
	public String getPayFrom() {
		return payFrom;
	}
	/**
	 * @param payFrom the payFrom to set
	 */
	public void setPayFrom(String payFrom) {
		this.payFrom = payFrom;
	}
	/**
	 * @return the payTo
	 */
	public String getPayTo() {
		return payTo;
	}
	/**
	 * @param payTo the payTo to set
	 */
	public void setPayTo(String payTo) {
		this.payTo = payTo;
	}
	/**
	 * @return the recordContraAmount
	 */
	public String getRecordContraAmount() {
		return recordContraAmount;
	}
	/**
	 * @param recordContraAmount the recordContraAmount to set
	 */
	public void setRecordContraAmount(String recordContraAmount) {
		this.recordContraAmount = recordContraAmount;
	}
	/**
	 * @return the recordContraDate
	 */
	public String getRecordContraDate() {
		return recordContraDate;
	}
	/**
	 * @param recordContraDate the recordContraDate to set
	 */
	public void setRecordContraDate(String recordContraDate) {
		this.recordContraDate = recordContraDate;
	}
	/**
	 * @return the recordContraDescription
	 */
	public String getRecordContraDescription() {
		return recordContraDescription;
	}
	/**
	 * @param recordContraDescription the recordContraDescription to set
	 */
	public void setRecordContraDescription(String recordContraDescription) {
		this.recordContraDescription = recordContraDescription;
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
	 * @return the recordContraOwner
	 */
	public String getRecordContraOwner() {
		return recordContraOwner;
	}
	/**
	 * @param recordContraOwner the recordContraOwner to set
	 */
	public void setRecordContraOwner(String recordContraOwner) {
		this.recordContraOwner = recordContraOwner;
	}
	/**
	 * @return the recordContraCreationDate
	 */
	public Date getRecordContraCreationDate() {
		return recordContraCreationDate;
	}
	/**
	 * @param recordContraCreationDate the recordContraCreationDate to set
	 */
	public void setRecordContraCreationDate(Date recordContraCreationDate) {
		this.recordContraCreationDate = recordContraCreationDate;
	}
	/**
	 * @return the payFromBank
	 */
	public String getPayFromBank() {
		return payFromBank;
	}
	/**
	 * @param payFromBank the payFromBank to set
	 */
	public void setPayFromBank(String payFromBank) {
		this.payFromBank = payFromBank;
	}
	/**
	 * @return the payToBank
	 */
	public String getPayToBank() {
		return payToBank;
	}
	/**
	 * @param payToBank the payToBank to set
	 */
	public void setPayToBank(String payToBank) {
		this.payToBank = payToBank;
	}
	/**
	 * @return the payFromBankId
	 */
	public String getPayFromBankId() {
		return payFromBankId;
	}
	/**
	 * @param payFromBankId the payFromBankId to set
	 */
	public void setPayFromBankId(String payFromBankId) {
		this.payFromBankId = payFromBankId;
	}
	/**
	 * @return the payToBankId
	 */
	public String getPayToBankId() {
		return payToBankId;
	}
	/**
	 * @param payToBankId the payToBankId to set
	 */
	public void setPayToBankId(String payToBankId) {
		this.payToBankId = payToBankId;
	}

	
}
