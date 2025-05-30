package com.tien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private int id;
    private int productId;
    private int userId;
    private int rating;
    private String comment;

}
