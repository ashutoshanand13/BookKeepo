/**
 * 
 */
package in.winwithweb.gst.model;

/**
 * @author sachingoyal
 *
 */
public enum DocumentType {

	INTRASTATE("Intra State", "Intra State"), INTERSTATE("Inter State", "Inter State"),
	BOTH("Both", "Intra State/Inter State");

	private final String type;
	private final String value;

	DocumentType(String type, String value) {
		this.type = type;
		this.value = value;

	}

	public String getDocumentType() {
		return this.type;
	}

	public String getDocumentValue() {
		return this.value;
	}
}
