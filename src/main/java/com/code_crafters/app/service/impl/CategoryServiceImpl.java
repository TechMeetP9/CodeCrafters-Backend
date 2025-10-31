package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.CategoryRequest;
import com.code_crafters.app.entity.Category;
import com.code_crafters.app.mapper.CategoryMapper;
import com.code_crafters.app.repository.CategoryRepository;
import com.code_crafters.app.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category create(CategoryRequest request) {
        categoryRepository.findByName(request.getName()).ifPresent(c -> {
            throw new IllegalArgumentException("Category already exists: " + c.getName());
        });

        Category category = categoryMapper.toEntity(request);
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Long id, CategoryRequest request) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));

        existing.setName(request.getName());
        return categoryRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
        categoryRepository.delete(existing);
    }
}
