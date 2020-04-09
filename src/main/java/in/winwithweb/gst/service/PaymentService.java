/**
 * 
 */
package in.winwithweb.gst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.Payment;
import in.winwithweb.gst.repository.PaymentRepository;

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

	
}
