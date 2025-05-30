package com.tien.repository;


import com.tien.model.Order;
import com.tien.model.OrderDetail;

import java.util.List;

public interface OrderRepository {
    int insertOrder(Order order);
    void insertOrderDetail(OrderDetail orderDetail);

    List<Order> getOrdersByUser(int userId);
    List<OrderDetail> getOrderDetailsByOrderId(int orderId);
}
