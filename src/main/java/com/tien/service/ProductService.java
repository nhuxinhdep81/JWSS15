package com.tien.service;

import com.tien.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
}
