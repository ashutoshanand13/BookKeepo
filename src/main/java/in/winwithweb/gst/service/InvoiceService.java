/**
 * 
 */
package in.winwithweb.gst.service;

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

}
