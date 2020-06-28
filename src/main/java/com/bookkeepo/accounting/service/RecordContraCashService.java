package com.bookkeepo.accounting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.model.RecordContraCash;
import com.bookkeepo.accounting.repository.RecordContraCashRepository;

/**
 * @author Ashutosh Anand
 *
 */
@Service("contracashService")
public class RecordContraCashService {
	
	private RecordContraCashRepository recordcontracashRepository;
	
	@Autowired
	public RecordContraCashService(RecordContraCashRepository recordcontracashRepository){
		this.recordcontracashRepository = recordcontracashRepository;

	}
	
	public void saveRecordContraCashEntry(RecordContraCash recordContraCash) {
		recordcontracashRepository.save(recordContraCash);
	}

	public RecordContraCash findById(int id) {
		return recordcontracashRepository.findById(id);
	}

}
