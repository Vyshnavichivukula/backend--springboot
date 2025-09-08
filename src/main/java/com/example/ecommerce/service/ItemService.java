package com.example.ecommerce.service;

import com.example.ecommerce.model.Item;
import com.example.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ItemService {

    @Autowired private ItemRepository itemRepo;

    public List<Item> filterItems(Optional<String> category, Optional<Double> min, Optional<Double> max) {
        if (category.isPresent()) return itemRepo.findByCategory(category.get());
        if (min.isPresent() && max.isPresent()) return itemRepo.findByPriceBetween(min.get(), max.get());
        return itemRepo.findAll();
    }

    public Item save(Item item) { return itemRepo.save(item); }

    public Item update(Long id, Item item) {
        Item existing = itemRepo.findById(id).orElseThrow();
        existing.setName(item.getName());
        existing.setCategory(item.getCategory());
        existing.setPrice(item.getPrice());
        return itemRepo.save(existing);
    }

    public void delete(Long id) { itemRepo.deleteById(id); }
}
