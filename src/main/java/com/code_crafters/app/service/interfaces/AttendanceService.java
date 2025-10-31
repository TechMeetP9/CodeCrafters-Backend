package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.AttendanceRequest;
import com.code_crafters.app.dto.response.AttendanceResponse;

import java.util.List;
import java.util.UUID;

public interface AttendanceService {
    AttendanceResponse registerAttendance(AttendanceRequest request);
    void unregisterAttendance(AttendanceRequest request);
    List<AttendanceResponse> getAttendees(UUID eventId);
}
