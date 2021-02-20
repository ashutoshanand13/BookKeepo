/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.dtos.PaymentDto;
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

	@Query("SELECT new com.bookkeepo.accounting.dtos.PaymentDto("
			+ "o.id, o.paymentNumber, o.paymentReference, "
			+ "o.paymentDate, o.paymentMode, o.paymentAmount, "
			+ "o.paymentDescription, o.accountRefNo.accountName) "
			+ "FROM Payment o WHERE o.paymentOwner=?1 AND o.paymentCompanyDetails=?2 AND o.paymentDeleted=?3")
	List<PaymentDto> findAllPayment(String owner, Company company, int isDeleted);
	
	Payment findByIdAndPaymentOwner(int id, String owner);
	
	List<Payment> findByAccountRefNo(Accounts account);
	
	
	@Query("SELECT o FROM Payment o WHERE o.paymentCompanyDetails=?1 AND str_to_date(o.paymentDate,'%d-%m-%Y') BETWEEN ?2 AND ?3")
	List<Payment> findByStartEndDate(Company company, Date startDate, Date endDate);
	 
	
	List<Payment> findAllByPaymentCompanyDetailsAndPaymentCreationDateBetween(Company company, Date startDate, Date
			 endDate);
}
