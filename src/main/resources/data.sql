REPLACE INTO `role` VALUES (1,'USER');
REPLACE INTO `role` VALUES (2,'ADMIN');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (1,'Capital Account','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (2,'Current Assets','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (3,'Current Liabilities','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (4,'Direct Expenses','Profit & loss account','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (5,'Direct Incomes','Profit & loss account','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (6,'Fixed Assets','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (7,'Indirect Expenses','Profit & loss account','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (8,'Indirect Incomes','Profit & loss account','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (9,'Investments','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (10,'Loans (Liability)','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (11,'Misc. Expenses (ASSET)','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (12,'Purchase Accounts','Profit & loss account','Dr','','Dr');	
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (13,'Sales Accounts','Profit & loss account','Cr','','Cr');	
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (14,'Suspense A/c','Balance Sheet','Depends','Depends','Depends');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (15,'Bank Accounts','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (16,'Bank OD A/c','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (17,'Cash-in-hand','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (18,'Deposits (Asset)','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (19,'Duties & Taxes','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (20,'Loans & Advances (Asset)','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (21,'Provisions','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (22,'Reserves & Surplus','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (23,'Secured Loans','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (24,'Stock-in-hand','Balance Sheet',	'Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (25,'Sundry Creditors','Balance Sheet','Cr','Dr','Cr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (26,'Sundry Debtors','Balance Sheet','Dr','Cr','Dr');
REPLACE INTO `account_ledger` (account_ledger_id,account_ledger_type,account_ledger_use,account_ledger_postive,account_ledger_negative,account_normal_balance) VALUES (27,'Unsecured Loans','Balance Sheet','Cr','Dr','Cr');
DROP TRIGGER IF EXISTS GENERATE_PAYMENT_NUMBER;
DROP TRIGGER IF EXISTS GENERATE_RECEIPT_NUMBER;
DROP TRIGGER IF EXISTS UPDATE_INVOICE_PAID_IND;
CREATE TRIGGER GENERATE_PAYMENT_NUMBER BEFORE INSERT ON payment_details FOR EACH ROW SET NEW.payment_number = (SELECT COALESCE(MAX(payment_number), 0) + 1  FROM payment_details WHERE payment_owner = NEW.payment_owner AND payment_company_reference_no = NEW.payment_company_reference_no);
CREATE TRIGGER GENERATE_RECEIPT_NUMBER BEFORE INSERT ON receipt_details FOR EACH ROW SET NEW.receipt_number = (SELECT COALESCE(MAX(receipt_number), 0) + 1  FROM receipt_details WHERE receipt_owner = NEW.receipt_owner AND receipt_company_reference_no = NEW.receipt_company_reference_no);
CREATE TRIGGER UPDATE_INVOICE_PAID_IND BEFORE UPDATE ON invoice_details FOR EACH ROW SET NEW.invoice_paid = (SELECT IF(NEW.invoice_total_amount_after_tax = NEW.invoice_paid_amount,'1','0'));