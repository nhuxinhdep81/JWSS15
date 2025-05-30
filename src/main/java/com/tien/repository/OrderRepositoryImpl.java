package com.tien.repository;

import com.tien.config.ConnectionDB;
import com.tien.model.Order;
import com.tien.model.OrderDetail;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public int insertOrder(Order order) {
        String sql = "{CALL insert_order(?, ?, ?, ?, ?)}";  // Thêm tham số OUT
        int orderId = 0;
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, order.getUserId());
            cs.setString(2, order.getRecipientName());
            cs.setString(3, order.getAddress());
            cs.setString(4, order.getPhoneNumber());
            cs.registerOutParameter(5, Types.INTEGER);

            cs.executeUpdate();

            orderId = cs.getInt(5);  // Lấy order_id trả về
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }


    @Override
    public void insertOrderDetail(OrderDetail orderDetail) {
        String sql = "{CALL insert_order_detail(?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, orderDetail.getOrderId());
            cs.setInt(2, orderDetail.getProductId());
            cs.setInt(3, orderDetail.getQuantity());
            cs.setDouble(4, orderDetail.getCurrentPrice());
            cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Order> getOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "{CALL get_orders_by_user(?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setRecipientName(rs.getString("recipient_name"));
                    order.setAddress(rs.getString("address"));
                    order.setPhoneNumber(rs.getString("phone_number"));
                    order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "{CALL get_order_details_by_order_id(?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, orderId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    OrderDetail detail = new OrderDetail();
                    detail.setId(rs.getInt("id"));
                    detail.setOrderId(rs.getInt("order_id"));
                    detail.setProductId(rs.getInt("product_id"));
                    detail.setQuantity(rs.getInt("quantity"));
                    detail.setCurrentPrice(rs.getDouble("current_price"));
                    orderDetails.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }


}
