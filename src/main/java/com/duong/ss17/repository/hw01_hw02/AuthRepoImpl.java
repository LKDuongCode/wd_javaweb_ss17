package com.duong.ss17.repository.hw01_hw02;

import com.duong.ss17.dto.hw01_hw02.RegisterCustomerDTO;
import com.duong.ss17.entity.hw01_hw02.Customer;
import com.duong.ss17.entity.hw01_hw02.Role;
import com.duong.ss17.entity.hw01_hw02.UserStatus;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.Optional;

@Repository
@Transactional
public class AuthRepoImpl implements AuthRepo {
    private final SessionFactory sessionFactory;

    public AuthRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean insert(RegisterCustomerDTO registerCustomerDTO) {
        Customer c = new Customer();
        c.setEmail(registerCustomerDTO.getEmail());
        c.setPassword(registerCustomerDTO.getPassword());
        c.setPhone(registerCustomerDTO.getPhone());
        c.setUsername(registerCustomerDTO.getUsername());
        c.setRole(Role.CUSTOMER);
        c.setStatus(UserStatus.ACTIVE);
        try {
            getSession().persist(c);
            return true;
        } catch (Exception e) {
            System.err.println("Lỗi" + e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return getSession().createQuery("from Customer where email = :e", Customer.class)
                .setParameter("e", email).uniqueResultOptional();
    }

}
