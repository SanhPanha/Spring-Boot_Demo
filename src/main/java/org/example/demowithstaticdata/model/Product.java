package org.example.demowithstaticdata.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private static int idCounter = 0;

    @Setter
    // Getters
    private final int id;
    // Setters
    @Setter
    private String name;
    @Setter
    private double price;

    public Product(String name, double price) {
        this.id = ++idCounter;
        this.name = name;
        this.price = price;
    }
}
