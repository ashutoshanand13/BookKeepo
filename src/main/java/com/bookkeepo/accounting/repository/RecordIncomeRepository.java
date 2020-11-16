package com.bookkeepo.accounting.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.RecordIncome;

/**
 * @author Ashutosh Anand
 *
 */
@Repository("incomeRepository")
public interface RecordIncomeRepository extends JpaRepository<RecordIncome, Long>{
	
	RecordIncome findById(int id);

	@Query("SELECT o FROM RecordIncome o WHERE o.recordIncomeOwner = ?1 AND o.incomeWithAccountReference = ?2 AND o.incomeCompanyDetails = ?3 AND str_to_date(o.incomeDate,'%d-%m-%Y') BETWEEN ?4 AND ?5")
	List<RecordIncome> findIncomeByOwnerCompanyStartEndDate(String owner, Accounts account, Company company, Date startDate, Date endDate);
	
	@Query("SELECT o FROM RecordIncome o WHERE o.recordIncomeOwner = ?1 AND o.incomePayableWithAccountReference = ?2 AND o.incomeCompanyDetails = ?3 AND str_to_date(o.incomeDate,'%d-%m-%Y') BETWEEN ?4 AND ?5")
	List<RecordIncome> findIncomePayableByOwnerCompanyStartEndDate(String owner, Accounts account, Company company, Date startDate, Date endDate);
	
}
