package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.CategoryRequest;
import com.code_crafters.app.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    Category create(CategoryRequest request);
    Optional<Category> findById(UUID id);
    List<Category> findAll();
    Category update(UUID id, CategoryRequest request);
    void delete(UUID id);
}
