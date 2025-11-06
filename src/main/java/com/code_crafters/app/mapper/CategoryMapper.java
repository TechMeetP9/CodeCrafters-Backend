package com.code_crafters.app.mapper;

import com.code_crafters.app.dto.request.CategoryRequest;
import com.code_crafters.app.dto.response.CategoryResponse;
import com.code_crafters.app.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    
    CategoryResponse toResponse(Category category);
    
    Category toEntity(CategoryRequest request);
}