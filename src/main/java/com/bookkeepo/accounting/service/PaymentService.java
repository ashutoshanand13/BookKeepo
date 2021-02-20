/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.dtos.PaymentDto;
import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.Payment;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;
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
	public PaymentService(PaymentRepository paymentRepository, CompanyDetailsRepository companyDetailsRepository) {
		this.paymentRepository = paymentRepository;

	}

	public void saveAccount(Payment payment) {
		paymentRepository.save(payment);
	}

	public List<PaymentDto> fetchAllPayment(String owner, Company company) {
		return paymentRepository.findAllPayment(owner, company, 0);
	}

	public Payment findByIdAndPaymentOwner(int id, String owner) {
		return paymentRepository.findByIdAndPaymentOwner(id, owner);
	}

	public List<Payment> findByAccountRefNo(Accounts account) {
		return paymentRepository.findByAccountRefNo(account);
	}

	public List<Payment> findByStartEndDate(Company company, Date startDate, Date endDate) {
		return paymentRepository.findByStartEndDate(company, startDate, endDate);
	}

}
