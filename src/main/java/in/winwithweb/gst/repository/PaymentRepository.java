/**
 * 
 */
package in.winwithweb.gst.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.Payment;

/**
 * 
 * @author Ashutosh
 *
 */

@Repository("paymentRepository")
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	


}
