REPLACE INTO `role` VALUES (1,'USER');
REPLACE INTO `role` VALUES (2,'ADMIN');
update ebdb.company_details set company_creation_date=NOW(), company_last_modified = NOW();
update ebdb.account_details set account_creation_date=NOW();
update ebdb.payment_details set payment_creation_date=NOW();
update ebdb.receipt_details set receipt_creation_date=NOW();
update ebdb.user set user_creation_date=NOW();
update ebdb.invoice_details set invoice_creation_date=NOW();