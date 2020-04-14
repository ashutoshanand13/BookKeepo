package in.winwithweb.gst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "receipt_details")
public class Receipts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "receipt_id")
	private int id;

	@Column(name = "receipt_number")
	@NotEmpty(message = "*Please provide the receipt number")
	private String receiptNumber;

	@Column(name = "receipt_reference")
	@NotEmpty(message = "*Please provide the receipt reference")
	private String receiptReference;

	@Column(name = "receipt_date")
	@NotEmpty(message = "*Please provide the receipt date")
	private String receiptDate;

	@Column(name = "receipt_mode")
	@NotEmpty(message = "*Please provide the receipt mode")
	private String receiptMode;

	@Column(name = "receipt_amount")
	@NotEmpty(message = "*Please provide the receipt amount")
	private String receiptAmount;

	@Column(name = "receipt_description")
	@NotEmpty(message = "*Please provide the receipt description")
	private String receiptDescription;

	@Column(name = "receipt_owner")
	private String receiptOwner;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_account_reference_no")
	private Accounts accountRefNo;

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
	 * @return the receiptNumber
	 */
	public String getReceiptNumber() {
		return receiptNumber;
	}

	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	/**
	 * @return the receiptAmount
	 */
	public String getReceiptAmount() {
		return receiptAmount;
	}

	/**
	 * @param receiptAmount the receiptAmount to set
	 */
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	/**
	 * @return the receiptReference
	 */
	public String getReceiptReference() {
		return receiptReference;
	}

	/**
	 * @param receiptReference the receiptReference to set
	 */
	public void setReceiptReference(String receiptReference) {
		this.receiptReference = receiptReference;
	}

	/**
	 * @return the receiptDate
	 */
	public String getReceiptDate() {
		return receiptDate;
	}

	/**
	 * @param receiptDate the receiptDate to set
	 */
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	/**
	 * @return the receiptMode
	 */
	public String getReceiptMode() {
		return receiptMode;
	}

	/**
	 * @param receiptMode the receiptMode to set
	 */
	public void setReceiptMode(String receiptMode) {
		this.receiptMode = receiptMode;
	}

	/**
	 * @return the receiptDescription
	 */
	public String getReceiptDescription() {
		return receiptDescription;
	}

	/**
	 * @param receiptDescription the receiptDescription to set
	 */
	public void setReceiptDescription(String receiptDescription) {
		this.receiptDescription = receiptDescription;
	}

	/**
	 * @return the receiptOwner
	 */
	public String getReceiptOwner() {
		return receiptOwner;
	}

	/**
	 * @param receiptOwner the receiptOwner to set
	 */
	public void setReceiptOwner(String receiptOwner) {
		this.receiptOwner = receiptOwner;
	}

	/**
	 * @return the accountRefNo
	 */
	public Accounts getAccountRefNo() {
		return accountRefNo;
	}

	/**
	 * @param accountRefNo the accountRefNo to set
	 */
	public void setAccountRefNo(Accounts accountRefNo) {
		this.accountRefNo = accountRefNo;
	}

}
