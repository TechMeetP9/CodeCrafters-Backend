package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.CategoryRequest;
import com.code_crafters.app.dto.response.CategoryResponse;
import com.code_crafters.app.entity.Category;
import com.code_crafters.app.mapper.CategoryMapper;
import com.code_crafters.app.repository.CategoryRepository;
import com.code_crafters.app.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                              CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
    
    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
            .map(categoryMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    public CategoryResponse getCategoryById(UUID id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toResponse(category); 
    }
    
    @Override
    public CategoryResponse getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Category not found: " + name));
        return categoryMapper.toResponse(category);
    }
    
    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category already exists");
        }
        
        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }
}