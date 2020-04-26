/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Payment;

/**
 * 
 * @author Ashutosh
 *
 */

@Repository("paymentRepository")
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByPaymentOwner(String owner);
	
	Payment findByPaymentNumberAndPaymentOwner(String paymentNo, String owner);

}
