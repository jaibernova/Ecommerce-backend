package com.gestion.empleados.modelo;

public class ProductSearchCriteria {
    private String name;
    private Double minPrice;
    private Double maxPrice;

    // Constructor vacío
    public ProductSearchCriteria() {
    }

    // Constructor con parámetros
    public ProductSearchCriteria(String name, Double minPrice, Double maxPrice) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}