/**
 * 
 */
package com.bookkeepo.accounting.model;

/**
 * @author Yash Singh
 *
 */
public class LedgerInfo {
	
	private String accountType;
	private String startDate;
	private String endDate;
	/**
	 * @return the account
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
