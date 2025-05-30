package com.tien.repository;

import com.tien.model.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(int id);
}
