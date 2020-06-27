/**
 * 
 */
package com.bookkeepo.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.AccountLedger;

/**
 * @author Yash Singh
 *
 */

@Repository("accountLedgerRepository")
public interface AccountLedgerRepository extends JpaRepository<AccountLedger, Long> {

	

}
