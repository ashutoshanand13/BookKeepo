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

	public Accounts findAccountByGstin(String gstin) {
		return accountRepository.findByGstin(gstin);
	}

	public Accounts findAccountByPan(String pan) {
		return accountRepository.findByAccountPan(pan);
	}

	public List<String> fetchAccountName() {
		List<Accounts> accountList = accountRepository.findAll();
		List<String> accountName = new ArrayList<String>();
		accountName.add("Select Account");
		for (Accounts account : accountList) {
			accountName.add(account.getAccountName());
		}
		return accountName;
	}

	public String findGSTByAccountName(String accountName) {
		Accounts account = accountRepository.findByAccountName(accountName);
		if (account != null) {
			return account.getGstin();
		} else {
			return "No GST Found";
		}
	}
	
	public String findPanByAccountName(String accountName) {
		Accounts account = accountRepository.findByAccountName(accountName);
		if (account != null) {
			return account.getAccountPan();
		} else {
			return "No Pan Found";
		}
	}
}
