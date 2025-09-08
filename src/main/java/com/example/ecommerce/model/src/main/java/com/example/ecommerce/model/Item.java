package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
public class Item {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String category;
    private double price;
}
