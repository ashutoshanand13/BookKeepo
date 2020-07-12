package com.bookkeepo.accounting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.model.RecordExpense;
import com.bookkeepo.accounting.repository.RecordExpenseRepository;

/**
 * @author Ashutosh Anand
 *
 */
@Service("expenseService")
public class RecordExpenseService {
	
	private RecordExpenseRepository expenseRepository;
	
	@Autowired
	public RecordExpenseService(RecordExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}
	
	public void saveRecordExpenseEntry(RecordExpense recordexpense) {
		expenseRepository.save(recordexpense);
	}
	
	public RecordExpense findById(int id) {
		return expenseRepository.findById(id);
	}

}
