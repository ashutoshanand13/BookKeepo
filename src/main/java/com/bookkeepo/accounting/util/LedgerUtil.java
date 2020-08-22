/**
 * 
 */
package com.bookkeepo.accounting.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.entity.Payment;
import com.bookkeepo.accounting.entity.Receipts;
import com.bookkeepo.accounting.model.LedgerColumns;
import com.bookkeepo.accounting.model.LedgerInfo;
import com.bookkeepo.accounting.service.AccountLedgerService;
import com.bookkeepo.accounting.service.PaymentService;
import com.bookkeepo.accounting.service.ReceiptService;

/**
 * @author Yash Singh
 *
 */
@Component
public class LedgerUtil{
	
	private static ReceiptService receipt;
	private static PaymentService payment;
	private static AccountLedgerService accountLedger;
	
	private static String DEBIT = "Dr";

	@Autowired
	ReceiptService receiptService;

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	AccountLedgerService accountLedgerService;

    @PostConstruct
    public void init() {
    	LedgerUtil.receipt = receiptService;
    	LedgerUtil.payment = paymentService;
    	LedgerUtil.accountLedger = accountLedgerService;
    }

	public static Map<Accounts, List<LedgerColumns>> setUpLedgers(List<InvoiceDetails> invoices, LedgerInfo ledger) {
		
		Map<Accounts, List<LedgerColumns>> ledgerMap = new HashMap<Accounts, List<LedgerColumns>>();
		
		for (InvoiceDetails invoice : nullGuard(invoices)) {
			if (InvoiceUtil.isValidDate(ledger.getStartDate(), ledger.getEndDate(),
					CommonUtils.convertDateIntoFormat(invoice.getInvoiceDate()))) {
				
				List<LedgerColumns> invoiceData;
				
				if(ledgerMap.get(invoice.getInvoiceAccountDetails()) == null) {
					invoiceData = new ArrayList<LedgerColumns>();
					LedgerColumns column = new LedgerColumns();
					column.setParticulars(invoice.getInvoiceType());
					column.setDate(invoice.getInvoiceDate());
					if(DEBIT.equals(accountLedger.findByAccountType(ledger.getAccountType()).getAccountpostive())){
						column.setDebit(invoice.getInvoiceTotalAmountAfterTax());
					} else {
						column.setCredit(invoice.getInvoiceTotalAmountAfterTax());
					}
					
					invoiceData.add(column);
					ledgerMap.put(invoice.getInvoiceAccountDetails(), invoiceData);
				} else {
					invoiceData = ledgerMap.get(invoice.getInvoiceAccountDetails());
					LedgerColumns column = new LedgerColumns();
					column.setParticulars(invoice.getInvoiceType());
					column.setDate(invoice.getInvoiceDate());
					if(DEBIT.equals(accountLedger.findByAccountType(ledger.getAccountType()).getAccountpostive())){
						column.setDebit(invoice.getInvoiceTotalAmountAfterTax());
					} else {
						column.setCredit(invoice.getInvoiceTotalAmountAfterTax());
					}
					
					invoiceData.add(column);
					ledgerMap.put(invoice.getInvoiceAccountDetails(), invoiceData);
				}
				
			}
			
		}
		for(Accounts account : nullGuard(ledgerMap.keySet())) {
			List<Receipts> accountReceipt = receipt.findByAccountRefNo(account);
			List<Payment> accountPayment = payment.findByAccountRefNo(account);
			
			for(Receipts ledgerReceipt : nullGuard(accountReceipt)) {
				
				if (InvoiceUtil.isValidDate(ledger.getStartDate(), ledger.getEndDate(),
						CommonUtils.convertDateIntoFormat(ledgerReceipt.getReceiptDate()))) {
					
					List<LedgerColumns> receiptData;
					
					if(ledgerMap.get(ledgerReceipt.getAccountRefNo()) == null) {
						receiptData = new ArrayList<LedgerColumns>();
						LedgerColumns column = new LedgerColumns();
						column.setParticulars(ledgerReceipt.getReceiptMode());
						column.setDate(ledgerReceipt.getReceiptDate());
						column.setCredit(ledgerReceipt.getReceiptAmount());
						
						receiptData.add(column);
						ledgerMap.put(ledgerReceipt.getAccountRefNo(), receiptData);
					} else {
						receiptData = ledgerMap.get(ledgerReceipt.getAccountRefNo());
						LedgerColumns column = new LedgerColumns();
						column.setParticulars(ledgerReceipt.getReceiptMode());
						column.setDate(ledgerReceipt.getReceiptDate());
						column.setCredit(ledgerReceipt.getReceiptAmount());
						
						receiptData.add(column);
						ledgerMap.put(ledgerReceipt.getAccountRefNo(), receiptData);
					}
					
				}
				
			}	
			
			for(Payment ledgerPayment : nullGuard(accountPayment)) {
				
				if (InvoiceUtil.isValidDate(ledger.getStartDate(), ledger.getEndDate(),
						CommonUtils.convertDateIntoFormat(ledgerPayment.getPaymentDate()))) {
					
					List<LedgerColumns> paymentData;
					
					if(ledgerMap.get(ledgerPayment.getAccountRefNo()) == null) {
						paymentData = new ArrayList<LedgerColumns>();
						LedgerColumns column = new LedgerColumns();
						column.setParticulars(ledgerPayment.getPaymentMode());
						column.setDate(ledgerPayment.getPaymentDate());
						column.setDebit(ledgerPayment.getPaymentAmount());
						
						paymentData.add(column);
						ledgerMap.put(ledgerPayment.getAccountRefNo(), paymentData);
					} else {
						paymentData = ledgerMap.get(ledgerPayment.getAccountRefNo());
						LedgerColumns column = new LedgerColumns();
						column.setParticulars(ledgerPayment.getPaymentMode());
						column.setDate(ledgerPayment.getPaymentDate());
						column.setDebit(ledgerPayment.getPaymentAmount());
						
						paymentData.add(column);
						ledgerMap.put(ledgerPayment.getAccountRefNo(), paymentData);
					}
					
				}
				
			}
		}
		
		return ledgerMap;
		
	}
	
	/**
	 * Method to avoid iterating over null list
	 * 
	 * @param <T>
	 * @param item
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends Iterable> T nullGuard(T item) {
		  if (item == null) {
		    return (T) Collections.emptyList();
		  } else {
		    return item;
		  }
		}

}
