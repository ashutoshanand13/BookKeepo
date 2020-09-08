/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.Receipts;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;
import com.bookkeepo.accounting.repository.ReceiptRepository;

/**
 * @author sachingoyal
 *
 */

@Service("receiptService")
public class ReceiptService {

	private ReceiptRepository receiptRepository;
	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	public ReceiptService(ReceiptRepository receiptRepository, CompanyDetailsRepository companyDetailsRepository) {
		this.receiptRepository = receiptRepository;
		this.companyDetailsRepository = companyDetailsRepository;

	}

	public void saveAccount(Receipts receipt) {
		receiptRepository.save(receipt);
	}

	public List<Receipts> fetchAllReceipt(String owner) {
		return receiptRepository.findByReceiptOwnerAndReceiptCompanyDetailsAndReceiptDeleted(owner,
				companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1), 0);
	}
	
	public Receipts findByIdAndReceiptOwner(int id, String user) {
		return receiptRepository.findByIdAndReceiptOwner(id, user);
	}

	public List<Receipts> findByAccountRefNo(Accounts account) {
		return receiptRepository.findByAccountRefNo(account);
	}
	
	public List<Receipts> findAllByReceiptCompanyDetailsAndReceiptCreationDateBetween(Company company, Date startDate, Date
			 endDate){
		return receiptRepository.findAllByReceiptCompanyDetailsAndReceiptCreationDateBetween(company,startDate,endDate);
	}
}
