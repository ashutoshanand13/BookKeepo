/**
 * 
 */
package in.winwithweb.gst.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.Accounts;

/**
 * @author sachingoyal
 *
 */

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Accounts, Long> {
	
	List<Accounts> findByAccountOwner(String owner);
	
	Accounts findByAccountName(String accountName);

	Accounts findByGstinAndAccountOwner(String gstin, String user);
}
