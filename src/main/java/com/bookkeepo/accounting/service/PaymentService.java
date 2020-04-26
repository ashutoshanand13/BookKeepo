/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.Payment;
import com.bookkeepo.accounting.repository.PaymentRepository;

/**
 * 
 * @author Ashutosh
 *
 */

@Service("paymentService")
public class PaymentService {

	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;

	}

	public void saveAccount(Payment payment) {
		paymentRepository.save(payment);
	}

	public List<Payment> fetchAllPayment(String owner) {
		return paymentRepository.findByPaymentOwner(owner);
	}
	
	public Payment findByPaymentNumberAndPaymentOwner(String paymentNo, String owner) {
		return paymentRepository.findByPaymentNumberAndPaymentOwner(paymentNo,owner);
	}

}
