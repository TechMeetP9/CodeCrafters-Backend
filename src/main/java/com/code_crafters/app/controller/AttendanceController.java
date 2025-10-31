package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.AttendanceRequest;
import com.code_crafters.app.dto.response.AttendanceResponse;
import com.code_crafters.app.service.interfaces.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponse> joinEvent(@RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.registerAttendance(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> leaveEvent(@RequestBody AttendanceRequest request) {
        attendanceService.unregisterAttendance(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<AttendanceResponse>> listAttendees(@PathVariable UUID eventId) {
        return ResponseEntity.ok(attendanceService.getAttendees(eventId));
    }
}
