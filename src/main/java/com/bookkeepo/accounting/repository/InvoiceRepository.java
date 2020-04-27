/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.InvoiceDetails;

/**
 * @author sachingoyal
 *
 */
@Repository("invoiceRepository")
public interface InvoiceRepository extends JpaRepository<InvoiceDetails, Long> {
	List<InvoiceDetails> findByInvoiceOwner(String name);

	InvoiceDetails findById(int id);

	InvoiceDetails findByInvoiceUniqueKey(String key);

	List<InvoiceDetails> findByInvoiceOwnerAndInvoiceType(String invoiceOwner, String invoiceType);

	InvoiceDetails findByInvoiceNumberAndInvoiceOwnerAndInvoiceType(String invoiceNo, String owner, String type);

	List<InvoiceDetails> findByInvoiceOwnerAndInvoiceTypeAndInvoiceSubType(String invoiceOwner, String invoiceType,
			String invoiceSubType);

}
