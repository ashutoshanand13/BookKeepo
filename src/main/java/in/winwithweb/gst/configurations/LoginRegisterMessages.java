package in.winwithweb.gst.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:messages.properties")
@Component
@ConfigurationProperties("loginregister")
public class LoginRegisterMessages {

	private String inCorrectLoginMessage;
	private String logoutSuccessMessage;
	private String userAlreadyExistsMessage;
	private String registrationSuccessMessage;
	private String userNotFoundMessage;
	private String successAcctActivationMessage;
	private String failedAcctActivationMessage;
	private String newPasswordSentMessage;
	private String activationLinkSentMessage;
	/**
	 * @return the inCorrectLoginMessage
	 */
	public String getInCorrectLoginMessage() {
		return inCorrectLoginMessage;
	}
	/**
	 * @param inCorrectLoginMessage the inCorrectLoginMessage to set
	 */
	public void setInCorrectLoginMessage(String inCorrectLoginMessage) {
		this.inCorrectLoginMessage = inCorrectLoginMessage;
	}
	/**
	 * @return the logoutSuccessMessage
	 */
	public String getLogoutSuccessMessage() {
		return logoutSuccessMessage;
	}
	/**
	 * @param logoutSuccessMessage the logoutSuccessMessage to set
	 */
	public void setLogoutSuccessMessage(String logoutSuccessMessage) {
		this.logoutSuccessMessage = logoutSuccessMessage;
	}
	/**
	 * @return the userAlreadyExistsMessage
	 */
	public String getUserAlreadyExistsMessage() {
		return userAlreadyExistsMessage;
	}
	/**
	 * @param userAlreadyExistsMessage the userAlreadyExistsMessage to set
	 */
	public void setUserAlreadyExistsMessage(String userAlreadyExistsMessage) {
		this.userAlreadyExistsMessage = userAlreadyExistsMessage;
	}
	/**
	 * @return the registrationSuccessMessage
	 */
	public String getRegistrationSuccessMessage() {
		return registrationSuccessMessage;
	}
	/**
	 * @param registrationSuccessMessage the registrationSuccessMessage to set
	 */
	public void setRegistrationSuccessMessage(String registrationSuccessMessage) {
		this.registrationSuccessMessage = registrationSuccessMessage;
	}
	/**
	 * @return the userNotFoundMessage
	 */
	public String getUserNotFoundMessage() {
		return userNotFoundMessage;
	}
	/**
	 * @param userNotFoundMessage the userNotFoundMessage to set
	 */
	public void setUserNotFoundMessage(String userNotFoundMessage) {
		this.userNotFoundMessage = userNotFoundMessage;
	}
	/**
	 * @return the successAcctActivationMessage
	 */
	public String getSuccessAcctActivationMessage() {
		return successAcctActivationMessage;
	}
	/**
	 * @param successAcctActivationMessage the successAcctActivationMessage to set
	 */
	public void setSuccessAcctActivationMessage(String successAcctActivationMessage) {
		this.successAcctActivationMessage = successAcctActivationMessage;
	}
	/**
	 * @return the failedAcctActivationMessage
	 */
	public String getFailedAcctActivationMessage() {
		return failedAcctActivationMessage;
	}
	/**
	 * @param failedAcctActivationMessage the failedAcctActivationMessage to set
	 */
	public void setFailedAcctActivationMessage(String failedAcctActivationMessage) {
		this.failedAcctActivationMessage = failedAcctActivationMessage;
	}
	/**
	 * @return the newPasswordSentMessage
	 */
	public String getNewPasswordSentMessage() {
		return newPasswordSentMessage;
	}
	/**
	 * @param newPasswordSentMessage the newPasswordSentMessage to set
	 */
	public void setNewPasswordSentMessage(String newPasswordSentMessage) {
		this.newPasswordSentMessage = newPasswordSentMessage;
	}
	/**
	 * @return the activationLinkSentMessage
	 */
	public String getActivationLinkSentMessage() {
		return activationLinkSentMessage;
	}
	/**
	 * @param activationLinkSentMessage the activationLinkSentMessage to set
	 */
	public void setActivationLinkSentMessage(String activationLinkSentMessage) {
		this.activationLinkSentMessage = activationLinkSentMessage;
	}
	
	
	
	
}
