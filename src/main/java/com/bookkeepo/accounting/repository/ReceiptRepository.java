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
import com.bookkeepo.accounting.entity.Receipts;

/**
 * 
 * @author Ashutosh
 *
 */

@Repository("receiptRepository")
public interface ReceiptRepository extends JpaRepository<Receipts, Long> {

	List<Receipts> findByReceiptOwnerAndReceiptCompanyDetails(String owner, Company company);
	
	@Modifying
    @Query("delete from Receipts u where u.id = ?1 and u.receiptOwner = ?2")
    void deleteUsersByIDAndUser(int id, String user);
}
