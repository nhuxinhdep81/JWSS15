package com.tien.repository;

import com.tien.model.Review;
import java.util.List;

public interface ReviewRepository {
    void addReview(Review review);
    List<Review> findByProductId(int productId);
}
