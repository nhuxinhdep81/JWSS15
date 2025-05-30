package com.tien.service;

import com.tien.model.Order;
import com.tien.model.OrderDetail;

import java.util.List;

public interface OrderService {
    int createOrder(Order order, List<OrderDetail> orderDetails);
    List<Order> getOrdersByUser(int userId);
    List<OrderDetail> getOrderDetailsByOrderId(int orderId);
}
