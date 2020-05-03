REPLACE INTO `role` VALUES (1,'USER');
REPLACE INTO `role` VALUES (2,'ADMIN');
DROP TRIGGER IF EXISTS GENERATE_PAYMENT_NUMBER;
DROP TRIGGER IF EXISTS GENERATE_RECEIPT_NUMBER;
CREATE TRIGGER GENERATE_PAYMENT_NUMBER BEFORE INSERT ON payment_details FOR EACH ROW SET NEW.payment_number = (SELECT COALESCE(MAX(payment_number), 0) + 1  FROM payment_details WHERE payment_owner = NEW.payment_owner AND payment_company_reference_no = NEW.payment_company_reference_no);
CREATE TRIGGER GENERATE_RECEIPT_NUMBER BEFORE INSERT ON receipt_details FOR EACH ROW SET NEW.receipt_number = (SELECT COALESCE(MAX(receipt_number), 0) + 1  FROM receipt_details WHERE receipt_owner = NEW.receipt_owner AND receipt_company_reference_no = NEW.receipt_company_reference_no);