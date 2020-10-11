/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.InvoiceNumber;
import com.bookkeepo.accounting.entity.Role;
import com.bookkeepo.accounting.entity.User;
import com.bookkeepo.accounting.repository.InvoiceNumberRepository;
import com.bookkeepo.accounting.repository.RoleRepository;
import com.bookkeepo.accounting.repository.UserRepository;
import com.bookkeepo.accounting.util.CommonUtils;

/**
 * @author sachingoyal
 *
 */
@Service("userService")
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private InvoiceNumberRepository invoiceNumberRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			InvoiceNumberRepository invoiceNumberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.invoiceNumberRepository = invoiceNumberRepository;
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
		invoiceNumberRepository.save(new InvoiceNumber(user.getEmail()));
	}

	public void updateUser(User user) {
		userRepository.save(user);
	}

	public void deleteInActiveUser() {
		List<User> inActiveUser = userRepository.findByActive(0);
		userRepository.deleteAll(inActiveUser);
	}

}
