/**
 * 
 */
package in.winwithweb.gst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.sales.InvoiceDetails;
import in.winwithweb.gst.repository.InvoiceRepository;

/**
 * @author sachingoyal
 *
 */

@Service("invoiceService")
public class InvoiceService {

	private InvoiceRepository invoiceRepository;

	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;

	}

	public void saveInvoice(InvoiceDetails invoice) {
		invoiceRepository.save(invoice);
	}

	public List<InvoiceDetails> fetchAllInvoice() {
		return invoiceRepository.findAll();
	}
	
	public List<InvoiceDetails> findByInvoiceOwner(String name) {
		return invoiceRepository.findByInvoiceOwner(name);
	}

}
