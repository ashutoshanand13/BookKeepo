package com.bookkeepo.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.model.RecordExpense;

/**
 * @author Ashutosh Anand
 *
 */
@Repository("expenseRepository")
public interface RecordExpenseRepository extends JpaRepository<RecordExpense, Long>{
	
	RecordExpense findById(int id);

}
