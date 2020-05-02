/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Company;

/**
 * @author Yash Singh
 *
 */
@Repository("companyDetailsRepository")
public interface CompanyDetailsRepository extends JpaRepository<Company, Long> {
	List<Company> findByUserName(String name);
	
	Company findByCompanyUniqueKey(String key);
	
	List<Company> findByCompanyDeleted(int companyDeleted);
}
