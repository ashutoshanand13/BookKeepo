/**
 * 
 */
package com.bookkeepo.accounting.model;

/**
 * @author sachingoyal
 *
 */
public class Reports {

	private InvoiceType invoiceType;
	private InvoiceSubType invoiceSubType;
	private String startDate;
	private String endDate;

	/**
	 * @return the invoiceType
	 */
	public InvoiceType getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the invoiceSubType
	 */
	public InvoiceSubType getInvoiceSubType() {
		return invoiceSubType;
	}

	/**
	 * @param invoiceSubType the invoiceSubType to set
	 */
	public void setInvoiceSubType(InvoiceSubType invoiceSubType) {
		this.invoiceSubType = invoiceSubType;
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
