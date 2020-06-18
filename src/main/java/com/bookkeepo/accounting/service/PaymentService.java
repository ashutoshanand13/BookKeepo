/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookkeepo.accounting.entity.Payment;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;
import com.bookkeepo.accounting.repository.PaymentRepository;

/**
 * 
 * @author Ashutosh
 *
 */

@Service("paymentService")
@Transactional
public class PaymentService {

	private PaymentRepository paymentRepository;
	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository, CompanyDetailsRepository companyDetailsRepository) {
		this.paymentRepository = paymentRepository;
		this.companyDetailsRepository = companyDetailsRepository;

	}

	public void saveAccount(Payment payment) {
		paymentRepository.save(payment);
	}

	public List<Payment> fetchAllPayment(String owner) {
		return paymentRepository.findByPaymentOwnerAndPaymentCompanyDetails(owner,
				companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1));
	}
	
	public void deleteUsersByIDAndUser(int id, String user) {
		paymentRepository.deleteUsersByIDAndUser(id, user);
	}

}
