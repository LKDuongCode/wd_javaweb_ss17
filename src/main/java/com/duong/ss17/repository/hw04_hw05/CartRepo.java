package com.duong.ss17.repository.hw04_hw05;

import com.duong.ss17.dto.hw04_hw05.AddCartDTO;
import com.duong.ss17.dto.hw04_hw05.UpdateCartDTO;
import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.entity.hw04_hw05.Cart;

import java.util.List;

public interface CartRepo {
    boolean insert(AddCartDTO dto);
    boolean updateQuantity(UpdateCartDTO dto);
    boolean delete(int id);


    List<Cart> findByCustomerId(int customerId);
    double calculateTotalPrice(int customerId);

    List<Product> getProductsInCart(int customerId);
    double getTotalMoney(int customerId);
    void clearCart(int customerId);

}

