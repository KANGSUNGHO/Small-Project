package maskbook.maskshop.service;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.item.Item;
import maskbook.maskshop.domain.item.ItemArea;
import maskbook.maskshop.domain.item.Mask;
import maskbook.maskshop.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(Long id, String name, int price, int stockQuantity, String kinds, ItemArea area) {
        Mask item = (Mask) itemRepository.findOne(id);
        item.setItemName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        item.setKinds(kinds);
        item.setArea(area);
    }
}
