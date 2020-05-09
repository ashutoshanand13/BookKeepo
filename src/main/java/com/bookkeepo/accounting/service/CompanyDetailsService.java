package com.bookkeepo.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;

@Service("companyDetailsService")
public class CompanyDetailsService {

	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	public CompanyDetailsService(CompanyDetailsRepository companyDetailsRepository) {
		this.companyDetailsRepository = companyDetailsRepository;
	}

	public Company findByUserName(String name) {
		return companyDetailsRepository.findByUserNameAndCompanyActive(name, 1);
	}

	public void save(Company company) {
		companyDetailsRepository.save(company);
	}

	public void saveAll(List<Company> companyList) {
		companyDetailsRepository.saveAll(companyList);
	}

	public Company findByCompanyUniqueKey(String key) {
		return companyDetailsRepository.findByCompanyUniqueKey(key);
	}

	public List<Company> fetchAllCompanies(String userName) {
		return companyDetailsRepository.findByCompanyDeletedAndUserName(0, userName);
	}

	public int getActivateCompanyId(String name) {
		return companyDetailsRepository.findByUserNameAndCompanyActive(name, 1).getId();
	}
}
