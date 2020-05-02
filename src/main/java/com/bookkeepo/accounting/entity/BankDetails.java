/**
 * 
 */
package com.bookkeepo.accounting.entity;

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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * @author Ashutosh Anand
 *
 */

@Data
@Entity
@Table(name = "user_bank_details")
public class BankDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_bank_id")
	private int id;

	@Column(name = "user_bank_account")
	private String userBankAccount;

	@Column(name = "user_bank_name")
	private String userBankName;

	@Column(name = "user_bank_nickname")
	private String userBankNickname;

	@Column(name = "user_bank_branch")
	private String userBankBranch;

	@Column(name = "user_bank_actype")
	private String userBankActype;

	@Column(name = "user_bank_creator")
	private String userBankCreator;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_bank_creation_date", nullable = false, updatable = false)
	private Date userBankCreationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_bank_last_modified", nullable = false)
	private Date userBankLastModified;

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
	 * @return the userBankAccount
	 */
	public String getUserBankAccount() {
		return userBankAccount;
	}

	/**
	 * @param userBankAccount the userBankAccount to set
	 */
	public void setUserBankAccount(String userBankAccount) {
		this.userBankAccount = userBankAccount;
	}

	/**
	 * @return the userBankName
	 */
	public String getUserBankName() {
		return userBankName;
	}

	/**
	 * @param userBankName the userBankName to set
	 */
	public void setUserBankName(String userBankName) {
		this.userBankName = userBankName;
	}

	/**
	 * @return the userBankNickname
	 */
	public String getUserBankNickname() {
		return userBankNickname;
	}

	/**
	 * @param userBankNickname the userBankNickname to set
	 */
	public void setUserBankNickname(String userBankNickname) {
		this.userBankNickname = userBankNickname;
	}

	/**
	 * @return the userBankBranch
	 */
	public String getUserBankBranch() {
		return userBankBranch;
	}

	/**
	 * @param userBankBranch the userBankBranch to set
	 */
	public void setUserBankBranch(String userBankBranch) {
		this.userBankBranch = userBankBranch;
	}

	/**
	 * @return the userBankActype
	 */
	public String getUserBankActype() {
		return userBankActype;
	}

	/**
	 * @param userBankActype the userBankActype to set
	 */
	public void setUserBankActype(String userBankActype) {
		this.userBankActype = userBankActype;
	}

	/**
	 * @return the userBankCreator
	 */
	public String getUserBankCreator() {
		return userBankCreator;
	}

	/**
	 * @param userBankCreator the userBankCreator to set
	 */
	public void setUserBankCreator(String userBankCreator) {
		this.userBankCreator = userBankCreator;
	}

	/**
	 * @return the userBankCreationDate
	 */
	public Date getUserBankCreationDate() {
		return userBankCreationDate;
	}

	/**
	 * @param userBankCreationDate the userBankCreationDate to set
	 */
	public void setUserBankCreationDate(Date userBankCreationDate) {
		this.userBankCreationDate = userBankCreationDate;
	}

	/**
	 * @return the userBankLastModified
	 */
	public Date getUserBankLastModified() {
		return userBankLastModified;
	}

	/**
	 * @param userBankLastModified the userBankLastModified to set
	 */
	public void setUserBankLastModified(Date userBankLastModified) {
		this.userBankLastModified = userBankLastModified;
	}
}
