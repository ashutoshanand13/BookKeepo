package com.bookkeepo.accounting.model;

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

import org.hibernate.annotations.CreationTimestamp;

import com.bookkeepo.accounting.entity.Accounts;

import lombok.Data;

/**
* @author Ashutosh
*/
@Data
@Entity
@Table(name = "record_income_details")
public class RecordIncome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "record_income_id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recordincome_account_reference_no", updatable = false)
	private Accounts incomeWithAccountReference;
	
	@Column(name = "income_date")
	private String incomeDate;
	
	@Column(name = "income_type")
	private String incomeType;
	
	@Column(name = "income_amount")
	private String incomeAmount;
	
	@Column(name = "income_description")
	private String incomeDescription;
	
	@Column(name = "record_income_owner", nullable = false, updatable = false)
	private String recordIncomeOwner;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "record_income_creation_date", nullable = false, updatable = false)
	private Date recordIncomeCreationDate;
	/**
	 * @return the incomeDate
	 */
	public String getIncomeDate() {
		return incomeDate;
	}
	/**
	 * @param incomeDate the incomeDate to set
	 */
	public void setIncomeDate(String incomeDate) {
		this.incomeDate = incomeDate;
	}
	/**
	 * @return the incomeType
	 */
	public String getIncomeType() {
		return incomeType;
	}
	/**
	 * @param incomeType the incomeType to set
	 */
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	/**
	 * @return the incomeAmount
	 */
	public String getIncomeAmount() {
		return incomeAmount;
	}
	/**
	 * @param incomeAmount the incomeAmount to set
	 */
	public void setIncomeAmount(String incomeAmount) {
		this.incomeAmount = incomeAmount;
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
	 * @return the incomeWithAccountReference
	 */
	public Accounts getIncomeWithAccountReference() {
		return incomeWithAccountReference;
	}
	/**
	 * @param incomeWithAccountReference the incomeWithAccountReference to set
	 */
	public void setIncomeWithAccountReference(Accounts incomeWithAccountReference) {
		this.incomeWithAccountReference = incomeWithAccountReference;
	}
	/**
	 * @return the recordIncomeOwner
	 */
	public String getRecordIncomeOwner() {
		return recordIncomeOwner;
	}
	/**
	 * @param recordIncomeOwner the recordIncomeOwner to set
	 */
	public void setRecordIncomeOwner(String recordIncomeOwner) {
		this.recordIncomeOwner = recordIncomeOwner;
	}
	/**
	 * @return the recordIncomeCreationDate
	 */
	public Date getRecordIncomeCreationDate() {
		return recordIncomeCreationDate;
	}
	/**
	 * @param recordIncomeCreationDate the recordIncomeCreationDate to set
	 */
	public void setRecordIncomeCreationDate(Date recordIncomeCreationDate) {
		this.recordIncomeCreationDate = recordIncomeCreationDate;
	}
	/**
	 * @return the incomeDescription
	 */
	public String getIncomeDescription() {
		return incomeDescription;
	}
	/**
	 * @param incomeDescription the incomeDescription to set
	 */
	public void setIncomeDescription(String incomeDescription) {
		this.incomeDescription = incomeDescription;
	}
	
	

}
