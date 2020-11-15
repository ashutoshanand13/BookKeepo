/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.BankDetails;
import com.bookkeepo.accounting.entity.Company;

/**
 * @author Ashutosh Anand
 *
 */
@Repository("bankRepository")
public interface BankRepository extends JpaRepository<BankDetails, Long> {

	List<BankDetails> findByUserBankCreatorAndBankCompanyDetails(String owner, Company company);

	BankDetails findById(int id);
	
	BankDetails findByUserBankAccount(String bankAccount);

}
