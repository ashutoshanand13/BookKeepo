package in.winwithweb.gst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "payment_details")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private int id;

	@Column(name = "payment_number")
	@NotEmpty(message = "*Please provide the payment number")
	private String paymentNumber;

	@Column(name = "payment_account_name")
	@NotEmpty(message = "*Please provide the account name")
	private String accountName;

	@Column(name = "payment_account_gstin")
	@NotEmpty(message = "*Please provide the account GST")
	private String gstin;

	@Column(name = "payment_account_address")
	@NotEmpty(message = "*Please provide the account address")
	private String accountAddress;

	@Column(name = "payment_reference")
	@NotEmpty(message = "*Please provide the payment reference")
	private String paymentReference;

	@Column(name = "payment_date")
	@NotEmpty(message = "*Please provide the payment date")
	private String paymentDate;

	@Column(name = "payment_mode")
	@NotEmpty(message = "*Please provide the payment mode")
	private String paymentMode;
	
	@Column(name = "payment_amount")
	@NotEmpty(message = "*Please provide the Payment amount")
	private String paymentAmount;

	@Column(name = "payment_description")
	@NotEmpty(message = "*Please provide the payment description")
	private String paymentDescription;
	
	@Column(name = "payment_owner")
	private String paymentOwner;

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
	 * @return the paymentNumber
	 */
	public String getPaymentNumber() {
		return paymentNumber;
	}

	/**
	 * @param paymentNumber the paymentNumber to set
	 */
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}

	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	/**
	 * @return the accountAddress
	 */
	public String getAccountAddress() {
		return accountAddress;
	}

	/**
	 * @param accountAddress the accountAddress to set
	 */
	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	/**
	 * @return the paymentReference
	 */
	public String getPaymentReference() {
		return paymentReference;
	}

	/**
	 * @param paymentReference the paymentReference to set
	 */
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the paymentDescription
	 */
	public String getPaymentDescription() {
		return paymentDescription;
	}

	/**
	 * @param paymentDescription the paymentDescription to set
	 */
	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	/**
	 * @return the paymentAmount
	 */
	public String getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the paymentOwner
	 */
	public String getPaymentOwner() {
		return paymentOwner;
	}

	/**
	 * @param paymentOwner the paymentOwner to set
	 */
	public void setPaymentOwner(String paymentOwner) {
		this.paymentOwner = paymentOwner;
	}
	
}
