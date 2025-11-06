package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.CategoryRequest;
import com.code_crafters.app.dto.response.CategoryResponse;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(UUID id);
    CategoryResponse getCategoryByName(String name);
    CategoryResponse createCategory(CategoryRequest request);
}