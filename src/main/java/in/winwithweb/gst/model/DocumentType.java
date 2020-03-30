/**
 * 
 */
package in.winwithweb.gst.model;

/**
 * @author sachingoyal
 *
 */
public enum DocumentType {

	INTRASTATE("Intra State"), INTERSTATE("Inter State"), BOTH("Both");

	private final String type;

	DocumentType(String type) {
		this.type = type;
	}

	public String getDocumentType() {
		return this.type;
	}

}
