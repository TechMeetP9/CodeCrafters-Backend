package com.code_crafters.app.mapper;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.dto.response.EventResponse;
import com.code_crafters.app.entity.Event;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventsMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)          
    @Mapping(target = "category", ignore = true)         
    @Mapping(target = "location", ignore = true)        
    @Mapping(target = "createdAt", ignore = true)       
    @Mapping(target = "updatedAt", ignore = true)        
    @Mapping(target = "currentAttendees", constant = "0")
    @Mapping(target = "eventDate", source = "dateTime")  
    @Mapping(target = "eventTime", source = "dateTime")  
    @Mapping(target = "duration", ignore = true)         
    @Mapping(target = "imageUrl", ignore = true)  
    Event toEntity(EventsRequest dto);

    @AfterMapping
    default void splitDateTime(@MappingTarget Event event, EventsRequest dto) {
        if (dto.getDateTime() != null) {
            event.setEventDate(dto.getDateTime().toLocalDate());
            event.setEventTime(dto.getDateTime().toLocalTime());
        }
    }

    @Mapping(target = "location", source = "location.name")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "userId", source = "creator.id")
    EventResponse toDto(Event event);

    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "currentAttendees", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "eventDate", ignore = true)
    @Mapping(target = "eventTime", ignore = true)
    void updateEntityFromDto(EventsRequest dto, @MappingTarget Event entity);

    @AfterMapping
    default void splitDateTimeUpdate(@MappingTarget Event event, EventsRequest dto) {
        if (dto.getDateTime() != null) {
            event.setEventDate(dto.getDateTime().toLocalDate());
            event.setEventTime(dto.getDateTime().toLocalTime());
        }
    }
}
