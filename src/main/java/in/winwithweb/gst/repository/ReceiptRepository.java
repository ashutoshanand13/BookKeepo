/**
 * 
 */
package in.winwithweb.gst.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.Receipts;

/**
 * 
 * @author Ashutosh
 *
 */

@Repository("receiptRepository")
public interface ReceiptRepository extends JpaRepository<Receipts, Long> {
	


}
