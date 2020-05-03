/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.BankDetails;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.repository.BankRepository;

/**
 * @author Ashutosh Anand
 *
 */
@Service("bankService")
public class BankService {

	private BankRepository bankRepository;

	@Autowired
	public BankService(BankRepository bankRepository) {
		this.bankRepository = bankRepository;

	}

	public List<BankDetails> fetchCreatedBanks(String owner, Company company) {
		List<BankDetails> createdbankslist = new ArrayList<BankDetails>();
		BankDetails newBankDetails = new BankDetails();
		newBankDetails.setId(0);
		newBankDetails.setUserBankName("Add New Bank");
		createdbankslist.add(newBankDetails);

		List<BankDetails> dbItemList = bankRepository.findByUserBankCreatorAndBankCompanyDetails(owner, company);
		if (!dbItemList.isEmpty()) {
			createdbankslist.addAll(dbItemList);
		}

		return createdbankslist;
	}

	public void saveBank(BankDetails item) {
		bankRepository.save(item);
	}

	public BankDetails findById(int id) {
		return bankRepository.findById(id);
	}
}
