/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Accounts;

/**
 * @author sachingoyal
 *
 */

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Accounts, Long> {

	List<Accounts> findByAccountOwner(String owner);

	Accounts findByAccountName(String accountName);

	Accounts findByGstinAndAccountOwner(String gstin, String user);

	Accounts findById(int id);

	Accounts findByAccountOwnerAndAccountPan(String accountOwner, String accountPan);

	Accounts findByAccountOwnerAndGstin(String accountOwner, String gstin);
	
	List<Accounts> findByAccountOwnerAndAccountName(String accountOwner, String name);

}
