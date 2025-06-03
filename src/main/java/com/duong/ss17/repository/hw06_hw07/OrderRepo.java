package com.duong.ss17.repository.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.entity.hw06_hw07.Order;

import java.util.List;

public interface OrderRepo {
    boolean createOrder(int customerId, List<Product> cartProducts, CreateOrderDTO dto);
    List<Order> findOrdersByCustomerId(int customerId);
}