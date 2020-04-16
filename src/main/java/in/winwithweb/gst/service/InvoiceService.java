/**
 * 
 */
package in.winwithweb.gst.service;

import java.util.List;
import java.util.stream.Collectors;

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

	public InvoiceDetails findById(String key) {
		return invoiceRepository.findByInvoiceUniqueKey(key);
	}

	public List<String> findByInvoiceOwnerAndInvoiceType(String name, String type) {
		List<InvoiceDetails> allInvoice = invoiceRepository.findByInvoiceOwnerAndInvoiceType(name, type);
		return allInvoice.stream().map(p -> p.getInvoiceNumber()).collect(Collectors.toList());
	}

	public InvoiceDetails findByInvoiceNumberAndInvoiceOwner(String invoiceNumber, String owner) {
		return invoiceRepository.findByInvoiceNumberAndInvoiceOwner(invoiceNumber, owner);
	}

	public InvoiceDetails findByInvoiceNumberAndInvoiceOwnerAndInvoiceType(String invoiceNo, String type, String owner) {
		return invoiceRepository.findByInvoiceNumberAndInvoiceOwnerAndInvoiceType(invoiceNo, owner, type);
	}
}
