package com.duong.ss17.service.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.entity.hw06_hw07.Order;
import com.duong.ss17.repository.hw06_hw07.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public boolean createOrder(CreateOrderDTO dto) {
        return orderRepo.insert(dto);
    }

    @Override
    public List<Order> getOrdersByCustomer(int customerId) {
        return orderRepo.findByCustomerId(customerId);
    }
}
