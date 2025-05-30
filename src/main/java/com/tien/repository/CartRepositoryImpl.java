package com.tien.repository;

import com.tien.config.ConnectionDB;
import com.tien.model.Cart;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {

    @Override
    public void addOrUpdateCart(int userId, int productId, int quantity) {
        String sql = "{CALL upsert_cart(?, ?, ?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            cs.setInt(2, productId);
            cs.setInt(3, quantity);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cart> getCartByUserId(int userId) {
        List<Cart> carts = new ArrayList<>();
        String sql = "{CALL get_cart_by_user(?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart(
                        rs.getInt("id_cart"),
                        rs.getInt("id_user"),
                        rs.getInt("id_product"),
                        rs.getInt("quantity"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public void clearCartByUser(int userId) {
        String sql = "DELETE FROM cart WHERE id_user = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
