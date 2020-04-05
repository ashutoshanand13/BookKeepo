/**
 * 
 */
package in.winwithweb.gst.model;

/**
 * @author sachingoyal
 *
 */
public enum InvoiceSubType {

	INTRASTATE("Intra State", "Intra State"), INTERSTATE("Inter State", "Inter State"),
	BOTH("Both", "Intra State/Inter State");

	private final String type;
	private final String value;

	InvoiceSubType(String type, String value) {
		this.type = type;
		this.value = value;

	}

	public String getInvoiceSubType() {
		return this.type;
	}

	public String getInvoiceSubTypeValue() {
		return this.value;
	}
}
