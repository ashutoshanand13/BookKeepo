/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return paymentRepository.findByPaymentOwnerAndPaymentCompanyDetailsAndPaymentDeleted(owner,
				companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1), 0);
	}
	
	public Payment findByIdAndPaymentOwner(int id, String owner) {
		return paymentRepository.findByIdAndPaymentOwner(id, owner);
	}
	
	public List<Payment> findByAccountRefNo(Accounts account) {
		return paymentRepository.findByAccountRefNo(account);
	}
	
	public List<Payment> findByStartEndDate(Company company, Date startDate, Date endDate){
		return paymentRepository.findByStartEndDate(company, startDate, endDate);
	}

}
