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
@Table(name = "invoice_product_details")
public class InvoiceProductDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_product_id")
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
	
	@Column(name = "product_amount")
	private String productAmount;
	
	@Column(name = "product_discount")
	private String productDiscount;
	
	@Column(name = "product_gst_rate")
	private String productGstRate;
	
	@Column(name = "product_tax_value")
	private String productTaxValue;
	
	@Column(name = "product_cgst")
	private String productCgst;
	
	@Column(name = "product_sgst")
	private String productSgst;
	
	@Column(name = "product_igst")
	private String productIgst;
	
	@Column(name = "product_total_amount")
	private String productTotalAmount;
	
	@Column(name = "product_owner")
	private String productOwner;

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
	 * @return the productAmount
	 */
	public String getProductAmount() {
		return productAmount;
	}

	/**
	 * @param productAmount the productAmount to set
	 */
	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
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
	 * @return the productTaxValue
	 */
	public String getProductTaxValue() {
		return productTaxValue;
	}

	/**
	 * @param productTaxValue the productTaxValue to set
	 */
	public void setProductTaxValue(String productTaxValue) {
		this.productTaxValue = productTaxValue;
	}

	/**
	 * @return the productCgst
	 */
	public String getProductCgst() {
		return productCgst;
	}

	/**
	 * @param productCgst the productCgst to set
	 */
	public void setProductCgst(String productCgst) {
		this.productCgst = productCgst;
	}

	/**
	 * @return the productSgst
	 */
	public String getProductSgst() {
		return productSgst;
	}

	/**
	 * @param productSgst the productSgst to set
	 */
	public void setProductSgst(String productSgst) {
		this.productSgst = productSgst;
	}

	/**
	 * @return the productIgst
	 */
	public String getProductIgst() {
		return productIgst;
	}

	/**
	 * @param productIgst the productIgst to set
	 */
	public void setProductIgst(String productIgst) {
		this.productIgst = productIgst;
	}

	/**
	 * @return the productTotalAmount
	 */
	public String getProductTotalAmount() {
		return productTotalAmount;
	}

	/**
	 * @param productTotalAmount the productTotalAmount to set
	 */
	public void setProductTotalAmount(String productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
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

}
