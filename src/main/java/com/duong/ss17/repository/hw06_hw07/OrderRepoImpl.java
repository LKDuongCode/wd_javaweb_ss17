package com.duong.ss17.repository.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.entity.hw06_hw07.Order;
import com.duong.ss17.entity.hw06_hw07.OrderStatus;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class OrderRepoImpl implements OrderRepo {
    private final SessionFactory sessionFactory;

    public OrderRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean createOrder(int customerId, List<Product> cartProducts, CreateOrderDTO dto) {
        try {
            Order order = new Order();
            order.setCustomerId(customerId);
            order.setListProduct(cartProducts);
            order.setRecipientName(dto.getRecipientName());
            order.setPhoneNumber(dto.getPhoneNumber());
            order.setAddress(dto.getAddress());
            order.setStatus(OrderStatus.PENDING);
            double total = cartProducts.stream().mapToDouble(p -> p.getPrice()).sum();
            order.setTotalMoney(total);

            getSession().persist(order);
            return true;
        } catch (Exception e) {
            System.err.println("Lỗi tạo đơn hàng: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Order> findOrdersByCustomerId(int customerId) {
        return getSession()
                .createQuery("from Order where customerId = :id", Order.class)
                .setParameter("id", customerId)
                .list();
    }
}
