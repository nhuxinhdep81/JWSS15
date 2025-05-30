package com.tien.service;

import com.tien.model.Review;
import java.util.List;

public interface ReviewService {
    void addReview(Review review);
    List<Review> getReviewsByProduct(int productId);
}
