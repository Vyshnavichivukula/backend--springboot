package com.example.ecommerce.dto;

public class CartRequest {
    private Long itemId;
    private int quantity;

    // Constructors
    public CartRequest() {}
    public CartRequest(Long itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    // Getters & Setters
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
