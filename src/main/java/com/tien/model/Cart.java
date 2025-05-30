package com.tien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    private int idCart;
    private int userId;
    private int productId;
    private int quantity;
    private String productName;
    private double productPrice;



}
