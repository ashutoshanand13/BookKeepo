/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.dtos.AccountDto;
import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;

/**
 * @author sachingoyal
 *
 */

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Accounts, Long> {

	List<Accounts> findByAccountOwnerAndAccountCompanyDetails(String owner, Company company);
	
	List<Accounts> findByAccountOwnerAndAccountCompanyDetailsAndAccountType(String owner, Company company, String accountType);
	
	@Query("select u from Accounts u where u.accountOwner = ?1 and u.accountCompanyDetails in ?2 and u.accountType not in ('Cash-in-hand','Bank Accounts')")
	List<Accounts> findAccounts(String owner, Company company);
	
	@Query("select new com.bookkeepo.accounting.dtos.AccountDto(u.id, u.accountName) "
			+ "from Accounts u where u.accountOwner = ?1 and u.accountCompanyDetails in ?2 "
			+ "and u.accountType not in ('Cash-in-hand','Bank Accounts')")
	List<AccountDto> findAccountList(String owner, Company company);

	Accounts findById(int id);
	
	Accounts findByAccountNameAndAccountType(String name, String type);

	Accounts findByAccountOwnerAndAccountPanAndAccountCompanyDetails(String accountOwner, String accountPan,
			Company company);

	Accounts findByAccountOwnerAndGstinAndAccountCompanyDetails(String accountOwner, String gstin, Company company);

	List<Accounts> findByAccountOwnerAndAccountNameAndAccountCompanyDetails(String accountOwner, String name,
			Company company);
	
	@Query("select u from Accounts u where u.accountOwner = ?1 and u.accountType in ?2")
	List<Accounts> findByAccountOwnerAndAccountTypes(String owner, List<String> accountType);
	
	
	@Query("select new com.bookkeepo.accounting.dtos.AccountDto(u.id, u.accountName, u.accountType, "
			+ "u.gstin, u.accountPan, u.accountContact, "
			+ "u.accountAddress, u.accountState, u.accountPincode, "
			+ "u.openingBalanceType, u.openingBalanceAmount, u.accountEmail) "
			+ "from Accounts u where u.id = ?1")
	AccountDto findAccountbyId(int id);

}
