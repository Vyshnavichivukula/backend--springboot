package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String role;
}
