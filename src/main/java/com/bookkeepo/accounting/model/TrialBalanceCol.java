/**
 * 
 */
package com.bookkeepo.accounting.model;

/**
 * @author Yash Singh
 *
 */
public class TrialBalanceCol {

	String debitSum;
	
	String creditSum;
	
	String balance;

	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}

	/**
	 * @return the creditSum
	 */
	public String getCreditSum() {
		return creditSum;
	}

	/**
	 * @param creditSum the creditSum to set
	 */
	public void setCreditSum(String creditSum) {
		this.creditSum = creditSum;
	}

	/**
	 * @return the debitSum
	 */
	public String getDebitSum() {
		return debitSum;
	}

	/**
	 * @param debitSum the debitSum to set
	 */
	public void setDebitSum(String debitSum) {
		this.debitSum = debitSum;
	}
	
	
}
