/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.Payment;

/**
 * 
 * @author Ashutosh
 *
 */

@Repository("paymentRepository")
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByPaymentOwnerAndPaymentCompanyDetailsAndPaymentDeleted(String owner, Company company, int isDeleted);
	
	Payment findByIdAndPaymentOwner(int id, String owner);
	
	List<Payment> findByAccountRefNo(Accounts account);

}
