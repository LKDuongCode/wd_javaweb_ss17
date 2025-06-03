package com.duong.ss17.repository.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.entity.hw06_hw07.Order;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
    public boolean insert(CreateOrderDTO dto) {
        try {
            Order order = new Order();
            order.setCustomerId(dto.getCustomerId());
            order.setOrderDate(LocalDateTime.now());
            order.setTotalAmount(dto.getTotalAmount());

            getSession().persist(order);
            return true;
        } catch (Exception e) {
            System.err.println("Insert order error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {
        return getSession().createQuery(
                        "from Order where customerId = :cid order by orderDate desc", Order.class)
                .setParameter("cid", customerId)
                .list();
    }
}
