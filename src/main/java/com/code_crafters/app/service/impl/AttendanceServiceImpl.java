package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.AttendanceRequest;
import com.code_crafters.app.dto.response.AttendanceResponse;
import com.code_crafters.app.entity.Attendance;
import com.code_crafters.app.entity.Event;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.AttendanceMapper;
import com.code_crafters.app.repository.AttendanceRepository;
import com.code_crafters.app.repository.EventsRepository;
import com.code_crafters.app.repository.UsersRepository;
import com.code_crafters.app.service.interfaces.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UsersRepository usersRepository;
    private final EventsRepository eventsRepository;
    private final AttendanceMapper mapper;

    @Override
    public AttendanceResponse registerAttendance(AttendanceRequest request) {
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventsRepository.findById(request.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        if (attendanceRepository.findByUserAndEvent(user, event).isPresent()) {
            throw new IllegalArgumentException("User already registered for this event");
        }

        Attendance attendance = Attendance.builder()
                .user(user)
                .event(event)
                .joinedAt(java.time.LocalDateTime.now())
                .build();

        Attendance saved = attendanceRepository.save(attendance);
        return mapper.toResponse(saved);
    }

    @Override
    public void unregisterAttendance(AttendanceRequest request) {
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventsRepository.findById(request.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        Attendance attendance = attendanceRepository.findByUserAndEvent(user, event)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }

    @Override
    public List<AttendanceResponse> getAttendees(UUID eventId) {
        Event event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        return attendanceRepository.findAllByEvent(event)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
