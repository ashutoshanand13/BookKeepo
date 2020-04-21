/**
 * 
 */
package in.winwithweb.gst.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.Role;
import in.winwithweb.gst.model.User;
import in.winwithweb.gst.repository.RoleRepository;
import in.winwithweb.gst.repository.UserRepository;
import in.winwithweb.gst.util.CommonUtils;

/**
 * @author sachingoyal
 *
 */
@Service("userService")
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(0);
		user.setToken(CommonUtils.generateToken(user.getEmail()));
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	public void updateUser(User user) {
		userRepository.save(user);
	}

	public void deleteInActiveUser() {
		List<User> inActiveUser = userRepository.findAll();
		userRepository.deleteAll(inActiveUser);
	}

}
