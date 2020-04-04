/**
 * 
 */
package in.winwithweb.gst.model;

import in.winwithweb.gst.util.Constants;

/**
 * @author sachingoyal
 *
 */
public enum Type {

	Tax_Invoice("Tax Invoice", Constants.INVOICE_COLUMNS), Export_Invoice("Export Invoice", Constants.INVOICE_COLUMNS),
	Credit_Note("Credit Note", Constants.CREDIT_DEBIT_NOTE_COLUMNS),
	Debit_Note("Debit Note", Constants.CREDIT_DEBIT_NOTE_COLUMNS),
	Purchase_Invoice("Purchase Invoice", Constants.INVOICE_COLUMNS),
	Purchase_Order("Purchase Order", Constants.INVOICE_COLUMNS),
	Sales_Invoice("Sales Invoice", Constants.INVOICE_COLUMNS);

	private final String type;

	private String[] colums;

	Type(String type, String[] colums) {
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
