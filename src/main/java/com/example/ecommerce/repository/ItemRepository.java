package com.example.ecommerce.repository;

import com.example.ecommerce.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory(String category);
    List<Item> findByPriceBetween(double min, double max);
}
