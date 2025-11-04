package com.code_crafters.app.mapper;


import com.code_crafters.app.dto.response.AttendanceResponse;
import com.code_crafters.app.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public AttendanceResponse toResponse(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .userId(attendance.getUser().getId())
                .eventId(attendance.getEvent().getId())
                .userName(attendance.getUser().getName())      
                .eventName(attendance.getEvent().getTitle())
                .build();   
    }
}
