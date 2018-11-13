package com.assessment.cataloguing.repositories;

import com.assessment.cataloguing.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends CrudRepository<Item, Long> {

    Item findByItemIdentifier(String itemID);

    @Override
    Iterable<Item> findAll();
}
