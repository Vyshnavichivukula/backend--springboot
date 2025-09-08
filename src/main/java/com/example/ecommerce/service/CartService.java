package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import com.example.ecommerce.dto.CartRequest;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CartService {

    @Autowired private CartItemRepository cartRepo;
    @Autowired private ItemRepository itemRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private JwtUtil jwtUtil;

    public ResponseEntity<?> addItem(CartRequest request, String token) {
        String username = jwtUtil.extractUsername(token);
        User user = userRepo.findByUsername(username).orElseThrow();
        Item item = itemRepo.findById(request.getItemId()).orElseThrow();

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setItem(item);
        cartItem.setQuantity(request.getQuantity());

        cartRepo.save(cartItem);
        return ResponseEntity.ok("Item added to cart");
    }

    public ResponseEntity<?> removeItem(Long itemId, String token) {
        String username = jwtUtil.extractUsername(token);
        User user = userRepo.findByUsername(username).orElseThrow();

        List<CartItem> items = cartRepo.findByUser(user);
        items.stream()
            .filter(ci -> ci.getItem().getId().equals(itemId))
            .findFirst()
            .ifPresent(cartRepo::delete);

        return ResponseEntity.ok("Item removed");
    }

    public List<CartItem> getUserCart(String token) {
        String username = jwtUtil.extractUsername(token);
        User user = userRepo.findByUsername(username).orElseThrow();
        return cartRepo.findByUser(user);
    }
}
