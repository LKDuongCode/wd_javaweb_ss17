package com.duong.ss17.service.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.entity.hw06_hw07.Order;

import java.util.List;

public interface OrderService {
    boolean createOrder(CreateOrderDTO dto);

    List<Order> getOrdersByCustomer(int customerId);
}
