/**
 * 
 */
package in.winwithweb.gst.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.sales.InvoiceProductDetails;

/**
 * @author Yash Singh
 *
 */
@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<InvoiceProductDetails, Long>{
	
	List<InvoiceProductDetails> findByProductOwner(String owner);
	
}
