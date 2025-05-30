package com.tien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double currentPrice;
}
