/**
 * 
 */
package in.winwithweb.gst.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.Accounts;
import in.winwithweb.gst.repository.AccountRepository;

/**
 * @author sachingoyal
 *
 */

@Service("accountService")
public class AccountService {

	private AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;

	}

	public void saveAccount(Accounts account) {
		accountRepository.save(account);
	}

	public Accounts findAccountByGstin(String gst, String owner) {
		for(Accounts account : accountRepository.findByAccountOwner(owner)) {
			if(account.getGstin().equals(gst)) {
				return account;
			}
		}
		
		return null;
	}

	public Accounts findAccountByPan(String pan, String owner) {
		for(Accounts account : accountRepository.findByAccountOwner(owner)) {
			if(account.getAccountPan().equals(pan)) {
				return account;
			}
		}
		
		return null;
	}

	public List<String> fetchAccountName(String user) {
		List<Accounts> accountList = accountRepository.findAll();
		List<String> accountName = new ArrayList<>();
		for (Accounts account : accountList) {
			if(account.getAccountOwner().equals(user)) {
				accountName.add(account.getAccountName());
			}
		}
		return accountName;
	}
	
	
	public Accounts findByAccount(String accountName, String user) {
		for(Accounts account : accountRepository.findByAccountOwner(user)) {
			if(account.getAccountName().equals(accountName)) {
				return account;
			}
		}
		
		return null;
	}
}
