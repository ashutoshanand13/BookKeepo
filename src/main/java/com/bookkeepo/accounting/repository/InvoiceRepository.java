/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
