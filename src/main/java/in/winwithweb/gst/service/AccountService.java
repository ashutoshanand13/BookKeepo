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
		for (Accounts account : accountRepository.findByAccountOwner(owner)) {
			if (account.getGstin().equals(gst)) {
				return account;
			}
		}

		return null;
	}

	public Accounts findAccountByPan(String pan, String owner) {
		for (Accounts account : accountRepository.findByAccountOwner(owner)) {
			if (account.getAccountPan().equals(pan)) {
				return account;
			}
		}

		return null;
	}

	public List<Accounts> fetchAccountName(String user) {
		List<Accounts> accountList = new ArrayList<Accounts>();
		Accounts accounts = new Accounts();
		accounts.setId(0);
		accounts.setAccountName("Select Account");
		accountList.add(accounts);

		List<Accounts> dbAccountList = accountRepository.findByAccountOwner(user);

		if (!dbAccountList.isEmpty()) {
			accountList.addAll(dbAccountList);
		}

		return accountList;
	}

	public List<Accounts> fetchAccountNameForInvoice(String user) {
		List<Accounts> accountList = accountRepository.findByAccountOwner(user);
		return accountList;
	}

	public Accounts findByGstinAndAccountOwner(String accountName, String user) {
		String gstin = accountName.split("-")[1];
		return accountRepository.findByGstinAndAccountOwner(gstin, user);
	}

	public Accounts findById(int id) {
		return accountRepository.findById(id);
	}
}
