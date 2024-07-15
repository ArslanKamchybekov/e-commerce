package com.example.e_commerce.services;

import com.example.e_commerce.models.Product;
import com.example.e_commerce.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService{
    @Autowired
    ProductsRepository repository;

    @Override
    public List<Product> getProducts() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product get(int id) {
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()) throw new RuntimeException("Product does not exist");
        return product.get();
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
