package com.duong.ss17.service.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.dto.hw06_hw07.OrderHistoryDTO;
import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.entity.hw06_hw07.Order;
import com.duong.ss17.entity.hw06_hw07.OrderStatus;
import com.duong.ss17.repository.hw06_hw07.OrderRepo;
import com.duong.ss17.service.hw03.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepo orderRepo, ProductService productService) {
        this.orderRepo = orderRepo;
        this.productService = productService;
    }

    @Override
    public boolean insert(int customerId, List<Product> cartProducts, CreateOrderDTO dto) {
        return orderRepo.createOrder(customerId, cartProducts, dto);
    }

    @Override
    public List<OrderHistoryDTO> getOrdersByCustomerId(int customerId) {
        List<Order> orders = orderRepo.findOrdersByCustomerId(customerId);
        List<OrderHistoryDTO> historyList = new ArrayList<>();

        for (Order order : orders) {
            OrderHistoryDTO dto = new OrderHistoryDTO();
            dto.setId(order.getId());
            dto.setRecipientName(order.getRecipientName());
            dto.setTotalMoney(order.getTotalMoney());
            dto.setStatus(order.getStatus());

            historyList.add(dto);
        }

        return historyList;
    }


}

