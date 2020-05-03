/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.model.InvoiceData;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;
import com.bookkeepo.accounting.repository.InvoiceRepository;

/**
 * @author sachingoyal
 *
 */

@Service("invoiceService")
public class InvoiceService {

	private InvoiceRepository invoiceRepository;

	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepository, CompanyDetailsRepository companyDetailsRepository) {
		this.invoiceRepository = invoiceRepository;
		this.companyDetailsRepository = companyDetailsRepository;

	}

	public void saveInvoice(InvoiceDetails invoice) {
		invoiceRepository.save(invoice);
	}

	public List<InvoiceDetails> findByInvoiceOwner(String name) {
		return invoiceRepository.findByInvoiceOwnerAndInvoiceCompanyDetails(name,
				companyDetailsRepository.findByUserNameAndCompanyActive(name, 1));
	}

	public InvoiceDetails findByInvoiceUniqueKey(String key) {
		return invoiceRepository.findByInvoiceUniqueKey(key);
	}

	public InvoiceDetails findById(int id) {
		return invoiceRepository.findById(id);
	}

	public List<InvoiceData> findByInvoiceOwnerAndInvoiceType(String name, String type) {
		List<InvoiceData> invoiceList = new ArrayList<InvoiceData>();
		InvoiceData selectInvoice = new InvoiceData();
		selectInvoice.setId(0);
		selectInvoice.setInvoiceNumber("Select Against Invoice");
		invoiceList.add(selectInvoice);
		List<InvoiceDetails> allInvoice = invoiceRepository.findByInvoiceOwnerAndInvoiceTypeAndInvoiceCompanyDetails(
				name, type, companyDetailsRepository.findByUserNameAndCompanyActive(name, 1));
		for (InvoiceDetails invoice : allInvoice) {
			InvoiceData dbinvoice = new InvoiceData();
			dbinvoice.setId(invoice.getId());
			dbinvoice.setInvoiceNumber(invoice.getInvoiceNumber());
			invoiceList.add(dbinvoice);
		}
		return invoiceList;
	}

	public InvoiceDetails findByInvoiceNumberAndInvoiceOwnerAndInvoiceType(String invoiceNo, String type,
			String owner) {
		return invoiceRepository.findByInvoiceNumberAndInvoiceOwnerAndInvoiceTypeAndInvoiceCompanyDetails(invoiceNo,
				owner, type, companyDetailsRepository.findByUserNameAndCompanyActive(owner, 1));
	}
}
