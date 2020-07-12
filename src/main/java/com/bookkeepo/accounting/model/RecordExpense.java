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
@Table(name = "record_expense_details")
public class RecordExpense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "record_expense_id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recordexpense_account_reference_no", updatable = false)
	private Accounts expenseWithAccountReference;
	
	@Column(name = "expense_date")
	private String expenseDate;
	
	@Column(name = "expense_description")
	private String expenseDescription;
	
	@Column(name = "expense_amount")
	private String expenseAmount;
	
	@Column(name = "record_expense_owner", nullable = false, updatable = false)
	private String recordExpenseOwner;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "record_expense_creation_date", nullable = false, updatable = false)
	private Date recordExpenseCreationDate;
	/**
	 * @return the expenseDate
	 */
	public String getExpenseDate() {
		return expenseDate;
	}
	/**
	 * @param expenseDate the expenseDate to set
	 */
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}
	/**
	 * @return the expenseDescription
	 */
	public String getExpenseDescription() {
		return expenseDescription;
	}
	/**
	 * @param expenseDescription the expenseDescription to set
	 */
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	/**
	 * @return the expenseAmount
	 */
	public String getExpenseAmount() {
		return expenseAmount;
	}
	/**
	 * @param expenseAmount the expenseAmount to set
	 */
	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
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
	 * @return the expenseWithAccountReference
	 */
	public Accounts getExpenseWithAccountReference() {
		return expenseWithAccountReference;
	}
	/**
	 * @param expenseWithAccountReference the expenseWithAccountReference to set
	 */
	public void setExpenseWithAccountReference(Accounts expenseWithAccountReference) {
		this.expenseWithAccountReference = expenseWithAccountReference;
	}
	/**
	 * @return the recordExpenseOwner
	 */
	public String getRecordExpenseOwner() {
		return recordExpenseOwner;
	}
	/**
	 * @param recordExpenseOwner the recordExpenseOwner to set
	 */
	public void setRecordExpenseOwner(String recordExpenseOwner) {
		this.recordExpenseOwner = recordExpenseOwner;
	}
	/**
	 * @return the recordExpenseCreationDate
	 */
	public Date getRecordExpenseCreationDate() {
		return recordExpenseCreationDate;
	}
	/**
	 * @param recordExpenseCreationDate the recordExpenseCreationDate to set
	 */
	public void setRecordExpenseCreationDate(Date recordExpenseCreationDate) {
		this.recordExpenseCreationDate = recordExpenseCreationDate;
	}
	
	

}
