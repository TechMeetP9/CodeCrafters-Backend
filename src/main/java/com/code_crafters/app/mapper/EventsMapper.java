package com.code_crafters.app.mapper;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.dto.response.EventResponse;
import com.code_crafters.app.entity.Category;
import com.code_crafters.app.entity.Events;
import com.code_crafters.app.repository.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EventsMapper {

    @Autowired
    protected CategoryRepository categoryRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "category", expression = "java(categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException(\"Category not found\")))")
    public abstract Events toEntity(EventsRequest dto);

    @Mapping(target = "userId", source = "creator.id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    public abstract EventResponse toDto(Events event);

    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "category", expression = "java(categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException(\"Category not found\")))")
    public abstract void updateEntityFromDto(EventsRequest dto, @MappingTarget Events entity);
}
