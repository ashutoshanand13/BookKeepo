/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.repository.AccountRepository;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;

/**
 * @author sachingoyal
 *
 */

@Service("accountService")
public class AccountService {

	private AccountRepository accountRepository;

	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository, CompanyDetailsRepository companyDetailsRepository) {
		this.accountRepository = accountRepository;
		this.companyDetailsRepository = companyDetailsRepository;

	}

	public Accounts saveAccount(Accounts account) {
		 return accountRepository.save(account);
	}

	public Accounts findAccountByGstin(String gst, String owner) {
		return accountRepository.findByAccountOwnerAndGstinAndAccountCompanyDetails(owner, gst,
				companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1));
	}

	public Accounts findAccountByPan(String pan, String owner) {
		return accountRepository.findByAccountOwnerAndAccountPanAndAccountCompanyDetails(owner, pan,
				companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1));
	}

	public List<Accounts> fetchAccountName(String user, Company company) {
		List<Accounts> accountList = new ArrayList<Accounts>();
		Accounts accounts = new Accounts();
		accounts.setId(0);
		accounts.setAccountName("Select Account");
		accountList.add(accounts);

		List<Accounts> dbAccountList = accountRepository.findByAccountOwnerAndAccountCompanyDetails(user, company);

		if (!dbAccountList.isEmpty()) {
			accountList.addAll(dbAccountList);
		}

		return accountList;
	}

	public List<Accounts> fetchAccountNameForInvoice(String user, String accountType) {
		List<Accounts> accountList = new ArrayList<Accounts>();
		Accounts selectAccount = new Accounts();
		selectAccount.setId(0);
		selectAccount.setAccountName("Select Account");
		accountList.add(selectAccount);

		Accounts newAccount = new Accounts();
		newAccount.setId(-1);
		newAccount.setAccountName("Add New Account");
		accountList.add(newAccount);

		List<Accounts> dbAccountList = accountRepository.findByAccountOwnerAndAccountCompanyDetailsAndAccountType(user,
				companyDetailsRepository.findByUserNameAndCompanyActive(user, 1), accountType);

		if (!dbAccountList.isEmpty()) {
			accountList.addAll(dbAccountList);
		}

		return accountList;
	}

	public Accounts findById(int id) {
		return accountRepository.findById(id);
	}

	public List<Accounts> findByAccountOwnerAndAccountName(String accountOwner, String name) {
		List<Accounts> accountList = accountRepository.findByAccountOwnerAndAccountNameAndAccountCompanyDetails(
				accountOwner, name, companyDetailsRepository.findByUserNameAndCompanyActive(accountOwner, 1));
		return accountList.size() == 0 ? null : accountList;
	}
	
	public List<Accounts> findByAccountOwnerAndAccountTypes(String accountOwner, List<String> accountType) {
		List<Accounts> accountList = new ArrayList<Accounts>();
		Accounts selectAccount = new Accounts();
		selectAccount.setId(0);
		selectAccount.setAccountName("Select Account");
		accountList.add(selectAccount);
		
		Accounts newAccount = new Accounts();
		newAccount.setId(-1);
		newAccount.setAccountName("Add New Account");
		accountList.add(newAccount);
		
		List<Accounts> dbAccountList = accountRepository.findByAccountOwnerAndAccountTypes(accountOwner, accountType);

		if (!dbAccountList.isEmpty()) {
			accountList.addAll(dbAccountList);
		}
				
		
		return accountList;
	}
	
	public List<Accounts> findAccounts(String owner, Company company) {
		List<Accounts> accountList = new ArrayList<Accounts>();
		Accounts accounts = new Accounts();
		accounts.setId(0);
		accounts.setAccountName("Select Account");
		accountList.add(accounts);

		List<Accounts> dbAccountList = accountRepository.findAccounts(owner, company);

		if (!dbAccountList.isEmpty()) {
			accountList.addAll(dbAccountList);
		}

		return accountList;
	}
	
	
	public List<Accounts> fetchAccountNameForExpenseAndIncome(String user, String accountType) {
		List<Accounts> accountList = new ArrayList<Accounts>();
		Accounts selectAccount = new Accounts();
		selectAccount.setId(0);
		selectAccount.setAccountName("Auto-create Account");
		accountList.add(selectAccount);

		List<Accounts> dbAccountList = accountRepository.findByAccountOwnerAndAccountCompanyDetailsAndAccountType(user,
				companyDetailsRepository.findByUserNameAndCompanyActive(user, 1), accountType);

		if (!dbAccountList.isEmpty()) {
			accountList.addAll(dbAccountList);
		}

		return accountList;
	}
	
	public Accounts findByAccountNameAndAccountType(String name, String type) {
		return accountRepository.findByAccountNameAndAccountType(name, type);
	}
	
}
