package com.example.cyber.service;

import com.example.cyber.model.Product;
import com.example.cyber.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(cacheNames = "products")
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProductsSortedByPrice(boolean ascending) {
        return ascending ?
                productRepository.findAll(Sort.by(Sort.Direction.ASC, "price")) :
                productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    @Override
    public List<Product> getAllProductsSortedByRating(boolean ascending) {
        return ascending ?
                productRepository.findAll(Sort.by(Sort.Direction.ASC, "rating")) :
                productRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
    }
}
