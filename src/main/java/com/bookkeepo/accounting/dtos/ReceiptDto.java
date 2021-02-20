/**
 * 
 */
package com.bookkeepo.accounting.dtos;

/**
 * @author Yash Singh
 *
 */
public class ReceiptDto {
	
	private int id;

	private int receiptNumber;

	private String receiptReference;

	private String receiptDate;

	private String receiptMode;

	private String receiptAmount;

	private String receiptDescription;

	private String accountName;
	
	

	public ReceiptDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReceiptDto(int id, int receiptNumber, String receiptReference, String receiptDate, String receiptMode,
			String receiptAmount, String receiptDescription, String accountName) {
		super();
		this.id = id;
		this.receiptNumber = receiptNumber;
		this.receiptReference = receiptReference;
		this.receiptDate = receiptDate;
		this.receiptMode = receiptMode;
		this.receiptAmount = receiptAmount;
		this.receiptDescription = receiptDescription;
		this.accountName = accountName;
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
	 * @return the receiptNumber
	 */
	public int getReceiptNumber() {
		return receiptNumber;
	}

	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(int receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	/**
	 * @return the receiptReference
	 */
	public String getReceiptReference() {
		return receiptReference;
	}

	/**
	 * @param receiptReference the receiptReference to set
	 */
	public void setReceiptReference(String receiptReference) {
		this.receiptReference = receiptReference;
	}

	/**
	 * @return the receiptDate
	 */
	public String getReceiptDate() {
		return receiptDate;
	}

	/**
	 * @param receiptDate the receiptDate to set
	 */
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	/**
	 * @return the receiptMode
	 */
	public String getReceiptMode() {
		return receiptMode;
	}

	/**
	 * @param receiptMode the receiptMode to set
	 */
	public void setReceiptMode(String receiptMode) {
		this.receiptMode = receiptMode;
	}

	/**
	 * @return the receiptAmount
	 */
	public String getReceiptAmount() {
		return receiptAmount;
	}

	/**
	 * @param receiptAmount the receiptAmount to set
	 */
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	/**
	 * @return the receiptDescription
	 */
	public String getReceiptDescription() {
		return receiptDescription;
	}

	/**
	 * @param receiptDescription the receiptDescription to set
	 */
	public void setReceiptDescription(String receiptDescription) {
		this.receiptDescription = receiptDescription;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	
	
}
