/**
 * 
 */
package in.winwithweb.gst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.Company;

/**
 * @author Yash Singh
 *
 */
@Repository("companyDetailsRepository")
public interface CompanyDetailsRepository extends JpaRepository<Company, Long>{
	Company findByUserName(String name);
}
