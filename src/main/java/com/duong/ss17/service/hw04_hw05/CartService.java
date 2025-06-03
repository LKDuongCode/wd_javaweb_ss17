package com.duong.ss17.service.hw04_hw05;

import com.duong.ss17.dto.hw04_hw05.AddCartDTO;
import com.duong.ss17.dto.hw04_hw05.UpdateCartDTO;
import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.entity.hw04_hw05.Cart;

import java.util.List;

public interface CartService {
    boolean addToCart(AddCartDTO dto);
    boolean updateCart(UpdateCartDTO dto);
    boolean removeCart(int id);


    List<Cart> getCartByCustomer(int customerId);
    double getCartTotal(int customerId);
}
