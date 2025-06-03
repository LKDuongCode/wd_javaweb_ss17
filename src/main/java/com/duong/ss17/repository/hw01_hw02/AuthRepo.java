package com.duong.ss17.repository.hw01_hw02;

import com.duong.ss17.dto.hw01_hw02.RegisterCustomerDTO;
import com.duong.ss17.entity.hw01_hw02.Customer;

import java.util.Optional;

public interface AuthRepo {
    boolean insert (RegisterCustomerDTO registerCustomerDTO);
    Optional<Customer> findByEmail (String email);
}
