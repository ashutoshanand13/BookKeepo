/**
 * 
 */
package com.bookkeepo.accounting.model;

import com.bookkeepo.accounting.util.Constants;

/**
 * @author sachingoyal
 *
 */
public enum InvoiceType {

	Tax_Invoice("Tax Invoice", Constants.SUPPLY_OUTWARDS_REPORTS),
	Export_Invoice("Export Invoice", Constants.SUPPLY_OUTWARDS_REPORTS),
	Credit_Note("Credit Note", Constants.CREDIT_DEBIT_NOTE_COLUMNS),
	Debit_Note("Debit Note", Constants.CREDIT_DEBIT_NOTE_COLUMNS),
	Purchase_Invoice("Purchase Invoice", Constants.SUPPLY_INWARDS_REPORTS),
	Purchase_Order("Purchase Order", Constants.SUPPLY_INWARDS_REPORTS),
	Bill_Supply("Bill of Supply", Constants.BILL_OF_SUPPLY);

	private final String type;

	private String[] colums;

	InvoiceType(String type, String[] colums) {
		this.type = type;
		this.colums = colums;
	}

	public String getType() {
		return this.type;
	}

	public String[] getColums() {
		return this.colums;
	}

}
