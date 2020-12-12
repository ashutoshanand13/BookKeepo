package com.bookkeepo.accounting.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.model.RecordExpense;

/**
 * @author Ashutosh Anand
 *
 */
@Repository("expenseRepository")
public interface RecordExpenseRepository extends JpaRepository<RecordExpense, Long>{
	
	RecordExpense findById(int id);

	@Query("SELECT o FROM RecordExpense o WHERE o.recordExpenseOwner = ?1 AND o.expenseWithAccountReference = ?2 AND o.expenseCompanyDetails = ?3 AND str_to_date(o.expenseDate,'%d-%m-%Y') BETWEEN ?4 AND ?5")
	List<RecordExpense> findExpenseByOwnerCompanyStartEndDate(String owner, Accounts account, Company company, Date startDate, Date endDate);
	
	@Query("SELECT o FROM RecordExpense o WHERE o.recordExpenseOwner = ?1 AND o.expensePayableWithAccountReference = ?2 AND o.expenseCompanyDetails = ?3 AND str_to_date(o.expenseDate,'%d-%m-%Y') BETWEEN ?4 AND ?5")
	List<RecordExpense> findExpensePayableByOwnerCompanyStartEndDate(String owner, Accounts account, Company company, Date startDate, Date endDate);
}
