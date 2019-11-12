/**
 * 
 */
package in.winwithweb.gst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.Accounts;

/**
 * @author sachingoyal
 *
 */

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Accounts, Long> {
	
	Accounts findByGstin(String gstin);
	
	Accounts findByAccountPan(String pan);
	
	Accounts findByAccountName(String accountName);


}
