REPLACE INTO `role` VALUES (1,'USER');
REPLACE INTO `role` VALUES (2,'ADMIN');
DROP TRIGGER IF EXISTS update_Company_insert_creationdate;
DROP TRIGGER IF EXISTS update_Company_update_creationdate;
CREATE TRIGGER update_Company_insert_creationdate BEFORE INSERT ON company_details FOR EACH ROW SET NEW.company_creation_date = NOW();
CREATE TRIGGER update_Company_update_creationdate BEFORE UPDATE ON company_details FOR EACH ROW SET NEW.company_creation_date = OLD.company_creation_date, NEW.company_last_modified = NOW();