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
@Table(name = "invoice_company_details")
public class InvoiceCompanyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_company_id")
	private int id;

	@Column(name = "invoice_company_name")
	private String comanyName;

	@Column(name = "invoice_company_address")
	private String address;

	@Column(name = "invoice_company_phone")
	private String telephone;

	@Column(name = "invoice_company_gstin")
	private String companyGstin;

	@Column(name = "invoice_company_email")
	private String companyEmail;

	/**
	 * @return the comanyName
	 */
	public String getComanyName() {
		return comanyName;
	}

	/**
	 * @param comanyName the comanyName to set
	 */
	public void setComanyName(String comanyName) {
		this.comanyName = comanyName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

}
