package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.CategoryRequest;
import com.code_crafters.app.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(CategoryRequest request);

    Optional<Category> findById(Long id);

    List<Category> findAll();

    Category updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);
}
