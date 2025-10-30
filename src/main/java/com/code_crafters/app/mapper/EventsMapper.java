package com.code_crafters.app.mapper;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.dto.response.EventsResponse;
import com.code_crafters.app.entity.Events;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "attendees", ignore = true)
    Events toEntity(EventsRequest dto);

    @Mapping(target = "userId", source = "creator.id")
    @Mapping(target = "category", source = "category.name")
    EventsResponse toDto(Events event);

    void updateEntityFromDto(EventsRequest dto, @MappingTarget Events entity);
}
