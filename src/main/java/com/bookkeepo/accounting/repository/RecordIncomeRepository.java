package com.bookkeepo.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.model.RecordIncome;

/**
 * @author Ashutosh Anand
 *
 */
@Repository("incomeRepository")
public interface RecordIncomeRepository extends JpaRepository<RecordIncome, Long>{
	
	RecordIncome findById(int id);

}
