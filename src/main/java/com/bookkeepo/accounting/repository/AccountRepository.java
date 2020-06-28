/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;

/**
 * @author sachingoyal
 *
 */

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Accounts, Long> {

	List<Accounts> findByAccountOwnerAndAccountCompanyDetails(String owner, Company company);

	Accounts findById(int id);

	Accounts findByAccountOwnerAndAccountPanAndAccountCompanyDetails(String accountOwner, String accountPan,
			Company company);

	Accounts findByAccountOwnerAndGstinAndAccountCompanyDetails(String accountOwner, String gstin, Company company);

	List<Accounts> findByAccountOwnerAndAccountNameAndAccountCompanyDetails(String accountOwner, String name,
			Company company);
	
	@Query("select u from Accounts u where u.accountOwner = ?1 and u.accountType in ?2")
	List<Accounts> findByAccountOwnerAndAccountTypes(String owner, List<String> accountType);

}
