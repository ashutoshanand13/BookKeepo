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
@Table(name = "account_ledger")
public class AccountLedger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_ledger_id")
	private int id;

	@Column(name = "account_ledger_type")
	private String accountType;

	@Column(name = "account_ledger_use")
	private String accountUse;

	@Column(name = "account_ledger_postive")
	private String accountpostive;

	@Column(name = "account_ledger_negative")
	private String accountNegative;

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
	 * @return the accountUse
	 */
	public String getAccountUse() {
		return accountUse;
	}

	/**
	 * @param accountUse the accountUse to set
	 */
	public void setAccountUse(String accountUse) {
		this.accountUse = accountUse;
	}

	/**
	 * @return the accountpostive
	 */
	public String getAccountpostive() {
		return accountpostive;
	}

	/**
	 * @param accountpostive the accountpostive to set
	 */
	public void setAccountpostive(String accountpostive) {
		this.accountpostive = accountpostive;
	}

	/**
	 * @return the accountNegative
	 */
	public String getAccountNegative() {
		return accountNegative;
	}

	/**
	 * @param accountNegative the accountNegative to set
	 */
	public void setAccountNegative(String accountNegative) {
		this.accountNegative = accountNegative;
	}

}
