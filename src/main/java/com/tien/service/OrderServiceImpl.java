package com.tien.service;

import com.tien.model.Order;
import com.tien.model.OrderDetail;
import com.tien.repository.OrderRepository;
import com.tien.repository.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public int createOrder(Order order, List<OrderDetail> orderDetails) {
        int orderId = orderRepository.insertOrder(order);
        if (orderId > 0) {
            for (OrderDetail detail : orderDetails) {
                detail.setOrderId(orderId);
                orderRepository.insertOrderDetail(detail);
            }
            return orderId;
        }
        return 0;
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        return orderRepository.getOrdersByUser(userId);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return orderRepository.getOrderDetailsByOrderId(orderId);
    }
}
