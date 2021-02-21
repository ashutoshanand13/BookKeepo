/**
 * 
 */
package com.bookkeepo.accounting.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * @author sachingoyal
 *
 */

@Data
@Entity
@Table(name = "product_details")
public class ProductDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private int id;

	@Column(name = "product_desc")
	private String productDescription;

	@Column(name = "product_hnscode")
	private String productHnscode;

	@Column(name = "product_uom")
	private String productUom;

	@Column(name = "product_quantity")
	private String productQuantity;

	@Column(name = "product_rate")
	private String productRate;

	@Column(name = "product_discount")
	private String productDiscount;

	@Column(name = "product_gst_rate")
	private String productGstRate;

	@Column(name = "product_owner", nullable = false, updatable = false)
	private String productOwner;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "product_creation_date", nullable = false, updatable = false)
	private Date productCreationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "product_last_modified", nullable = false)
	private Date productLastModified;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "product_company_reference_no", updatable = false)
	private Company productCompanyDetails;

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
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the productHnscode
	 */
	public String getProductHnscode() {
		return productHnscode;
	}

	/**
	 * @param productHnscode the productHnscode to set
	 */
	public void setProductHnscode(String productHnscode) {
		this.productHnscode = productHnscode;
	}

	/**
	 * @return the productUom
	 */
	public String getProductUom() {
		return productUom;
	}

	/**
	 * @param productUom the productUom to set
	 */
	public void setProductUom(String productUom) {
		this.productUom = productUom;
	}

	/**
	 * @return the productQuantity
	 */
	public String getProductQuantity() {
		return productQuantity;
	}

	/**
	 * @param productQuantity the productQuantity to set
	 */
	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	/**
	 * @return the productRate
	 */
	public String getProductRate() {
		return productRate;
	}

	/**
	 * @param productRate the productRate to set
	 */
	public void setProductRate(String productRate) {
		this.productRate = productRate;
	}

	/**
	 * @return the productDiscount
	 */
	public String getProductDiscount() {
		return productDiscount;
	}

	/**
	 * @param productDiscount the productDiscount to set
	 */
	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}

	/**
	 * @return the productGstRate
	 */
	public String getProductGstRate() {
		return productGstRate;
	}

	/**
	 * @param productGstRate the productGstRate to set
	 */
	public void setProductGstRate(String productGstRate) {
		this.productGstRate = productGstRate;
	}

	/**
	 * @return the productOwner
	 */
	public String getProductOwner() {
		return productOwner;
	}

	/**
	 * @param productOwner the productOwner to set
	 */
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	/**
	 * @return the productCreationDate
	 */
	public Date getProductCreationDate() {
		return productCreationDate;
	}

	/**
	 * @param productCreationDate the productCreationDate to set
	 */
	public void setProductCreationDate(Date productCreationDate) {
		this.productCreationDate = productCreationDate;
	}

	/**
	 * @return the productLastModified
	 */
	public Date getProductLastModified() {
		return productLastModified;
	}

	/**
	 * @param productLastModified the productLastModified to set
	 */
	public void setProductLastModified(Date productLastModified) {
		this.productLastModified = productLastModified;
	}

	/**
	 * @return the productCompanyDetails
	 */
	public Company getProductCompanyDetails() {
		return productCompanyDetails;
	}

	/**
	 * @param productCompanyDetails the productCompanyDetails to set
	 */
	public void setProductCompanyDetails(Company productCompanyDetails) {
		this.productCompanyDetails = productCompanyDetails;
	}

}
