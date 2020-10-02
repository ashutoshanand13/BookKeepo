/**
 * 
 */
package com.bookkeepo.accounting.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookkeepo.accounting.entity.Accounts;
import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.InvoiceDetails;
import com.bookkeepo.accounting.entity.Payment;
import com.bookkeepo.accounting.entity.Receipts;
import com.bookkeepo.accounting.model.InvoiceType;
import com.bookkeepo.accounting.model.LedgerColumns;
import com.bookkeepo.accounting.model.LedgerInfo;
import com.bookkeepo.accounting.service.AccountLedgerService;
import com.bookkeepo.accounting.service.InvoiceService;
import com.bookkeepo.accounting.service.PaymentService;
import com.bookkeepo.accounting.service.ReceiptService;

/**
 * @author Yash Singh
 *
 */
@Component
public class LedgerUtil {

	private static ReceiptService receipt;
	private static PaymentService payment;
	private static AccountLedgerService accountLedger;
	private static InvoiceService invoice;

	private static String DEBIT = "Dr";

	@Autowired
	ReceiptService receiptService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	AccountLedgerService accountLedgerService;

	@Autowired
	InvoiceService invoiceService;

	@PostConstruct
	public void init() {
		LedgerUtil.receipt = receiptService;
		LedgerUtil.payment = paymentService;
		LedgerUtil.accountLedger = accountLedgerService;
		LedgerUtil.invoice = invoiceService;
	}

