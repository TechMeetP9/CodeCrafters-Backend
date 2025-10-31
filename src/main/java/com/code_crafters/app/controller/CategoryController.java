package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.CategoryRequest;
import com.code_crafters.app.dto.response.CategoryResponse;
import com.code_crafters.app.entity.Category;
import com.code_crafters.app.mapper.CategoryMapper;
import com.code_crafters.app.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .<ResponseEntity<?>>map(cat -> ResponseEntity.ok(categoryMapper.toDto(cat)))
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("message", "Category not found")));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> list = categoryService.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request) {
        try {
            Category created = categoryService.createCategory(request);
            return ResponseEntity.ok(categoryMapper.toDto(created));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error creating category"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        try {
            Category updated = categoryService.updateCategory(id, request);
            return ResponseEntity.ok(categoryMapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error updating category"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(Map.of("message", "Category deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error deleting category"));
        }
    }
}
