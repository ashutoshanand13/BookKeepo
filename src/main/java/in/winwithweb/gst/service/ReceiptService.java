/**
 * 
 */
package in.winwithweb.gst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.Receipts;
import in.winwithweb.gst.repository.ReceiptRepository;

/**
 * @author sachingoyal
 *
 */

@Service("receiptService")
public class ReceiptService {

	private ReceiptRepository receiptRepository;

	@Autowired
	public ReceiptService(ReceiptRepository receiptRepository) {
		this.receiptRepository = receiptRepository;

	}

	public void saveAccount(Receipts receipt) {
		receiptRepository.save(receipt);
	}

	
}
