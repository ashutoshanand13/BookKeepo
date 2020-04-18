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
@Table(name = "invoice_address_details")
public class InvoiceAddressDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_address_id")
	private int id;
	
	@Column(name = "invoice_biller_name")
	private String invoiceBillerName;
	
	@Column(name = "invoice_biller_address_name")
	private String invoiceBillerAddressName;
	
	@Column(name = "invoice_biller_gstin")
	private String invoiceBillerGst;
	
	@Column(name = "invoice_biller_state")
	private String invoiceBillerState;
	
	
	@Column(name = "invoice_party_name")
	private String invoicePartyName;
	
	@Column(name = "invoice_party_address_name")
	private String invoicePartyAddressName;
	
	
	@Column(name = "invoice_party_gstin")
	private String invoicePartyGst;
	
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
	 * @return the invoiceBillerName
	 */
	public String getInvoiceBillerName() {
		return invoiceBillerName;
	}

	/**
	 * @param invoiceBillerName the invoiceBillerName to set
	 */
	public void setInvoiceBillerName(String invoiceBillerName) {
		this.invoiceBillerName = invoiceBillerName;
	}

	/**
	 * @return the invoiceBillerAddressName
	 */
	public String getInvoiceBillerAddressName() {
		return invoiceBillerAddressName;
	}

	/**
	 * @param invoiceBillerAddressName the invoiceBillerAddressName to set
	 */
	public void setInvoiceBillerAddressName(String invoiceBillerAddressName) {
		this.invoiceBillerAddressName = invoiceBillerAddressName;
	}

	/**
	 * @return the invoiceBillerGst
	 */
	public String getInvoiceBillerGst() {
		return invoiceBillerGst;
	}

	/**
	 * @param invoiceBillerGst the invoiceBillerGst to set
	 */
	public void setInvoiceBillerGst(String invoiceBillerGst) {
		this.invoiceBillerGst = invoiceBillerGst;
	}

	/**
	 * @return the invoiceBillerState
	 */
	public String getInvoiceBillerState() {
		return invoiceBillerState;
	}

	/**
	 * @param invoiceBillerState the invoiceBillerState to set
	 */
	public void setInvoiceBillerState(String invoiceBillerState) {
		this.invoiceBillerState = invoiceBillerState;
	}

	/**
	 * @return the invoicePartyName
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
	 * @return the invoicePartyAddressName
	 */
	public String getInvoicePartyAddressName() {
		return invoicePartyAddressName;
	}

	/**
	 * @param invoicePartyAddressName the invoicePartyAddressName to set
	 */
	public void setInvoicePartyAddressName(String invoicePartyAddressName) {
		this.invoicePartyAddressName = invoicePartyAddressName;
	}

	/**
	 * @return the invoicePartyGst
	 */
	public String getInvoicePartyGst() {
		return invoicePartyGst;
	}

	/**
	 * @param invoicePartyGst the invoicePartyGst to set
	 */
	public void setInvoicePartyGst(String invoicePartyGst) {
		this.invoicePartyGst = invoicePartyGst;
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
