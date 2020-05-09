/**
 * 
 */
package com.bookkeepo.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookkeepo.accounting.entity.Company;
import com.bookkeepo.accounting.entity.ProductDetails;

/**
 * @author Yash Singh
 *
 */
@Repository("productRepository")
public interface ProductRepository extends JpaRepository<ProductDetails, Long> {

	List<ProductDetails> findByProductOwnerAndProductCompanyDetails(String owner,Company company);

	ProductDetails findById(int id);

}
