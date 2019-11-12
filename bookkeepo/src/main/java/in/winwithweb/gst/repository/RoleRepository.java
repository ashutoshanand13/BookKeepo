/**
 * 
 */
package in.winwithweb.gst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.Role;

/**
 * @author sachingoyal
 *
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}
