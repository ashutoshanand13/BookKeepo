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
	private String userbankName;
	
	@Column(name = "user_bank_nickname")
	private String userbankNickname;
	
	@Column(name = "user_bank_branch")
	private String userbankBranch;
	
	@Column(name = "user_bank_actype")
	private String userbankActype;
	
	@Column(name = "user_bank_creator")
	private String userbankCreator;

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
	 * @return the userbankName
	 */
	public String getUserbankName() {
		return userbankName;
	}

	/**
	 * @param userbankName the userbankName to set
	 */
	public void setUserbankName(String userbankName) {
		this.userbankName = userbankName;
	}

	/**
	 * @return the userbankNickname
	 */
	public String getUserbankNickname() {
		return userbankNickname;
	}

	/**
	 * @param userbankNickname the userbankNickname to set
	 */
	public void setUserbankNickname(String userbankNickname) {
		this.userbankNickname = userbankNickname;
	}

	/**
	 * @return the userbankBranch
	 */
	public String getUserbankBranch() {
		return userbankBranch;
	}

	/**
	 * @param userbankBranch the userbankBranch to set
	 */
	public void setUserbankBranch(String userbankBranch) {
		this.userbankBranch = userbankBranch;
	}

	/**
	 * @return the userbankActype
	 */
	public String getUserbankActype() {
		return userbankActype;
	}

	/**
	 * @param userbankActype the userbankActype to set
	 */
	public void setUserbankActype(String userbankActype) {
		this.userbankActype = userbankActype;
	}

	/**
	 * @return the userbankCreator
	 */
	public String getUserbankCreator() {
		return userbankCreator;
	}

	/**
	 * @param userbankCreator the userbankCreator to set
	 */
	public void setUserbankCreator(String userbankCreator) {
		this.userbankCreator = userbankCreator;
	}
	
	

	
	
}
