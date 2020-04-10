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

	public List<String> findByProductOwner(String owner) {
		ArrayList<String> itemList = new ArrayList<>();
		itemList.add("Add New Item");
		itemList.addAll(itemRepository.findByProductOwner(owner).stream().map(p -> p.getProductDescription())
				.collect(Collectors.toList()));
		return itemList;
	}

	public List<InvoiceProductDetails> fetchAllItems(String owner) {
		return itemRepository.findByProductOwner(owner);
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
}
