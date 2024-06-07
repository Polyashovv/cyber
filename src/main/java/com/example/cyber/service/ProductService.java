package com.example.cyber.service;

import com.example.cyber.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

   Product create(Product product);

   List<Product> getAllProducts();

   Optional<Product> getProductById(Long id);

   List<Product> getAllProductsSortedByPrice(boolean ascending);

   List<Product> getAllProductsSortedByRating(boolean ascending);
}
