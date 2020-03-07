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

	public InvoiceDetails findById(int id) {
		return invoiceRepository.findById(id);
	}

	public List<String> findbyInvoiceOwnerType(String name, String type) {
		List<InvoiceDetails> allInvoice = invoiceRepository.findAll();

		return allInvoice.stream().filter(p -> p.getInvoiceOwner().equals(name) && p.getType().equals(type))
				.map(p -> p.getInvoiceNumber()).collect(Collectors.toList());
	}
	
	public InvoiceDetails findByInvoiceNumber(String invoiceNumber) {
		return invoiceRepository.findByInvoiceNumber(invoiceNumber);
	}

	public List<InvoiceDetails> findByInvoiceOwner(String name, String type) {
		List<InvoiceDetails> allInvoice = invoiceRepository.findAll();
		return allInvoice.stream().filter(p -> p.getInvoiceOwner().equals(name) && p.getType().equals(type))
				.collect(Collectors.toList());
	}
}
