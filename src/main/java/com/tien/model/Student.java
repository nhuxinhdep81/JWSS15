package com.tien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private String id;
    private String name;
    private int age;
    private String className;
    private String email;
    private String address;
    private String phone;
}
