package com.duong.ss17.service.hw04_hw05;

import com.duong.ss17.dto.hw04_hw05.AddCartDTO;
import com.duong.ss17.dto.hw04_hw05.UpdateCartDTO;
import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.entity.hw04_hw05.Cart;
import com.duong.ss17.repository.hw04_hw05.CartRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;

    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public boolean addToCart(AddCartDTO dto) {
        return cartRepo.insert(dto);
    }

    @Override
    public boolean updateCart(UpdateCartDTO dto) {
        return cartRepo.updateQuantity(dto);
    }

    @Override
    public boolean removeCart(int id) {
        return cartRepo.delete(id);
    }
    @Override
    public List<Cart> getCartByCustomer(int customerId) {
        return cartRepo.findByCustomerId(customerId);
    }

    @Override
    public double getCartTotal(int customerId) {
        return cartRepo.calculateTotalPrice(customerId);
    }


    @Override
    public List<Product> getProductsInCart(int customerId) {
        return cartRepo.getProductsInCart(customerId);
    }

    @Override
    public double getTotalMoney(int customerId) {
        return cartRepo.getTotalMoney(customerId);
    }

    @Override
    public void clearCart(int customerId) {
        cartRepo.clearCart(customerId);
    }
}
