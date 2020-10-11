/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.entity.InvoiceNumber;
import com.bookkeepo.accounting.model.InvoiceData;
import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.repository.CompanyDetailsRepository;
import com.bookkeepo.accounting.repository.InvoiceNumberRepository;
import com.bookkeepo.accounting.repository.InvoiceRepository;
import com.bookkeepo.accounting.util.InvoiceUtil;

/**
 * @author sachingoyal
 *
 */

@Service("invoiceService")
public class InvoiceService {

	private InvoiceRepository invoiceRepository;

	private CompanyDetailsRepository companyDetailsRepository;

	private InvoiceNumberRepository invoiceNumberRepository;

	public static final String TAX_Invoice = InvoiceType.Tax_Invoice.getType();

	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepository, CompanyDetailsRepository companyDetailsRepository,
			InvoiceNumberRepository invoiceNumberRepository) {
		this.invoiceRepository = invoiceRepository;
		this.companyDetailsRepository = companyDetailsRepository;
		this.invoiceNumberRepository = invoiceNumberRepository;

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

	public String getInvoiceNumber(String email, String invoiceType) {
		String invoiceNumber = null;
		int newInvoiceNbr = 0;
		InvoiceNumber invoiceNumberDate = invoiceNumberRepository.findByName(email);
		switch (invoiceType) {
		case "Tax Invoice":
			newInvoiceNbr = invoiceNumberDate.getInvoiceNumberTaxInvoice() + 1;
			invoiceNumber = InvoiceUtil.getFinancialYear() + "/" + "TI/" + newInvoiceNbr;
			invoiceNumberDate.setInvoiceNumberTaxInvoice(newInvoiceNbr);
			invoiceNumberRepository.save(invoiceNumberDate);
			break;
		case "Export Invoice":
			newInvoiceNbr = invoiceNumberDate.getInvoiceNumberExportInvoice() + 1;
			invoiceNumber = InvoiceUtil.getFinancialYear() + "/" + "EI/" + newInvoiceNbr;
			invoiceNumberDate.setInvoiceNumberExportInvoice(newInvoiceNbr);
			invoiceNumberRepository.save(invoiceNumberDate);
			break;

		case "Credit Note":
			newInvoiceNbr = invoiceNumberDate.getInvoiceNumberCreditNote() + 1;
			invoiceNumber = InvoiceUtil.getFinancialYear() + "/" + "CN/" + newInvoiceNbr;
			invoiceNumberDate.setInvoiceNumberCreditNote(newInvoiceNbr);
			invoiceNumberRepository.save(invoiceNumberDate);
			break;

		case "Debit Note":
			newInvoiceNbr = invoiceNumberDate.getInvoiceNumberDebitNote() + 1;
			invoiceNumber = InvoiceUtil.getFinancialYear() + "/" + "DN/" + newInvoiceNbr;
			invoiceNumberDate.setInvoiceNumberDebitNote(newInvoiceNbr);
			invoiceNumberRepository.save(invoiceNumberDate);
			break;

		case "Purchase Invoice":
			newInvoiceNbr = invoiceNumberDate.getInvoiceNumberPurchaseInvoice() + 1;
			invoiceNumber = InvoiceUtil.getFinancialYear() + "/" + "PI/" + newInvoiceNbr;
			invoiceNumberDate.setInvoiceNumberPurchaseInvoice(newInvoiceNbr);
			invoiceNumberRepository.save(invoiceNumberDate);
			break;

		case "Purchase Order":
			newInvoiceNbr = invoiceNumberDate.getInvoiceNumberPurchaseOrder() + 1;
			invoiceNumber = InvoiceUtil.getFinancialYear() + "/" + "PO/" + newInvoiceNbr;
			invoiceNumberDate.setInvoiceNumberPurchaseOrder(newInvoiceNbr);
			invoiceNumberRepository.save(invoiceNumberDate);
			break;

		case "Bill of Supply":
			newInvoiceNbr = invoiceNumberDate.getInvoiceNumberBillSupply() + 1;
			invoiceNumber = InvoiceUtil.getFinancialYear() + "/" + "BOS/" + newInvoiceNbr;
			invoiceNumberDate.setInvoiceNumberBillSupply(newInvoiceNbr);
			invoiceNumberRepository.save(invoiceNumberDate);
			break;
		default:
			newInvoiceNbr = invoiceNumberDate.getInvoiceNumberDefault() + 1;
			invoiceNumber = InvoiceUtil.getFinancialYear() + "/" + "DEF/" + newInvoiceNbr;
			invoiceNumberDate.setInvoiceNumberDefault(newInvoiceNbr);
			invoiceNumberRepository.save(invoiceNumberDate);
			break;
		}

		return invoiceNumber;

	}

	public List<InvoiceDetails> findByInvoiceAccountDetailsAndInvoiceOwner(Accounts account, String owner) {
		List<InvoiceDetails> invoiceList = invoiceRepository.findByInvoiceAccountDetailsAndInvoiceOwner(account, owner);

		if (!invoiceList.isEmpty()) {
			InvoiceDetails temp = new InvoiceDetails();
			temp.setInvoiceUniqueKey("0");
			temp.setInvoiceNumber("Select");
			invoiceList.add(0, temp);
			return invoiceList;
		}

		return null;
	}
	
	public List<InvoiceDetails> findByAccountTypeAndAccountOwner(String owner, String accountType){
		return invoiceRepository.findByAccountTypeAndAccountOwner(owner, accountType);
	}
}
