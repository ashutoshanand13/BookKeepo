/**
 * 
 */
package com.bookkeepo.accounting.dtos;

/**
 * @author Yash Singh
 *
 */
public class AccountDto {

	private int id;

	private String accountName;

	private String accountOwner;

	private String accountType;

	private String gstin;

	private String accountPan;

	private String accountContact;

	private String accountAddress;

	private String accountState;

	private String accountPincode;
	
	private String openingBalanceType;
	
	private String openingBalanceAmount;

	private String accountEmail;
	
	

	public AccountDto() {
		super();
	}
	
	

	public AccountDto(int id, String accountName) {
		super();
		this.id = id;
		this.accountName = accountName;
	}



	public AccountDto(int id, String accountName, String accountType, String gstin, String accountPan,
			String accountContact, String accountAddress, String accountState, String accountPincode,
			String openingBalanceType, String openingBalanceAmount, String accountEmail) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.accountType = accountType;
		this.gstin = gstin;
		this.accountPan = accountPan;
		this.accountContact = accountContact;
		this.accountAddress = accountAddress;
		this.accountState = accountState;
		this.accountPincode = accountPincode;
		this.openingBalanceType = openingBalanceType;
		this.openingBalanceAmount = openingBalanceAmount;
		this.accountEmail = accountEmail;
	}



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
	 * @return the accountOwner
	 */
	public String getAccountOwner() {
		return accountOwner;
	}

	/**
	 * @param accountOwner the accountOwner to set
	 */
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
	 * @return the accountPan
	 */
	public String getAccountPan() {
		return accountPan;
	}

	/**
	 * @param accountPan the accountPan to set
	 */
	public void setAccountPan(String accountPan) {
		this.accountPan = accountPan;
	}

	/**
	 * @return the accountContact
	 */
	public String getAccountContact() {
		return accountContact;
	}

	/**
	 * @param accountContact the accountContact to set
	 */
	public void setAccountContact(String accountContact) {
		this.accountContact = accountContact;
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
	 * @return the accountState
	 */
	public String getAccountState() {
		return accountState;
	}

	/**
	 * @param accountState the accountState to set
	 */
	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	/**
	 * @return the accountPincode
	 */
	public String getAccountPincode() {
		return accountPincode;
	}

	/**
	 * @param accountPincode the accountPincode to set
	 */
	public void setAccountPincode(String accountPincode) {
		this.accountPincode = accountPincode;
	}

	/**
	 * @return the openingBalanceType
	 */
	public String getOpeningBalanceType() {
		return openingBalanceType;
	}

	/**
	 * @param openingBalanceType the openingBalanceType to set
	 */
	public void setOpeningBalanceType(String openingBalanceType) {
		this.openingBalanceType = openingBalanceType;
	}

	/**
	 * @return the openingBalanceAmount
	 */
	public String getOpeningBalanceAmount() {
		return openingBalanceAmount;
	}

	/**
	 * @param openingBalanceAmount the openingBalanceAmount to set
	 */
	public void setOpeningBalanceAmount(String openingBalanceAmount) {
		this.openingBalanceAmount = openingBalanceAmount;
	}

	/**
	 * @return the accountEmail
	 */
	public String getAccountEmail() {
		return accountEmail;
	}

	/**
	 * @param accountEmail the accountEmail to set
	 */
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}
	
	

}
