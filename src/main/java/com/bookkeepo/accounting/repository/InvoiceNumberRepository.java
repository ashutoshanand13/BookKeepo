/**
 * 
 */
package com.bookkeepo.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.InvoiceNumber;

/**
 * @author sachingoyal
 *
 */
@Repository("invoiceNumberRepository")
public interface InvoiceNumberRepository extends JpaRepository<InvoiceNumber, Long> {

	InvoiceNumber findByName(String email);

}
