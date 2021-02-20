/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.dtos.ReceiptDto;
import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.Receipts;

/**
 * 
 * @author Ashutosh
 *
 */

@Repository("receiptRepository")
public interface ReceiptRepository extends JpaRepository<Receipts, Long> {

	
	@Query("SELECT new com.bookkeepo.accounting.dtos.ReceiptDto("
			+ "o.id, o.receiptNumber, o.receiptReference, "
			+ "o.receiptDate, o.receiptMode, o.receiptAmount, "
			+ "o.receiptDescription, o.accountRefNo.accountName) "
			+ "FROM Receipts o WHERE o.receiptOwner=?1 AND o.receiptCompanyDetails=?2 AND o.receiptDeleted=?3")
	List<ReceiptDto> findAllReceipt(String owner, Company company, int isDeleted);
	
	Receipts findByIdAndReceiptOwner(int id, String owner);
	
	List<Receipts> findByAccountRefNo(Accounts account);
	
	@Query("SELECT o FROM Receipts o WHERE o.receiptCompanyDetails=?1 AND str_to_date(o.receiptDate,'%d-%m-%Y') BETWEEN ?2 AND ?3")
	List<Receipts> findByStartEndDate(Company company, Date startDate, Date endDate);
	
	List<Receipts> findAllByReceiptCompanyDetailsAndReceiptCreationDateBetween(Company company, Date startDate, Date
			 endDate);
}
