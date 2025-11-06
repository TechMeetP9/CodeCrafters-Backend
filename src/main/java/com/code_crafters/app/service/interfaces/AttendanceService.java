package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.response.AttendanceResponse;
import com.code_crafters.app.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface AttendanceService {
    AttendanceResponse joinEvent(UUID eventId);
    void leaveEvent(UUID eventId);
    List<UserResponse> getEventAttendees(UUID eventId);
    List<AttendanceResponse> getUserAttendances();
    boolean isUserAttending(UUID eventId);
}