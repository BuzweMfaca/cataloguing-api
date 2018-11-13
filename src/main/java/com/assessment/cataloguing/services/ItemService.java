package com.assessment.cataloguing.services;

import com.assessment.cataloguing.domain.Item;
import com.assessment.cataloguing.exceptions.ItemIdException;
import com.assessment.cataloguing.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item saveOrUpdate(Item item) {
        try {
            item.setItemIdentifier(item.getItemIdentifier().toUpperCase());
            return itemRepository.save(item);
        }catch (Exception e){
            throw new ItemIdException("Item ID '"+ item.getItemIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Item findItemByIdentifier(String itemId){

        Item item = itemRepository.findByItemIdentifier(itemId.toUpperCase());

        if(item == null){
            throw new ItemIdException("Item ID '"+itemId+"' does not exist");
        }

        return item;
    }

    public Iterable<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public void deleteItemByIdentifier(String itemid){
        Item item = itemRepository.findByItemIdentifier(itemid.toUpperCase());

        if(item == null){
            throw new ItemIdException("Could not find Item with ID '"+itemid+"'. This item does not exist");
        }

        itemRepository.delete(item);

    }
}
