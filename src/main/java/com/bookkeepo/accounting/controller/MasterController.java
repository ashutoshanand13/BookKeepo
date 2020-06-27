/**
 * 
 */
package com.bookkeepo.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import com.bookkeepo.accounting.configurations.LoginRegisterMessages;
import com.bookkeepo.accounting.configurations.MessageProperties;
import com.bookkeepo.accounting.service.AccountLedgerService;
import com.bookkeepo.accounting.service.AccountService;
import com.bookkeepo.accounting.service.BankService;
import com.bookkeepo.accounting.service.CompanyDetailsService;
import com.bookkeepo.accounting.service.EmailService;
import com.bookkeepo.accounting.service.InvoiceService;
import com.bookkeepo.accounting.service.ItemService;
import com.bookkeepo.accounting.service.PaymentService;
import com.bookkeepo.accounting.service.ReceiptService;
import com.bookkeepo.accounting.service.ReportService;
import com.bookkeepo.accounting.service.UserService;
import com.google.gson.Gson;

/**
 * @author sachingoyal
 *
 */
@Configuration
@Controller
public class MasterController {

	@Autowired
	protected AccountService accountService;

	@Autowired
	protected CompanyDetailsService companyDetailsService;

	@Autowired
	protected InvoiceService invoiceService;

	@Autowired
	protected ItemService itemService;

	@Autowired
	protected BankService bankService;

	@Autowired
	protected Gson gson;

	@Autowired
	protected UserService userService;

	@Autowired
	protected BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	protected EmailService emailservice;

	@Autowired
	protected MessageProperties messageproperties;

	@Autowired
	protected LoginRegisterMessages loginregistermessageproperties;

	@Autowired
	protected PaymentService paymentService;

	@Autowired
	protected ReceiptService receiptService;

	@Autowired
	protected ReportService reportService;
	
	@Autowired
	protected AccountLedgerService accountLedgerService;

}
