/**
 * 
 */
package in.winwithweb.gst.service;

import java.util.List;

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

	public List<Receipts> fetchAllReceipt(String owner) {
		return receiptRepository.findByReceiptOwner(owner);
	}

}
