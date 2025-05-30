package com.tien.service;

import com.tien.model.Cart;
import com.tien.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addOrUpdateCart(int userId, int productId, int quantity) {
        cartRepository.addOrUpdateCart(userId, productId, quantity);
    }

    @Override
    public List<Cart> getCartByUserId(int userId) {
        return cartRepository.getCartByUserId(userId);
    }

    @Override
    public void clearCartByUser(int userId) {
        cartRepository.clearCartByUser(userId);
    }
}
