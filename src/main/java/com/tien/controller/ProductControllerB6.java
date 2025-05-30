package com.tien.controller;

import com.tien.model.Product;
import com.tien.model.Review;
import com.tien.repository.ProductRepository;
import com.tien.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductControllerB6 {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productRepo.getAllProducts());
        return "listProduct";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        Product product = productRepo.getProductById(id);
        List<Review> reviews = reviewRepo.findByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("review", new Review());
        return "productDetail";
    }

    @PostMapping("/products/{id}/review")
    public String submitReview(@PathVariable int id,
                               @ModelAttribute("review") @Valid Review review,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", productRepo.getProductById(id));
            model.addAttribute("reviews", reviewRepo.findByProductId(id));
            return "productDetail";
        }
        review.setProductId(id);
        reviewRepo.addReview(review);
        return "redirect:/products/" + id;
    }
}
