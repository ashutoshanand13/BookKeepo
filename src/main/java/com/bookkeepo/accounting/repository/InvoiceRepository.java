/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.InvoiceDetails;

/**
 * @author sachingoyal
 *
 */
@Repository("invoiceRepository")
public interface InvoiceRepository extends JpaRepository<InvoiceDetails, Long> {
	List<InvoiceDetails> findByInvoiceOwnerAndInvoiceCompanyDetails(String name, Company company);

	InvoiceDetails findById(int id);

	InvoiceDetails findByInvoiceUniqueKey(String key);

	List<InvoiceDetails> findByInvoiceOwnerAndInvoiceTypeAndInvoiceCompanyDetails(String invoiceOwner, String invoiceType,Company company);

	InvoiceDetails findByInvoiceNumberAndInvoiceOwnerAndInvoiceTypeAndInvoiceCompanyDetails(String invoiceNo, String owner, String type,Company company);

	List<InvoiceDetails> findByInvoiceOwnerAndInvoiceTypeAndInvoiceSubTypeAndInvoiceCompanyDetails(String invoiceOwner, String invoiceType,
			String invoiceSubType,Company company);
	
	List<InvoiceDetails> findByInvoiceAccountDetailsAndInvoiceOwnerAndInvoicePaidAndInvoiceTypeNot(Accounts account, String owner, int paid,String invoiceType);
	
	List<InvoiceDetails> findByInvoiceAccountDetails(Accounts account);
	
	@Query("SELECT a FROM InvoiceDetails a INNER JOIN Accounts b on a.invoiceAccountDetails = b.id where a.invoiceOwner = ?1 and b.accountType = ?2")
	List<InvoiceDetails> findByAccountTypeAndAccountOwner(String owner, String accountType);

	@Query("SELECT a FROM InvoiceDetails a where a.invoiceOwner= ?1 and a.invoiceCompanyDetails= ?3 and a.invoiceType in (?2)")
	List<InvoiceDetails> findByInvoiceOwnerAndInvoiceTypeAndInvoiceCompanyDetails(String invoiceOwner, List<String> invoiceTypes, Company company);
	
	@Query("SELECT o FROM InvoiceDetails o WHERE o.invoiceType in (?1) AND o.invoiceOwner=?2 AND str_to_date(o.invoiceDate,'%d-%m-%Y') BETWEEN ?3 AND ?4")
	List<InvoiceDetails> findByStartEndDate(List<String> invoiceType,String invoiceOwner, Date startDate, Date endDate);
	
	@Modifying
	@Query("UPDATE InvoiceDetails o set o.invoicePaidAmt= o.invoicePaidAmt+:amt where o.id = :id")
	void updateInvoicePaidAmt(@Param("amt")String amt, @Param("id") int id);
}
