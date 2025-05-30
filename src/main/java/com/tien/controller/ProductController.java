package com.tien.controller;

import com.tien.dto.ProductDTO;
import com.tien.dto.SearchQueryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    // Danh sách sản phẩm giả lập
    private static final List<ProductDTO> productList = new ArrayList<>();

    static {
        productList.add(new ProductDTO("P001", "Laptop Dell", 1200.0));
        productList.add(new ProductDTO("P002", "iPhone 14", 999.0));
        productList.add(new ProductDTO("P003", "Samsung TV", 1500.0));
        productList.add(new ProductDTO("P004", "MacBook Pro", 2000.0));
    }

    // Hiển thị form tìm kiếm
    @GetMapping("/search-product")
    public String showSearchForm(Model model) {
        model.addAttribute("searchQuery", new SearchQueryDTO());
        model.addAttribute("products", new ArrayList<ProductDTO>());
        return "search-product";
    }

    // Xử lý tìm kiếm
    @PostMapping("/search-product")
    public String searchProduct(@ModelAttribute("searchQuery") @Valid SearchQueryDTO searchQuery,
                                BindingResult result,
                                Model model) {
        // Nếu có lỗi xác thực, trả lại form
        if (result.hasErrors()) {
            model.addAttribute("products", new ArrayList<ProductDTO>());
            return "search-product";
        }

        // Tìm kiếm sản phẩm
        List<ProductDTO> searchResults = productList.stream()
                .filter(product ->
                        product.getId().toLowerCase().contains(searchQuery.getSearchQuery().toLowerCase()) ||
                                product.getName().toLowerCase().contains(searchQuery.getSearchQuery().toLowerCase()))
                .collect(Collectors.toList());

        // Truyền kết quả tìm kiếm vào model
        model.addAttribute("products", searchResults);
        return "search-product";
    }
}