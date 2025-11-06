package com.code_crafters.app.controller;

import com.code_crafters.app.dto.response.AttendanceResponse;
import com.code_crafters.app.dto.response.UserResponse;
import com.code_crafters.app.service.interfaces.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    
    private AttendanceService attendanceService;
    
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    

    @PostMapping("/join/{eventId}")
    public ResponseEntity<?> joinEvent(@PathVariable UUID eventId) {
        try {
            AttendanceResponse attendance = attendanceService.joinEvent(eventId);
            return new ResponseEntity<>(attendance, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    

    @DeleteMapping("/leave/{eventId}")
    public ResponseEntity<?> leaveEvent(@PathVariable UUID eventId) {
        try {
            attendanceService.leaveEvent(eventId);
            return new ResponseEntity<>("You have successfully left the event", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/event/{eventId}/attendees")
    public ResponseEntity<List<UserResponse>> getEventAttendees(@PathVariable UUID eventId) {
        try {
            List<UserResponse> attendees = attendanceService.getEventAttendees(eventId);
            return new ResponseEntity<>(attendees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/my-attendances")
    public ResponseEntity<List<AttendanceResponse>> getMyAttendances() {
        return new ResponseEntity<>(attendanceService.getUserAttendances(), HttpStatus.OK);
    }
    

    @GetMapping("/is-attending/{eventId}")
    public ResponseEntity<Boolean> isUserAttending(@PathVariable UUID eventId) {
        try {
            boolean isAttending = attendanceService.isUserAttending(eventId);
            return new ResponseEntity<>(isAttending, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}