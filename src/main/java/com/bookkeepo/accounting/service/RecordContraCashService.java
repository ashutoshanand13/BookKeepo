package com.bookkeepo.accounting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.Accounts;
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
	public RecordContraCashService(RecordContraCashRepository recordcontracashRepository) {
		this.recordcontracashRepository = recordcontracashRepository;

	}

	public void saveRecordContraCashEntry(RecordContraCash recordContraCash) {
		recordcontracashRepository.save(recordContraCash);
	}

	public RecordContraCash findById(int id) {
		return recordcontracashRepository.findById(id);
	}

	public List<RecordContraCash> findByPayFromAndRecordContraOwner(String payFrom, String owner, Date startDate,
			Date endDate) {
		return recordcontracashRepository.findByPayFromAndRecordContraOwnerBetweenFromAndToDate(payFrom, owner,
				startDate, endDate);
	}

	public List<RecordContraCash> findByPayToAndRecordContraOwner(String payTo, String owner, Date startDate,
			Date endDate) {
		return recordcontracashRepository.findByPayToAndRecordContraOwnerBetweenFromAndToDate(payTo, owner, startDate,
				endDate);
	}

	public List<RecordContraCash> findByBankDetailsFromAndRecordContraOwner(Accounts payFrom, String owner,
			Date startDate, Date endDate) {
		return recordcontracashRepository.findByBankDetailsFromAndRecordContraOwnerBetweenFromAndToDate(payFrom, owner,
				startDate, endDate);
	}

	public List<RecordContraCash> findByBankDetailsToAndRecordContraOwner(Accounts payTo, String owner, Date startDate,
			Date endDate) {
		return recordcontracashRepository.findByBankDetailsToAndRecordContraOwnerBetweenFromAndToDate(payTo, owner,
				startDate, endDate);
	}
}
