/**
 * 
 */
package com.bookkeepo.accounting.dtos;

/**
 * @author Yash Singh
 *
 */
public class PaymentDto {

	private int id;

	private int paymentNumber;

	private String paymentReference;

	private String paymentDate;

	private String paymentMode;

	private String paymentAmount;

	private String paymentDescription;

	private String accountName;

	public PaymentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDto(int id, int paymentNumber, String paymentReference, String paymentDate, String paymentMode,
			String paymentAmount, String paymentDescription, String accountName) {
		super();
		this.id = id;
		this.paymentNumber = paymentNumber;
		this.paymentReference = paymentReference;
		this.paymentDate = paymentDate;
		this.paymentMode = paymentMode;
		this.paymentAmount = paymentAmount;
		this.paymentDescription = paymentDescription;
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
	 * @return the paymentNumber
	 */
	public int getPaymentNumber() {
		return paymentNumber;
	}

	/**
	 * @param paymentNumber the paymentNumber to set
	 */
	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	/**
	 * @return the paymentReference
	 */
	public String getPaymentReference() {
		return paymentReference;
	}

	/**
	 * @param paymentReference the paymentReference to set
	 */
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the paymentAmount
	 */
	public String getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the paymentDescription
	 */
	public String getPaymentDescription() {
		return paymentDescription;
	}

	/**
	 * @param paymentDescription the paymentDescription to set
	 */
	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
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
