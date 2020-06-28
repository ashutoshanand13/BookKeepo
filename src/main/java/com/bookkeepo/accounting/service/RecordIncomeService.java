package com.bookkeepo.accounting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.model.RecordIncome;
import com.bookkeepo.accounting.repository.RecordIncomeRepository;

/**
 * @author Ashutosh Anand
 *
 */
@Service("incomeService")
public class RecordIncomeService {
	

	
	private RecordIncomeRepository incomeRepository;
	
	@Autowired
	public RecordIncomeService(RecordIncomeRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}
	
	public void saveRecordIncomeEntry(RecordIncome recordincome) {
		incomeRepository.save(recordincome);
	}
	
	public RecordIncome findById(int id) {
		return incomeRepository.findById(id);
	}



}
