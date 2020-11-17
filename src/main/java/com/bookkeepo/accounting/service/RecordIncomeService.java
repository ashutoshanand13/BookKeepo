package com.bookkeepo.accounting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.RecordIncome;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;
import com.bookkeepo.accounting.repository.RecordIncomeRepository;

/**
 * @author Ashutosh Anand
 *
 */
@Service("incomeService")
public class RecordIncomeService {

	private RecordIncomeRepository incomeRepository;

	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	public RecordIncomeService(RecordIncomeRepository incomeRepository,
			CompanyDetailsRepository companyDetailsRepository) {
		this.incomeRepository = incomeRepository;
		this.companyDetailsRepository = companyDetailsRepository;
	}

	public void saveRecordIncomeEntry(RecordIncome recordincome) {
		incomeRepository.save(recordincome);
	}

	public RecordIncome findById(int id) {
		return incomeRepository.findById(id);
	}
	
	public List<RecordIncome> findIncomeByRecordIncomeOwner(String owner, Accounts account, Date startDate, Date endDate) {
		Company company = companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1);
		return incomeRepository.findIncomeByOwnerCompanyStartEndDate(owner, account, company, startDate, endDate);
	}
	
	public List<RecordIncome> findIncomePayableByRecordIncomeOwner(String owner, Accounts account, Date startDate, Date endDate) {
		Company company = companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1);
		return incomeRepository.findIncomePayableByOwnerCompanyStartEndDate(owner, account, company, startDate, endDate);
	}

}
