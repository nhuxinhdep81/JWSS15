package com.tien.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class SearchQueryDTO {
    @NotBlank(message = "Vui lòng nhập tên hoặc mã sản phẩm.")
    private String searchQuery;

    // Getters and Setters
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}