package com.example.e_commerce.repositories;

import com.example.e_commerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {}
