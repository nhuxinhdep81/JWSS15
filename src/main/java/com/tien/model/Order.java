package com.tien.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private int orderId;
    private int userId;
    private String recipientName;
    private String address;
    private String phoneNumber;
    private LocalDateTime orderDate;
}
