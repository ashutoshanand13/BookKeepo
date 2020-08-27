/**
 * 
 */
package com.bookkeepo.accounting.model;

/**
 * @author Yash Singh
 *
 */
public class LedgerInfo {
	
	private String accountId;
	private String startDate;
	private String endDate;
	/**
	 * @return the account
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

}
