package com.tien.repository;

import com.tien.config.ConnectionDB;
import com.tien.model.Review;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    @Override
    public void addReview(Review review) {
        String sql = "{CALL insert_review(?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, review.getProductId());
            cs.setInt(2, review.getUserId());
            cs.setInt(3, review.getRating());
            cs.setString(4, review.getComment());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Review> findByProductId(int productId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "{CALL get_reviews_by_product_id(?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, productId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setProductId(rs.getInt("id_product"));
                review.setUserId(rs.getInt("id_user"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

}
