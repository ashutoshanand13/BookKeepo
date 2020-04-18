/**
 * 
 */
package in.winwithweb.gst.model.sales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author sachingoyal
 *
 */

@Data
@Entity
@Table(name = "invoice_other_details")
public class InvoiceOtherDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_other_details_id")
	private int id;

	@Column(name = "linked_invoice")
	private String linkedInvoice;

	@Column(name = "linked_invoice_date")
	private String linkedInvoiceDate;

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
	 * @return the linkedInvoice
	 */
	public String getLinkedInvoice() {
		return linkedInvoice;
	}

	/**
	 * @param linkedInvoice the linkedInvoice to set
	 */
	public void setLinkedInvoice(String linkedInvoice) {
		this.linkedInvoice = linkedInvoice;
	}

	/**
	 * @return the linkedInvoiceDate
	 */
	public String getLinkedInvoiceDate() {
		return linkedInvoiceDate;
	}

	/**
	 * @param linkedInvoiceDate the linkedInvoiceDate to set
	 */
	public void setLinkedInvoiceDate(String linkedInvoiceDate) {
		this.linkedInvoiceDate = linkedInvoiceDate;
	}

}
