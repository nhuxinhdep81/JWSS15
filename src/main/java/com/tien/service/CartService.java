package com.tien.service;

import com.tien.model.Cart;
import java.util.List;

public interface CartService {
    void addOrUpdateCart(int userId, int productId, int quantity);
    List<Cart> getCartByUserId(int userId);
    void clearCartByUser(int userId);

}
