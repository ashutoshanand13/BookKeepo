package com.bookkeepo.accounting.service;

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
		return companyDetailsRepository.findByUserName(name);
	}
	
    public void save(Company company) {
    	companyDetailsRepository.save(company);
    }
}
