package com.example.cyber.service;

import com.example.cyber.controller.requests.OrderRequests;
import com.example.cyber.exceptions.ProductNotFoundException;
import com.example.cyber.model.Order;
import com.example.cyber.model.Product;
import com.example.cyber.model.User;
import com.example.cyber.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public Order placeNewOrder(OrderRequests order) throws ProductNotFoundException {
        Optional<Product> product = productService.getProductById(order.getProductId());
        if (product.isPresent()) {
            return orderRepository.save(
                    new Order(
                            null,
                            product.get(),
                            (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                            order.getAddress(),
                            new Date()
                    )
            );
        } else {
            throw new ProductNotFoundException(order.getProductId());
        }
    }

    @Override
    public Page<Order> getAllOrders(int page, boolean sortByDate) {
        int size = 2;
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by(sortByDate ? "orderDate" : "id")));
    }

    @Override
    public List<Order> getOrdersByItem(Long id) throws ProductNotFoundException {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return orderRepository.findAllByProduct(product.get());
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public List<Order> getAllOrdersSortedByDate(boolean ascending) {
        return ascending ? orderRepository.findAllByOrderByOrderDateAsc() : orderRepository.findAllByOrderByOrderDateDesc();
    }
}
