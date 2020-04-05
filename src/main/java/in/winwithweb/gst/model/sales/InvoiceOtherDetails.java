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

	@Column(name = "invoice_party_name")
	private String invoicePartyName;

	@Column(name = "invoice_party_address")
	private String invoicePartyAddress;

	@Column(name = "invoice_party_date")
	private String invoicePartyDate;

	@Column(name = "invoice_party_gstin")
	private String invoicePartyGstin;

	@Column(name = "invoice_party_state")
	private String invoicePartyState;


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

	/**
	 * @return the invoicePar tyName
	 */
	public String getInvoicePartyName() {
		return invoicePartyName;
	}

	/**
	 * @param invoicePartyName the invoicePartyName to set
	 */
	public void setInvoicePartyName(String invoicePartyName) {
		this.invoicePartyName = invoicePartyName;
	}

	/**
	 * @return the invoicePartyAddress
	 */
	public String getInvoicePartyAddress() {
		return invoicePartyAddress;
	}

	/**
	 * @param invoicePartyAddress the invoicePartyAddress to set
	 */
	public void setInvoicePartyAddress(String invoicePartyAddress) {
		this.invoicePartyAddress = invoicePartyAddress;
	}

	/**
	 * @return the invoicePartyDate
	 */
	public String getInvoicePartyDate() {
		return invoicePartyDate;
	}

	/**
	 * @param invoicePartyDate the invoicePartyDate to set
	 */
	public void setInvoicePartyDate(String invoicePartyDate) {
		this.invoicePartyDate = invoicePartyDate;
	}

	/**
	 * @return the invoicePartyGstin
	 */
	public String getInvoicePartyGstin() {
		return invoicePartyGstin;
	}

	/**
	 * @param invoicePartyGstin the invoicePartyGstin to set
	 */
	public void setInvoicePartyGstin(String invoicePartyGstin) {
		this.invoicePartyGstin = invoicePartyGstin;
	}

	/**
	 * @return the invoicePartyState
	 */
	public String getInvoicePartyState() {
		return invoicePartyState;
	}

	/**
	 * @param invoicePartyState the invoicePartyState to set
	 */
	public void setInvoicePartyState(String invoicePartyState) {
		this.invoicePartyState = invoicePartyState;
	}

}