/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Receipts;

/**
 * 
 * @author Ashutosh
 *
 */

@Repository("receiptRepository")
public interface ReceiptRepository extends JpaRepository<Receipts, Long> {

	List<Receipts> findByReceiptOwner(String owner);

	Receipts findByReceiptNumberAndReceiptOwner(String receiptNo, String owner);
}