/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.BankDetails;

/**
 * @author Ashutosh Anand
 *
 */
@Repository("bankRepository")
public interface BankRepository extends JpaRepository<BankDetails, Long>{
	
	List<BankDetails> findByuserbankCreator(String owner);
	
	BankDetails findById(int id);
	
}
