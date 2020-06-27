/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.AccountLedger;
import com.bookkeepo.accounting.repository.AccountLedgerRepository;

/**
 * @author Yash Singh
 *
 */

@Service("accountLedgerService")
public class AccountLedgerService {

	private AccountLedgerRepository accountLedgerRepository;

	@Autowired
	public AccountLedgerService(AccountLedgerRepository accountLedgerRepository) {
		this.accountLedgerRepository = accountLedgerRepository;

	}
	
	public List<AccountLedger> findAllAccountType() {
		return accountLedgerRepository.findAll();
	}

	
}
