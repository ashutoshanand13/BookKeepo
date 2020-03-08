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
		itemList.add("");
		itemList.addAll(itemRepository.findByProductOwner(owner).stream().map(p -> p.getProductDescription())
				.collect(Collectors.toList()));
		return itemList;
	}

	public InvoiceProductDetails findByProductDescription(String productDescription, String owner) {
		List<InvoiceProductDetails> temp = itemRepository.findByProductOwner(owner).stream().filter(p -> p.getProductDescription().equalsIgnoreCase(productDescription))
				.collect(Collectors.toList());
		 
		 return temp.get(0);
	}

	public void saveItem(InvoiceProductDetails item) {
		itemRepository.save(item);
	}
}
