/**
 * 
 */
package com.bookkeepo.accounting.model;

/**
 * @author sachingoyal
 *
 */
public class UserDetails {

	private String email;
	private String oldPassword;
	private String newPassword;
	private String trouble;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the trouble
	 */
	public String getTrouble() {
		return trouble;
	}

	/**
	 * @param trouble the trouble to set
	 */
	public void setTrouble(String trouble) {
		this.trouble = trouble;
	}

}
