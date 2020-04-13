/**
 * 
 */
package in.winwithweb.gst.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.sales.InvoiceDetails;

/**
 * @author sachingoyal
 *
 */
@Repository("invoiceRepository")
public interface InvoiceRepository extends JpaRepository<InvoiceDetails, Long>{
	List<InvoiceDetails> findByInvoiceOwner(String name);
	
	InvoiceDetails findById(int id);
	
	InvoiceDetails findByInvoiceNumberAndInvoiceOwner(String invoiceNumber, String owner);
	
	InvoiceDetails findByInvoiceUniqueKey(String key);
	
	@Query("SELECT u FROM InvoiceDetails u WHERE u.invoiceNumber = ?1 and u.invoiceType = ?2 and u.invoiceOwner = ?3")
	InvoiceDetails findByPageNameAndInvoiceNo(String invoiceNo, String pageName, String owner);
}
