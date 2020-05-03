/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.BankDetails;
import com.bookkeepo.accounting.repository.BankRepository;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;

/**
 * @author Ashutosh Anand
 *
 */
@Service("bankService")
public class BankService {

	private BankRepository bankRepository;
	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	public BankService(BankRepository bankRepository, CompanyDetailsRepository companyDetailsRepository) {
		this.bankRepository = bankRepository;
		this.companyDetailsRepository = companyDetailsRepository;

	}

	public List<BankDetails> fetchCreatedBanks(String owner) {
		List<BankDetails> createdbankslist = new ArrayList<BankDetails>();
		BankDetails newBankDetails = new BankDetails();
		newBankDetails.setId(0);
		newBankDetails.setUserBankName("Add New Bank");
		createdbankslist.add(newBankDetails);

		List<BankDetails> dbItemList = bankRepository.findByUserBankCreatorAndBankCompanyDetails(owner,
				companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1));
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
