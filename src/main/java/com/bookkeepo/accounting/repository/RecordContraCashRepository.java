package com.bookkeepo.accounting.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.model.RecordContraCash;

/**
 * @author Ashutosh Anand
 *
 */
@Repository("recordcontracashRepository")
public interface RecordContraCashRepository extends JpaRepository<RecordContraCash, Long>{
	
	RecordContraCash findById(int id);
	
	@Query("SELECT o FROM RecordContraCash o WHERE o.payFrom=?1 AND o.recordContraOwner =?2 AND str_to_date(o.recordContraDate,'%d-%m-%Y') BETWEEN ?3 AND ?4")
	List<RecordContraCash> findByPayFromAndRecordContraOwnerBetweenFromAndToDate(String payFrom, String owner, Date startDate, Date endDate);
	
	@Query("SELECT o FROM RecordContraCash o WHERE o.payTo=?1 AND o.recordContraOwner =?2 AND str_to_date(o.recordContraDate,'%d-%m-%Y') BETWEEN ?3 AND ?4")
	List<RecordContraCash> findByPayToAndRecordContraOwnerBetweenFromAndToDate(String payTo, String owner, Date startDate, Date endDate);
	
	@Query("SELECT o FROM RecordContraCash o WHERE o.bankDetailsFrom=?1 AND o.recordContraOwner =?2 AND str_to_date(o.recordContraDate,'%d-%m-%Y') BETWEEN ?3 AND ?4")
	List<RecordContraCash> findByBankDetailsFromAndRecordContraOwnerBetweenFromAndToDate(Accounts payFrom, String owner, Date startDate, Date endDate);
	
	@Query("SELECT o FROM RecordContraCash o WHERE o.bankDetailsTo=?1 AND o.recordContraOwner =?2 AND str_to_date(o.recordContraDate,'%d-%m-%Y') BETWEEN ?3 AND ?4")
	List<RecordContraCash> findByBankDetailsToAndRecordContraOwnerBetweenFromAndToDate(Accounts payTo, String owner, Date startDate, Date endDate);

}
