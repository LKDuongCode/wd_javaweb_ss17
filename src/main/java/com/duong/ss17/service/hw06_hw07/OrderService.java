package com.duong.ss17.service.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.dto.hw06_hw07.OrderHistoryDTO;
import com.duong.ss17.entity.hw03.Product;

import java.util.List;

public interface OrderService {
    boolean insert(int customerId, List<Product> cartProducts, CreateOrderDTO dto);
    List<OrderHistoryDTO> getOrdersByCustomerId(int customerId);
}
