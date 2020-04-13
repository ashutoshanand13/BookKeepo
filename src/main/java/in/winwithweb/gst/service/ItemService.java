/**
 * 
 */
package in.winwithweb.gst.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.sales.InvoiceProductDetails;
import in.winwithweb.gst.repository.ItemRepository;

/**
 * @author Yash Singh
 *
 */
@Service("itemService")
public class ItemService {

	private ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public List<InvoiceProductDetails> findByProductOwner(String owner) {
		List<InvoiceProductDetails> itemList = new ArrayList<>();
		InvoiceProductDetails InvoiceProductDetails = new InvoiceProductDetails();
		InvoiceProductDetails.setId(0);
		InvoiceProductDetails.setProductDescription("");

		List<InvoiceProductDetails> dbItemList = itemRepository.findByProductOwner(owner);
		if (!dbItemList.isEmpty()) {
			itemList.addAll(dbItemList);
		}
		return itemList;
	}

	public List<InvoiceProductDetails> fetchAllItems(String owner) {
		return itemRepository.findByProductOwner(owner);
	}

	public List<InvoiceProductDetails> fetchAllItemsForItem(String owner) {
		List<InvoiceProductDetails> itemList = new ArrayList<InvoiceProductDetails>();
		InvoiceProductDetails InvoiceProductDetails = new InvoiceProductDetails();
		InvoiceProductDetails.setId(0);
		InvoiceProductDetails.setProductDescription("Add New Item");
		itemList.add(InvoiceProductDetails);

		List<InvoiceProductDetails> dbItemList = itemRepository.findByProductOwner(owner);
		if (!dbItemList.isEmpty()) {
			itemList.addAll(dbItemList);
		}

		return itemList;
	}

	public InvoiceProductDetails findByProductDescription(String productDescription, String owner) {
		List<InvoiceProductDetails> temp = itemRepository.findByProductOwner(owner).stream()
				.filter(p -> p.getProductDescription().equalsIgnoreCase(productDescription))
				.collect(Collectors.toList());
		if (temp.size() > 0)
			return temp.get(0);
		else
			return null;
	}

	public void saveItem(InvoiceProductDetails item) {
		itemRepository.save(item);
	}

	public InvoiceProductDetails findById(int id) {
		return itemRepository.findById(id);
	}
}
