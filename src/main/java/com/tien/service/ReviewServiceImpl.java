package com.tien.service;

import com.tien.model.Review;
import com.tien.repository.ReviewRepository;
import com.tien.repository.ReviewRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void addReview(Review review) {
        reviewRepository.addReview(review);
    }

    @Override
    public List<Review> getReviewsByProduct(int productId) {
        return reviewRepository.findByProductId(productId);
    }
}
