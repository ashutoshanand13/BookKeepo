/**
 * 
 */
package in.winwithweb.gst.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.winwithweb.gst.model.User;

/**
 * @author sachingoyal
 *
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
	List<User> findByActive(int active);
}
