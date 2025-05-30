package com.tien.repository;

import com.tien.model.Cart;
import java.util.List;

public interface CartRepository {
    void addOrUpdateCart(int userId, int productId, int quantity);
    List<Cart> getCartByUserId(int userId);
    void clearCartByUser(int userId);

}
