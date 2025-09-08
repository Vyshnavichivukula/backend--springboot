package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
public class CartItem {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;

    private int quantity;
}

