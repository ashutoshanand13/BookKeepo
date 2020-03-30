/**
 * 
 */
package in.winwithweb.gst.model;

/**
 * @author sachingoyal
 *
 */
public enum Type {

	Tax_Invoice("Tax Invoice"), Export_Invoice("Export Invoice"), Credit_Note("Credit Note"), Debit_Note("Debit Note"),
	Purchase_Invoice("Purchase Invoice"), Purchase_Order("Purchase Order"), Sales_Invoice("Sales Invoice");

	private final String type;

	Type(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
}
