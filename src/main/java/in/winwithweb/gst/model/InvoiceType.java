/**
 * 
 */
package in.winwithweb.gst.model;

import in.winwithweb.gst.util.Constants;

/**
 * @author sachingoyal
 *
 */
public enum InvoiceType {

	Tax_Invoice("Tax Invoice", Constants.SUPPLY_OUTWARDS_REPORTS), Export_Invoice("Export Invoice", Constants.SUPPLY_OUTWARDS_REPORTS),
	Credit_Note("Credit Note", Constants.CREDIT_DEBIT_NOTE_COLUMNS),
	Debit_Note("Debit Note", Constants.CREDIT_DEBIT_NOTE_COLUMNS),
	Purchase_Invoice("Purchase Invoice", Constants.SUPPLY_INWARDS_REPORTS),
	Purchase_Order("Purchase Order", Constants.SUPPLY_INWARDS_REPORTS);

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
