/**
 * 
 */
package in.winwithweb.gst.model.sales;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import in.winwithweb.gst.model.Company;
import lombok.Data;

/**
 * @author sachingoyal
 *
 */
@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_id")
	private int id;

	@Column(name = "invoice_number")
	private String invoiceNumber;

	@Column(name = "invoice_date")
	private String invoiceDate;

	@Column(name = "invoice_creation_date")
	private Date invoiceCreationDate;

	@Column(name = "invoice_owner")
	private String invoiceOwner;

	@Column(name = "invoice_type")
	private String invoiceType;

	@Column(name = "invoice_sub_type")
	private String invoiceSubType;

	@Column(name = "invoice_dos")
	private String invoiceDOS;

	@Column(name = "invoice_po_date")
	private String invoicePoDate;

	@Column(name = "invoice_pos")
	private String invoicePOS;

	@Column(name = "invoice_state")
	private String invoiceState;

	@Column(name = "invoice_po_number")
	private String invoicePoNumber;

	@Column(name = "invoice_transport_mode")
	private String invoiceTransportMode;

	@Column(name = "invoice_vehicle_number")
	private String invoiceVehicleNumber;

	@Column(name = "invoice_reverse_charge")
	private String invoiceReverseCharge;

	@Column(name = "invoice_total_amount_before_tax")
	private String invoiceTotalAmountBeforeTax;

	@Column(name = "invoice_total_amount_after_tax")
	private String invoiceTotalAmountAfterTax;

	@Column(name = "invoice_tax_amount")
	private String invoiceTaxAmount;

	@Column(name = "invoice_igst_amount")
	private String invoiceIgstAmount;

	@Column(name = "invoice_cgst_amount")
	private String invoiceCgstAmount;

	@Column(name = "invoice_sgst_amount")
	private String invoiceSgstAmount;

	@Column(name = "invoice_key")
	private String invoiceUniqueKey;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_products_referece_no")
	private List<InvoiceProductDetails> invoiceProductDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_bank_reference_no")
	private InvoiceBankDetails invoiceBankDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_address_reference_no")
	private InvoiceAddressDetails invoiceAddressDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_company_reference_no")
	private Company invoiceCompanyDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_other_details_reference_no")
	private InvoiceOtherDetails invoiceOtherDetails;

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
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the invoiceDate
	 */
	public String getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the invoiceCreationDate
	 */
	public Date getInvoiceCreationDate() {
		return invoiceCreationDate;
	}

	/**
	 * @param invoiceCreationDate the invoiceCreationDate to set
	 */
	public void setInvoiceCreationDate(Date invoiceCreationDate) {
		this.invoiceCreationDate = invoiceCreationDate;
	}

	/**
	 * @return the invoiceOwner
	 */
	public String getInvoiceOwner() {
		return invoiceOwner;
	}

	/**
	 * @param invoiceOwner the invoiceOwner to set
	 */
	public void setInvoiceOwner(String invoiceOwner) {
		this.invoiceOwner = invoiceOwner;
	}

	/**
	 * @return the invoiceType
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the invoiceDOS
	 */
	public String getInvoiceDOS() {
		return invoiceDOS;
	}

	/**
	 * @param invoiceDOS the invoiceDOS to set
	 */
	public void setInvoiceDOS(String invoiceDOS) {
		this.invoiceDOS = invoiceDOS;
	}

	public String getInvoicePoDate() {
		return invoicePoDate;
	}

	public void setInvoicePoDate(String invoicePoDate) {
		this.invoicePoDate = invoicePoDate;
	}

	/**
	 * @return the invoicePOS
	 */
	public String getInvoicePOS() {
		return invoicePOS;
	}

	/**
	 * @param invoicePOS the invoicePOS to set
	 */
	public void setInvoicePOS(String invoicePOS) {
		this.invoicePOS = invoicePOS;
	}

	/**
	 * @return the invoiceState
	 */
	public String getInvoiceState() {
		return invoiceState;
	}

	/**
	 * @param invoiceState the invoiceState to set
	 */
	public void setInvoiceState(String invoiceState) {
		this.invoiceState = invoiceState;
	}

	/**
	 * @return the invoicePoNumber
	 */
	public String getInvoicePoNumber() {
		return invoicePoNumber;
	}

	/**
	 * @param invoicePoNumber the invoicePoNumber to set
	 */
	public void setInvoicePoNumber(String invoicePoNumber) {
		this.invoicePoNumber = invoicePoNumber;
	}

	/**
	 * @return the invoiceTransportMode
	 */
	public String getInvoiceTransportMode() {
		return invoiceTransportMode;
	}

	/**
	 * @param invoiceTransportMode the invoiceTransportMode to set
	 */
	public void setInvoiceTransportMode(String invoiceTransportMode) {
		this.invoiceTransportMode = invoiceTransportMode;
	}

	/**
	 * @return the invoiceVehicleNumber
	 */
	public String getInvoiceVehicleNumber() {
		return invoiceVehicleNumber;
	}

	/**
	 * @param invoiceVehicleNumber the invoiceVehicleNumber to set
	 */
	public void setInvoiceVehicleNumber(String invoiceVehicleNumber) {
		this.invoiceVehicleNumber = invoiceVehicleNumber;
	}

	/**
	 * @return the invoiceReverseCharge
	 */
	public String getInvoiceReverseCharge() {
		return invoiceReverseCharge;
	}

	/**
	 * @param invoiceReverseCharge the invoiceReverseCharge to set
	 */
	public void setInvoiceReverseCharge(String invoiceReverseCharge) {
		this.invoiceReverseCharge = invoiceReverseCharge;
	}

	/**
	 * @return the invoiceTotalAmountBeforeTax
	 */
	public String getInvoiceTotalAmountBeforeTax() {
		return invoiceTotalAmountBeforeTax;
	}

	/**
	 * @param invoiceTotalAmountBeforeTax the invoiceTotalAmountBeforeTax to set
	 */
	public void setInvoiceTotalAmountBeforeTax(String invoiceTotalAmountBeforeTax) {
		this.invoiceTotalAmountBeforeTax = invoiceTotalAmountBeforeTax;
	}

	/**
	 * @return the invoiceTotalAmountAfterTax
	 */
	public String getInvoiceTotalAmountAfterTax() {
		return invoiceTotalAmountAfterTax;
	}

	/**
	 * @param invoiceTotalAmountAfterTax the invoiceTotalAmountAfterTax to set
	 */
	public void setInvoiceTotalAmountAfterTax(String invoiceTotalAmountAfterTax) {
		this.invoiceTotalAmountAfterTax = invoiceTotalAmountAfterTax;
	}

	/**
	 * @return the invoiceTaxAmount
	 */
	public String getInvoiceTaxAmount() {
		return invoiceTaxAmount;
	}

	/**
	 * @param invoiceTaxAmount the invoiceTaxAmount to set
	 */
	public void setInvoiceTaxAmount(String invoiceTaxAmount) {
		this.invoiceTaxAmount = invoiceTaxAmount;
	}

	public String getInvoiceUniqueKey() {
		return invoiceUniqueKey;
	}

	public void setInvoiceUniqueKey(String invoiceUniqueKey) {
		this.invoiceUniqueKey = invoiceUniqueKey;
	}

	/**
	 * @return the invoiceProductDetails
	 */
	public List<InvoiceProductDetails> getInvoiceProductDetails() {
		return invoiceProductDetails;
	}

	public String getInvoiceIgstAmount() {
		return invoiceIgstAmount;
	}

	public void setInvoiceIgstAmount(String invoiceIgstAmount) {
		this.invoiceIgstAmount = invoiceIgstAmount;
	}

	public String getInvoiceCgstAmount() {
		return invoiceCgstAmount;
	}

	public void setInvoiceCgstAmount(String invoiceCgstAmount) {
		this.invoiceCgstAmount = invoiceCgstAmount;
	}

	public String getInvoiceSgstAmount() {
		return invoiceSgstAmount;
	}

	public void setInvoiceSgstAmount(String invoiceSgstAmount) {
		this.invoiceSgstAmount = invoiceSgstAmount;
	}

	/**
	 * @param invoiceProductDetails the invoiceProductDetails to set
	 */
	public void setInvoiceProductDetails(List<InvoiceProductDetails> invoiceProductDetails) {
		this.invoiceProductDetails = invoiceProductDetails;
	}

	/**
	 * @return the invoiceBankDetails
	 */
	public InvoiceBankDetails getInvoiceBankDetails() {
		return invoiceBankDetails;
	}

	/**
	 * @param invoiceBankDetails the invoiceBankDetails to set
	 */
	public void setInvoiceBankDetails(InvoiceBankDetails invoiceBankDetails) {
		this.invoiceBankDetails = invoiceBankDetails;
	}

	/**
	 * @return the invoiceAddressDetails
	 */
	public InvoiceAddressDetails getInvoiceAddressDetails() {
		if (invoiceAddressDetails == null) {
			invoiceAddressDetails = new InvoiceAddressDetails();
		}
		return invoiceAddressDetails;
	}

	/**
	 * @param invoiceAddressDetails the invoiceAddressDetails to set
	 */
	public void setInvoiceAddressDetails(InvoiceAddressDetails invoiceAddressDetails) {
		this.invoiceAddressDetails = invoiceAddressDetails;
	}

	/**
	 * @return the invoiceCompanyDetails
	 */
	public Company getInvoiceCompanyDetails() {
		return invoiceCompanyDetails;
	}

	/**
	 * @param invoiceCompanyDetails the invoiceCompanyDetails to set
	 */
	public void setInvoiceCompanyDetails(Company invoiceCompanyDetails) {
		this.invoiceCompanyDetails = invoiceCompanyDetails;
	}

	/**
	 * @return the invoiceSubType
	 */
	public String getInvoiceSubType() {
		return invoiceSubType;
	}

	/**
	 * @param invoiceSubType the invoiceSubType to set
	 */
	public void setInvoiceSubType(String invoiceSubType) {
		this.invoiceSubType = invoiceSubType;
	}

	/**
	 * @return the invoiceOtherDetails
	 */
	public InvoiceOtherDetails getInvoiceOtherDetails() {
		if (invoiceOtherDetails == null) {
			invoiceOtherDetails = new InvoiceOtherDetails();
		}
		return invoiceOtherDetails;
	}

	/**
	 * @param invoiceOtherDetails the invoiceOtherDetails to set
	 */
	public void setInvoiceOtherDetails(InvoiceOtherDetails invoiceOtherDetails) {
		this.invoiceOtherDetails = invoiceOtherDetails;
	}

}