	public static Map<Accounts, List<LedgerColumns>> setUpLedgers(Accounts account, LedgerInfo ledger) {

		Map<Accounts, List<LedgerColumns>> ledgerMap = new HashMap<Accounts, List<LedgerColumns>>();
		List<InvoiceDetails> invoices = invoice.findByInvoiceAccountDetails(account);

		for (InvoiceDetails invoice : nullGuard(invoices)) {
			if (InvoiceUtil.isValidDate(ledger.getStartDate(), ledger.getEndDate(),
					CommonUtils.convertDateIntoFormat(invoice.getInvoiceDate())) && invoice.getId() != 0) {
				List<LedgerColumns> invoiceData;

				if (ledgerMap.get(invoice.getInvoiceAccountDetails()) == null) {
					invoiceData = new ArrayList<LedgerColumns>();
					LedgerColumns column = new LedgerColumns();
					column.setParticulars(invoice.getInvoiceType());
					column.setDate(invoice.getInvoiceDate());
					if ((DEBIT.equals(accountLedger.findByAccountType(account.getAccountType()).getAccountpostive())
							&& !invoice.getInvoiceType().equals(InvoiceType.Credit_Note.getType()))
							|| invoice.getInvoiceType().equals(InvoiceType.Debit_Note.getType())) {
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
					if ((DEBIT.equals(accountLedger.findByAccountType(account.getAccountType()).getAccountpostive())
							&& !invoice.getInvoiceType().equals(InvoiceType.Credit_Note.getType()))
							|| invoice.getInvoiceType().equals(InvoiceType.Debit_Note.getType())) {
						column.setDebit(invoice.getInvoiceTotalAmountAfterTax());
					} else {
						column.setCredit(invoice.getInvoiceTotalAmountAfterTax());
					}

					invoiceData.add(column);
					ledgerMap.put(invoice.getInvoiceAccountDetails(), invoiceData);
				}

			}

		}

		List<Receipts> accountReceipt = receipt.findByAccountRefNo(account);
		List<Payment> accountPayment = payment.findByAccountRefNo(account);

		for (Receipts ledgerReceipt : nullGuard(accountReceipt)) {

			if (InvoiceUtil.isValidDate(ledger.getStartDate(), ledger.getEndDate(),
					CommonUtils.convertDateIntoFormat(ledgerReceipt.getReceiptDate()))) {

				List<LedgerColumns> receiptData;

				if (ledgerMap.get(ledgerReceipt.getAccountRefNo()) == null) {
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
					ledgerMap.put(account, receiptData);
				}

			}

		}

		for (Payment ledgerPayment : nullGuard(accountPayment)) {

			if (InvoiceUtil.isValidDate(ledger.getStartDate(), ledger.getEndDate(),
					CommonUtils.convertDateIntoFormat(ledgerPayment.getPaymentDate()))) {

				List<LedgerColumns> paymentData;

				if (ledgerMap.get(ledgerPayment.getAccountRefNo()) == null) {
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
					ledgerMap.put(account, paymentData);
				}

			}

		}

		List<LedgerColumns> particulars = ledgerMap.get(account);
		double creditSum = 0, debitSum = 0;
		for (LedgerColumns column : nullGuard(particulars)) {
			creditSum += column.getCredit() != null ? Double.valueOf(column.getCredit()) : 0;
			debitSum += column.getDebit() != null ? Double.valueOf(column.getDebit()) : 0;
		}
		LedgerColumns balance = new LedgerColumns();
		balance.setBalance(String.valueOf(debitSum - creditSum));
		balance.setParticulars("Balance");
		particulars.add(balance);
		ledgerMap.put(account, particulars);

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

	public static Map<Accounts, List<LedgerColumns>> setUpCashAndBankLedgers(Accounts account, LedgerInfo ledger,
			Company company) {
		Date startDate = null;
		Date endDate = null;
		Map<Accounts, List<LedgerColumns>> ledgerMap = new HashMap<Accounts, List<LedgerColumns>>();
		List<LedgerColumns> ledgerData = new ArrayList<LedgerColumns>();
		LedgerColumns ledgerColumnData = null;

		try {
			startDate = CommonUtils.convertToDate(ledger.getStartDate());
			endDate = CommonUtils.convertToDate(ledger.getEndDate());
			endDate.setTime(endDate.getTime() + 86399000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Payment> listofPayments = payment.findByStartEndDate(company, startDate, endDate);
		List<Receipts> listofReceipts = receipt.findByStartEndDate(company, startDate, endDate);

		Double debitSum = 0.0;
		Double creditSum = 0.0;

		for (Payment ledgerPayment : nullGuard(listofPayments)) {
			ledgerColumnData = new LedgerColumns();
			if (ledgerPayment.getPaymentMode().equals("Cash")
					&& account.getAccountType().equals(Constants.DEFAULT_ACCOUNT_ON_COMPANY_CREATION)) {
				ledgerColumnData.setParticulars("Payment-" + ledgerPayment.getAccountRefNo().getAccountName() + "-"
						+ ledgerPayment.getPaymentMode());
				ledgerColumnData.setDate(ledgerPayment.getPaymentDate());
				ledgerColumnData.setCredit(ledgerPayment.getPaymentAmount());
				creditSum += Double.valueOf(ledgerPayment.getPaymentAmount());
				ledgerData.add(ledgerColumnData);
			} else if (ledgerPayment.getPaymentMode().equals("Bank")
					&& account.getAccountType().equals(Constants.DEFAULT_ACCOUNT_ON_BANK_CREATION)
					&& account.getAccountName().equals(ledgerPayment.getBankDetails().getUserBankName())) {
				ledgerColumnData.setParticulars("Payment-" + ledgerPayment.getAccountRefNo().getAccountName() + "-"
						+ ledgerPayment.getPaymentMode());
				ledgerColumnData.setDate(ledgerPayment.getPaymentDate());
				ledgerColumnData.setCredit(ledgerPayment.getPaymentAmount());
				creditSum += Double.valueOf(ledgerPayment.getPaymentAmount());
				ledgerData.add(ledgerColumnData);
			}
		}

		for (Receipts ledgerReceipt : nullGuard(listofReceipts)) {
			if (ledgerReceipt.getReceiptMode().equals("Cash")
					&& account.getAccountType().equals(Constants.DEFAULT_ACCOUNT_ON_COMPANY_CREATION)) {
				ledgerColumnData = new LedgerColumns();
				ledgerColumnData.setParticulars("Receipt-" + ledgerReceipt.getAccountRefNo().getAccountName() + "-"
						+ ledgerReceipt.getReceiptMode());
				ledgerColumnData.setDate(ledgerReceipt.getReceiptDate());
				ledgerColumnData.setDebit(ledgerReceipt.getReceiptAmount());
				debitSum += Double.valueOf(ledgerReceipt.getReceiptAmount());
				ledgerData.add(ledgerColumnData);
			} else if (ledgerReceipt.getReceiptMode().equals("Bank")
					&& account.getAccountType().equals(Constants.DEFAULT_ACCOUNT_ON_BANK_CREATION)
					&& account.getAccountName().equals(ledgerReceipt.getBankDetails().getUserBankName())) {
				ledgerColumnData = new LedgerColumns();
				ledgerColumnData.setParticulars("Receipt-" + ledgerReceipt.getAccountRefNo().getAccountName() + "-"
						+ ledgerReceipt.getReceiptMode());
				ledgerColumnData.setDate(ledgerReceipt.getReceiptDate());
				ledgerColumnData.setDebit(ledgerReceipt.getReceiptAmount());
				debitSum += Double.valueOf(ledgerReceipt.getReceiptAmount());
				ledgerData.add(ledgerColumnData);
			}
		}
		ledgerColumnData = new LedgerColumns();
		ledgerColumnData.setBalance(String.valueOf(debitSum - creditSum));
		ledgerColumnData.setParticulars("Balance");
		ledgerData.add(ledgerColumnData);
		ledgerMap.put(account, ledgerData);

		return ledgerMap;
	}

}
