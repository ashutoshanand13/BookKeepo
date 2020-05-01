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

	public List<BankDetails> findByProductOwner(String owner) {
		List<BankDetails> itemList = new ArrayList<>();
		BankDetails InvoiceProductDetails = new BankDetails();
		InvoiceProductDetails.setId(0);
		InvoiceProductDetails.setUserBankName("");

		List<BankDetails> dbItemList = bankRepository.findByUserBankCreator(owner);
		if (!dbItemList.isEmpty()) {
			itemList.addAll(dbItemList);
		}
		return itemList;
	}

	public List<BankDetails> fetchAllItems(String owner) {
		return bankRepository.findByUserBankCreator(owner);
	}

	public List<BankDetails> fetchCreatedBanks(String owner) {
		List<BankDetails> createdbankslist = new ArrayList<BankDetails>();
		BankDetails bankdetails = new BankDetails();
		bankdetails.setId(0);
		bankdetails.setUserBankName("Add New Bank");
		createdbankslist.add(bankdetails);

		List<BankDetails> dbItemList = bankRepository.findByUserBankCreator(owner);
		if (!dbItemList.isEmpty()) {
			createdbankslist.addAll(dbItemList);
		}

		return createdbankslist;
	}

	public void saveItem(BankDetails item) {
		bankRepository.save(item);
	}

	public BankDetails findById(int id) {
		return bankRepository.findById(id);
	}
}
