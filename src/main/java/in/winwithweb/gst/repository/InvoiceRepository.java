/**
 * 
 */
package in.winwithweb.gst.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.sales.InvoiceDetails;

/**
 * @author sachingoyal
 *
 */
@Repository("invoiceRepository")
public interface InvoiceRepository extends JpaRepository<InvoiceDetails, Long>{
	List<InvoiceDetails> findByInvoiceOwner(String name);
}
