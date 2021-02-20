/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.dtos.ReceiptDto;
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

	@Autowired
	public ReceiptService(ReceiptRepository receiptRepository, CompanyDetailsRepository companyDetailsRepository) {
		this.receiptRepository = receiptRepository;

	}

	public void saveAccount(Receipts receipt) {
		receiptRepository.save(receipt);
	}

	public List<ReceiptDto> fetchAllReceipt(String owner, Company company) {
		return receiptRepository.findAllReceipt(owner, company, 0);
	}

	public Receipts findByIdAndReceiptOwner(int id, String user) {
		return receiptRepository.findByIdAndReceiptOwner(id, user);
	}

	public List<Receipts> findByAccountRefNo(Accounts account) {
		return receiptRepository.findByAccountRefNo(account);
	}

	public List<Receipts> findByStartEndDate(Company company, Date startDate, Date endDate) {
		return receiptRepository.findByStartEndDate(company, startDate, endDate);
	}
}
