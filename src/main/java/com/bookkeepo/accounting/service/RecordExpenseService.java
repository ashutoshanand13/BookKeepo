package com.bookkeepo.accounting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.RecordExpense;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;
import com.bookkeepo.accounting.repository.RecordExpenseRepository;

/**
 * @author Ashutosh Anand
 *
 */
@Service("expenseService")
public class RecordExpenseService {

	private RecordExpenseRepository expenseRepository;

	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	public RecordExpenseService(RecordExpenseRepository expenseRepository,
			CompanyDetailsRepository companyDetailsRepository) {
		this.expenseRepository = expenseRepository;
		this.companyDetailsRepository = companyDetailsRepository;
	}

	public void saveRecordExpenseEntry(RecordExpense recordexpense) {
		expenseRepository.save(recordexpense);
	}

	public RecordExpense findById(int id) {
		return expenseRepository.findById(id);
	}

	public List<RecordExpense> findExpenseByRecordExpenseOwner(String owner, Accounts account, Date startDate, Date endDate) {
		Company company = companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1);
		return expenseRepository.findExpenseByOwnerCompanyStartEndDate(owner, account, company, startDate, endDate);
	}
	
	public List<RecordExpense> findExpensePayableByRecordExpenseOwner(String owner, Accounts account, Date startDate, Date endDate) {
		Company company = companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1);
		return expenseRepository.findExpensePayableByOwnerCompanyStartEndDate(owner, account, company, startDate, endDate);
	}

}
