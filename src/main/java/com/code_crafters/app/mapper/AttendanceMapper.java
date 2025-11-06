package com.code_crafters.app.mapper;

import com.code_crafters.app.dto.response.AttendanceResponse;
import com.code_crafters.app.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.name", target = "userFullName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.title", target = "eventTitle")
    AttendanceResponse toResponse(Attendance attendance);
}
