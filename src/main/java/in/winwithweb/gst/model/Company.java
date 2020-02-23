package in.winwithweb.gst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "company_details")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	private int id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_gstin")
	private String companyGstin;
	
	@Column(name = "company_state")
	private String companyState;
	
	@Column(name = "company_address")
	private String companyAddress;
	
	@Column(name = "company_email")
	private String companyEmail;
	
	@Column(name = "company_telephone")
	private String companyTelephone;
	
	@Column(name = "company_bank_ac")
	private String companyBankAC;
	
	@Column(name = "company_bank_ifsc")
	private String companyBankIFSC;
	
	@Column(name = "company_bank_tnc")
	private String companyBankTNC;
	
	@Lob
	@Column(name = "company_logo", length = 2097152)
	private byte[] companyLogo;
	
	@Transient
	private boolean redirected;
	
	public Company() {
		
	}
	
	public Company(boolean redirected) {
		super();
		this.redirected = redirected;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyGstin
	 */
	public String getCompanyGstin() {
		return companyGstin;
	}
	/**
	 * @param companyGstin the companyGstin to set
	 */
	public void setCompanyGstin(String companyGstin) {
		this.companyGstin = companyGstin;
	}
	/**
	 * @return the companyState
	 */
	public String getCompanyState() {
		return companyState;
	}
	/**
	 * @param companyState the companyState to set
	 */
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}
	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}
	/**
	 * @param companyEmail the companyEmail to set
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	/**
	 * @return the companyTelephone
	 */
	public String getCompanyTelephone() {
		return companyTelephone;
	}
	/**
	 * @param companyTelephone the companyTelephone to set
	 */
	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}
	/**
	 * @return the companyLogo
	 */
	public byte[] getCompanyLogo() {
		return companyLogo;
	}
	/**
	 * @param companyLogo the companyLogo to set
	 */
	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}
	/**
	 * @return the companyBankAC
	 */
	public String getCompanyBankAC() {
		return companyBankAC;
	}
	/**
	 * @param companyBankAC the companyBankAC to set
	 */
	public void setCompanyBankAC(String companyBankAC) {
		this.companyBankAC = companyBankAC;
	}
	/**
	 * @return the companyBankIFSC
	 */
	public String getCompanyBankIFSC() {
		return companyBankIFSC;
	}
	/**
	 * @param companyBankIFSC the companyBankIFSC to set
	 */
	public void setCompanyBankIFSC(String companyBankIFSC) {
		this.companyBankIFSC = companyBankIFSC;
	}
	/**
	 * @return the companyBankTNC
	 */
	public String getCompanyBankTNC() {
		return companyBankTNC;
	}
	/**
	 * @param companyBankTNC the companyBankTNC to set
	 */
	public void setCompanyBankTNC(String companyBankTNC) {
		this.companyBankTNC = companyBankTNC;
	}
	/**
	 * @return the redirected
	 */
	public boolean isRedirected() {
		return redirected;
	}
	/**
	 * @param redirected the redirected to set
	 */
	public void setRedirected(boolean redirected) {
		this.redirected = redirected;
	}
	
}
