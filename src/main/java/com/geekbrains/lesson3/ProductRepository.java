package com.geekbrains.lesson3;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("ProductRepositoryBean")

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        products.add(new Product(1,"apple",9.5));
        products.add(new Product(2,"tomato",5.8));
        products.add(new Product(3,"carrot",7.2));
    }

    public Product findById(int id){
         return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
