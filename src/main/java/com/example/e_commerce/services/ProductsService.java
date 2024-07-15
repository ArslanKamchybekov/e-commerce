package com.example.e_commerce.services;

import com.example.e_commerce.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsService {
    List<Product> getProducts();
    Product save(Product product);
    Product get(int id);
    void delete(int id);
}
