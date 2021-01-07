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
import com.bookkeepo.accounting.model.RecordContraCash;
import com.bookkeepo.accounting.model.RecordExpense;
import com.bookkeepo.accounting.model.RecordIncome;
import com.bookkeepo.accounting.model.TrialBalanceCol;
import com.bookkeepo.accounting.service.AccountLedgerService;
import com.bookkeepo.accounting.service.InvoiceService;
import com.bookkeepo.accounting.service.PaymentService;
import com.bookkeepo.accounting.service.ReceiptService;
import com.bookkeepo.accounting.service.RecordContraCashService;
import com.bookkeepo.accounting.service.RecordExpenseService;
import com.bookkeepo.accounting.service.RecordIncomeService;

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
	private static RecordExpenseService recordExpense;
	private static RecordIncomeService recordIncome;
	private static RecordContraCashService recordSS;

	private static String DEBIT = "Dr";

	@Autowired
	ReceiptService receiptService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	AccountLedgerService accountLedgerService;

	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	RecordExpenseService recordExpenseService;
	
	@Autowired
	RecordIncomeService recordIncomeService;
	
	@Autowired
	RecordContraCashService recordContraCashService;

	@PostConstruct
	public void init() {
		LedgerUtil.receipt = receiptService;
		LedgerUtil.payment = paymentService;
		LedgerUtil.accountLedger = accountLedgerService;
		LedgerUtil.invoice = invoiceService;
		LedgerUtil.recordExpense = recordExpenseService;
		LedgerUtil.recordIncome = recordIncomeService;
		LedgerUtil.recordSS = recordContraCashService;
	}

	public static Map<Accounts, List<LedgerColumns>> setUpLedgers(Accounts account, LedgerInfo ledger) {

		Map<Accounts, List<LedgerColumns>> ledgerMap = new HashMap<Accounts, List<LedgerColumns>>();
		List<InvoiceDetails> invoices = invoice.findByInvoiceAccountDetails(account);

		for (InvoiceDetails invoice : nullGuard(invoices)) {
			if (InvoiceUtil.isValidDate(ledger.getStartDate(), ledger.getEndDate(),
					CommonUtils.convertDateIntoFormat(invoice.getInvoiceDate())) && invoice.getId() != 0
					&& !invoice.getInvoiceType().equals(InvoiceType.Purchase_Order.getType())) {
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
		
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = CommonUtils.convertToDate(ledger.getStartDate());
			endDate = CommonUtils.convertToDate(ledger.getEndDate());
			endDate.setTime(endDate.getTime() + 86399000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<RecordExpense> expenseList = recordExpense.findExpensePayableByRecordExpenseOwner(account.getAccountOwner(), account, startDate, endDate);
		List<RecordIncome> incomeList = recordIncome.findIncomePayableByRecordIncomeOwner(account.getAccountOwner(), account, startDate, endDate);

		for(RecordExpense expense:nullGuard(expenseList)) {
			List<LedgerColumns> expenseData;
			if (ledgerMap.get(account) == null) {
				expenseData = new ArrayList<LedgerColumns>();
				LedgerColumns data = new LedgerColumns();
				data.setParticulars(expense.getExpenseWithAccountReference().getAccountName());
				data.setDate(expense.getExpenseDate());
				if (DEBIT.equals(accountLedger.findByAccountType(account.getAccountType()).getAccountpostive())) {
					data.setDebit(expense.getExpenseAmount());
				} else {
					data.setCredit(expense.getExpenseAmount());
				}
				expenseData.add(data);
				ledgerMap.put(account, expenseData);
			} else {
				expenseData = ledgerMap.get(account);
				LedgerColumns data = new LedgerColumns();
				data.setParticulars(expense.getExpenseWithAccountReference().getAccountName());
				data.setDate(expense.getExpenseDate());
				if (DEBIT.equals(accountLedger.findByAccountType(account.getAccountType()).getAccountpostive())) {
					data.setDebit(expense.getExpenseAmount());
				} else {
					data.setCredit(expense.getExpenseAmount());
				}
				expenseData.add(data);
				ledgerMap.put(account, expenseData);
			}
			
		}
		
		for(RecordIncome income:nullGuard(incomeList)) {
			List<LedgerColumns> expenseData;
			if (ledgerMap.get(account) == null) {
				expenseData = new ArrayList<LedgerColumns>();
				LedgerColumns data = new LedgerColumns();
				data.setParticulars(income.getIncomeWithAccountReference().getAccountName());
				data.setDate(income.getIncomeDate());
				if (DEBIT.equals(accountLedger.findByAccountType(account.getAccountType()).getAccountpostive())) {
					data.setDebit(income.getIncomeAmount());
				} else {
					data.setCredit(income.getIncomeAmount());
				}
				expenseData.add(data);
				ledgerMap.put(account, expenseData);
			} else {
				expenseData = ledgerMap.get(account);
				LedgerColumns data = new LedgerColumns();
				data.setParticulars(income.getIncomeWithAccountReference().getAccountName());
				data.setDate(income.getIncomeDate());
				if (DEBIT.equals(accountLedger.findByAccountType(account.getAccountType()).getAccountpostive())) {
					data.setDebit(income.getIncomeAmount());
				} else {
					data.setCredit(income.getIncomeAmount());
				}
				expenseData.add(data);
				ledgerMap.put(account, expenseData);
			}
			
		}
		
		List<LedgerColumns> particulars = ledgerMap.get(account)!=null ? ledgerMap.get(account):new ArrayList<LedgerColumns>();
		double creditSum = 0, debitSum = 0;
		for (LedgerColumns column : nullGuard(particulars)) {
			creditSum += column.getCredit() != null ? Double.valueOf(column.getCredit()) : 0;
			debitSum += column.getDebit() != null ? Double.valueOf(column.getDebit()) : 0;
		}
		LedgerColumns balance = new LedgerColumns();
		balance.setCreditSum(String.valueOf(creditSum));
		balance.setDebitSum(String.valueOf(debitSum));
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
		
		
		List<RecordContraCash> payTo, payFrom;
		
		if(account.getAccountType().equals(Constants.DEFAULT_ACCOUNT_ON_COMPANY_CREATION)) {
			payFrom = recordSS.findByPayFromAndRecordContraOwner("CASH", account.getAccountOwner(), startDate, endDate);
			payTo = recordSS.findByPayToAndRecordContraOwner("CASH", account.getAccountOwner(), startDate, endDate);
		} else {
			payFrom = recordSS.findByBankDetailsFromAndRecordContraOwner(account, account.getAccountOwner(), startDate, endDate);
			payTo = recordSS.findByBankDetailsToAndRecordContraOwner(account, account.getAccountOwner(), startDate, endDate);
		}
		
		

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
		
		for (RecordContraCash recordCC : nullGuard(payTo)) {
			ledgerColumnData = new LedgerColumns();
			if (recordCC.getPayFrom().equals("CASH")) {
				ledgerColumnData.setParticulars("CASH");
			} else {
				ledgerColumnData.setParticulars(recordCC.getBankDetailsFrom().getAccountName());
			}
			debitSum += Double.valueOf(recordCC.getRecordContraAmount());
			ledgerColumnData.setDebit(recordCC.getRecordContraAmount());
			ledgerColumnData.setDate(recordCC.getRecordContraDate());
			ledgerData.add(ledgerColumnData);
		}
		
		for (RecordContraCash recordCC : nullGuard(payFrom)) {
			ledgerColumnData = new LedgerColumns();
			if (recordCC.getPayTo().equals("CASH")) {
				ledgerColumnData.setParticulars("CASH");
			} else {
				ledgerColumnData.setParticulars(recordCC.getBankDetailsTo().getAccountName());
			}
			creditSum += Double.valueOf(recordCC.getRecordContraAmount());
			ledgerColumnData.setCredit(recordCC.getRecordContraAmount());
			ledgerColumnData.setDate(recordCC.getRecordContraDate());
			ledgerData.add(ledgerColumnData);
		}

		ledgerColumnData = new LedgerColumns();
		ledgerColumnData.setBalance(String.valueOf(debitSum - creditSum));
		ledgerColumnData.setParticulars("Balance");
		ledgerData.add(ledgerColumnData);
		ledgerMap.put(account, ledgerData);

		return ledgerMap;
	}

	public static List<LedgerColumns> ledgerforInvoices(List<InvoiceDetails> invoiceData) {
		List<LedgerColumns> ledgerdata = new ArrayList<LedgerColumns>();
		Double creditSum=0.0;
		Double debitSum=0.0;
		for(InvoiceDetails invoice:nullGuard(invoiceData)) {
			LedgerColumns data = new LedgerColumns();
			data.setParticulars(invoice.getInvoiceAccountDetails().getAccountName()+"-"+invoice.getInvoiceType());
			data.setDate(invoice.getInvoiceDate());
			if (DEBIT.equals(accountLedger.findByAccountType(invoice.getInvoiceAccountDetails().getAccountType()).getAccountpostive())){
				data.setDebit(invoice.getInvoiceTotalAmountAfterTax());
				debitSum+=Double.valueOf(data.getDebit());
			} else {
				data.setCredit(invoice.getInvoiceTotalAmountAfterTax());
				creditSum+=Double.valueOf(data.getCredit());
			}
			ledgerdata.add(data);
		}
		LedgerColumns ledgerColumnData = new LedgerColumns();
		ledgerColumnData.setBalance(String.valueOf(debitSum - creditSum));
		ledgerColumnData.setParticulars("Balance");
		ledgerdata.add(ledgerColumnData);
		return ledgerdata;
	}

	public static Map<Accounts, List<LedgerColumns>> setUpExpenseLedgers(Accounts account, LedgerInfo ledger) {
		
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = CommonUtils.convertToDate(ledger.getStartDate());
			endDate = CommonUtils.convertToDate(ledger.getEndDate());
			endDate.setTime(endDate.getTime() + 86399000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		Map<Accounts, List<LedgerColumns>> ledgerMap = new HashMap<Accounts, List<LedgerColumns>>();
		List<RecordExpense> expenseList = recordExpense.findExpenseByRecordExpenseOwner(account.getAccountOwner(), account, startDate, endDate);
		List<LedgerColumns> ledgerData = new ArrayList<LedgerColumns>();
		Double debitSum = 0.0;
		Double creditSum = 0.0;
		for(RecordExpense expense:nullGuard(expenseList)) {
			LedgerColumns data = new LedgerColumns();
			data.setParticulars(expense.getExpenseWithAccountReference().getAccountName()+" Payable");
			data.setDate(expense.getExpenseDate());
			if (DEBIT.equals(accountLedger.findByAccountType(account.getAccountType()).getAccountpostive())) {
				data.setDebit(expense.getExpenseAmount());
				debitSum += Double.valueOf(expense.getExpenseAmount());
			} else {
				data.setCredit(expense.getExpenseAmount());
				creditSum += Double.valueOf(expense.getExpenseAmount());
			}
			ledgerData.add(data);
		}
		LedgerColumns ledgerColumnData = new LedgerColumns();
		ledgerColumnData.setBalance(String.valueOf(debitSum - creditSum));
		ledgerColumnData.setParticulars("Balance");
		ledgerData.add(ledgerColumnData);
		ledgerMap.put(account, ledgerData);
		return ledgerMap;
	}
	
	
	public static Map<Accounts, List<LedgerColumns>> setUpIncomeLedgers(Accounts account, LedgerInfo ledger) {
		
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = CommonUtils.convertToDate(ledger.getStartDate());
			endDate = CommonUtils.convertToDate(ledger.getEndDate());
			endDate.setTime(endDate.getTime() + 86399000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		Map<Accounts, List<LedgerColumns>> ledgerMap = new HashMap<Accounts, List<LedgerColumns>>();
		List<RecordIncome> incomeList = recordIncome.findIncomeByRecordIncomeOwner(account.getAccountOwner(), account, startDate, endDate);
		List<LedgerColumns> ledgerData = new ArrayList<LedgerColumns>();
		Double debitSum = 0.0;
		Double creditSum = 0.0;
		for(RecordIncome expense:nullGuard(incomeList)) {
			LedgerColumns data = new LedgerColumns();
			data.setParticulars(expense.getIncomeWithAccountReference().getAccountName()+" Payable");
			data.setDate(expense.getIncomeDate());
			if (DEBIT.equals(accountLedger.findByAccountType(account.getAccountType()).getAccountpostive())) {
				data.setDebit(expense.getIncomeAmount());
				debitSum += Double.valueOf(expense.getIncomeAmount());
			} else {
				data.setCredit(expense.getIncomeAmount());
				creditSum += Double.valueOf(expense.getIncomeAmount());
			}
			ledgerData.add(data);
		}
		LedgerColumns ledgerColumnData = new LedgerColumns();
		ledgerColumnData.setBalance(String.valueOf(debitSum - creditSum));
		ledgerColumnData.setParticulars("Balance");
		ledgerData.add(ledgerColumnData);
		ledgerMap.put(account, ledgerData);
		return ledgerMap;
	}
	
	public static Map<Accounts, List<TrialBalanceCol>> trialBalanceLedger(Accounts account, LedgerInfo ledger) {
		Map<Accounts, List<LedgerColumns>> ledgerMap = setUpLedgers(account, ledger);
		Map<Accounts, List<TrialBalanceCol>> trialBalance = new HashMap();
		
		List<LedgerColumns> particulars = ledgerMap.get(account);
		List<TrialBalanceCol> trialBalanceList = new ArrayList<>();
		double creditSum = 0, debitSum = 0;
		for (LedgerColumns column : particulars) {
			if(null != column)
			{
			creditSum += column.getCredit() != null ? Double.valueOf(column.getCredit()) : 0;
			debitSum += column.getDebit() != null ? Double.valueOf(column.getDebit()) : 0;
			}
		}
		TrialBalanceCol balance = new TrialBalanceCol();
		balance.setCreditSum(String.valueOf(creditSum));
		balance.setDebitSum(String.valueOf(debitSum));
		if(account.getOpeningBalanceType().equalsIgnoreCase("cr"))
			balance.setBalance(String.valueOf(debitSum - (creditSum + Double.valueOf(account.getOpeningBalanceAmount()))));
		else
			balance.setBalance(String.valueOf((debitSum + Double.valueOf(account.getOpeningBalanceAmount())) - creditSum));
		balance.setAccountNormalBalance(accountLedger.findByAccountType(account.getAccountType()).getAccountNormalBalance());
		trialBalanceList.add(balance);
		trialBalance.put(account, trialBalanceList);
		return trialBalance;
		
	}
	

}
