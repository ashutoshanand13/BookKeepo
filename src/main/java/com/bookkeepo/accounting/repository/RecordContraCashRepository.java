package com.bookkeepo.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.model.RecordContraCash;

/**
 * @author Ashutosh Anand
 *
 */
@Repository("recordcontracashRepository")
public interface RecordContraCashRepository extends JpaRepository<RecordContraCash, Long>{
	
	RecordContraCash findById(int id);

}
