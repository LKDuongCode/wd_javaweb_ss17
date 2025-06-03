package com.duong.ss17.service.hw01_hw02;

import com.duong.ss17.dto.hw01_hw02.LoginUserDTO;
import com.duong.ss17.dto.hw01_hw02.RegisterCustomerDTO;
import com.duong.ss17.entity.hw01_hw02.Customer;
import com.duong.ss17.repository.hw01_hw02.AuthRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepo authRepo;

    public AuthServiceImpl(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public boolean insert(RegisterCustomerDTO registerCustomerDTO) {
        return authRepo.insert(registerCustomerDTO);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return authRepo.findByEmail(email);
    }

    @Override
    public boolean login(LoginUserDTO loginUserDTO) {
        Optional<Customer> optionalCustomer = findByEmail(loginUserDTO.getEmail());
        if(optionalCustomer.isEmpty()) return false;

        Customer c = optionalCustomer.get();
        String inputPassword = loginUserDTO.getPassword();
        String correctPassword = c.getPassword();

        return inputPassword.equals(correctPassword);
    }
}
