/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.Payment;

/**
 * 
 * @author Ashutosh
 *
 */

@Repository("paymentRepository")
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByPaymentOwnerAndPaymentCompanyDetails(String owner, Company company);
	
	@Modifying
    @Query("delete from Payment u where u.id = ?1 and u.paymentOwner = ?2")
    void deleteUsersByIDAndUser(int id, String user);

}
