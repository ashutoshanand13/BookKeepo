/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.InvoiceProductDetails;

/**
 * @author Yash Singh
 *
 */
@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<InvoiceProductDetails, Long>{
	
	List<InvoiceProductDetails> findByProductOwner(String owner);
	
	InvoiceProductDetails findById(int id);
	
}
