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

	Tax_Invoice("Tax Invoice", Constants.TAX_INVOICE_COLUMNS),
	Export_Invoice("Export Invoice", Constants.SALES_INVOICE_COLUMNS),
	Credit_Note("Credit Note", Constants.DEBIT_NOTE_COLUMNS), Debit_Note("Debit Note", Constants.DEBIT_NOTE_COLUMNS),
	Purchase_Invoice("Purchase Invoice", Constants.DEBIT_NOTE_COLUMNS),
	Purchase_Order("Purchase Order", Constants.DEBIT_NOTE_COLUMNS),
	Sales_Invoice("Sales Invoice", Constants.SALES_INVOICE_COLUMNS);

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
