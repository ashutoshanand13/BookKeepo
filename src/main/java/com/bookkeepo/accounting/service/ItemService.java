/**
 * 
 */
package com.bookkeepo.accounting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookkeepo.accounting.entity.ProductDetails;
import com.bookkeepo.accounting.repository.ProductRepository;

/**
 * @author Yash Singh
 *
 */
@Service("itemService")
public class ItemService {

	private ProductRepository productRepository;

	@Autowired
	public ItemService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDetails> findByProductOwner(String owner) {
		List<ProductDetails> itemList = new ArrayList<>();
		ProductDetails InvoiceProductDetails = new ProductDetails();
		InvoiceProductDetails.setId(0);
		InvoiceProductDetails.setProductDescription("");

		List<ProductDetails> dbItemList = productRepository.findByProductOwner(owner);
		if (!dbItemList.isEmpty()) {
			itemList.addAll(dbItemList);
		}
		return itemList;
	}

	public List<ProductDetails> fetchAllItems(String owner) {
		return productRepository.findByProductOwner(owner);
	}

	public List<ProductDetails> fetchAllItemsForItems(String owner) {
		List<ProductDetails> itemList = new ArrayList<ProductDetails>();
		ProductDetails addNewItem = new ProductDetails();
		addNewItem.setId(0);
		addNewItem.setProductDescription("Add New Item");
		itemList.add(addNewItem);

		List<ProductDetails> dbItemList = productRepository.findByProductOwner(owner);
		if (!dbItemList.isEmpty()) {
			itemList.addAll(dbItemList);
		}

		return itemList;
	}

	public void saveProductItem(ProductDetails item) {
		productRepository.save(item);
	}

	public ProductDetails findById(int id) {
		return productRepository.findById(id);
	}
}
