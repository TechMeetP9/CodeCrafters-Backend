package com.code_crafters.app.mapper;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.dto.response.EventResponse;
import com.code_crafters.app.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = { LocationMapper.class })
public interface EventsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "location", ignore = true) 
    Event toEntity(EventsRequest dto);

    @Mapping(target = "userId", source = "creator.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "location", source = "location.name")
    EventResponse toDto(Event event);

    void updateEntityFromDto(EventsRequest dto, @MappingTarget Event entity);
}
