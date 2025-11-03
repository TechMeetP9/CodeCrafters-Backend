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
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
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
    @Transactional
    public AttendanceResponse registerAttendance(AttendanceRequest request) {
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + request.getUserId()));
        
        Event event = eventsRepository.findById(request.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + request.getEventId()));

        // Verificar si ya está registrado
        if (attendanceRepository.findByUserAndEvent(user, event).isPresent()) {
            throw new IllegalArgumentException("User is already registered for this event");
        }

        // Verificar capacidad del evento
        if (event.getCurrentAttendees() >= event.getCapacity()) {
            throw new IllegalArgumentException("Event has reached maximum capacity");
        }

        // Crear registro de asistencia
        Attendance attendance = Attendance.builder()
                .user(user)
                .event(event)
                .joinedAt(LocalDateTime.now())
                .build();

        Attendance saved = attendanceRepository.save(attendance);

        // Incrementar contador de asistentes
        event.setCurrentAttendees(event.getCurrentAttendees() + 1);
        eventsRepository.save(event);

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void unregisterAttendance(AttendanceRequest request) {
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + request.getUserId()));
        
        Event event = eventsRepository.findById(request.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + request.getEventId()));

        // Buscar el registro de asistencia
        Attendance attendance = attendanceRepository.findByUserAndEvent(user, event)
                .orElseThrow(() -> new IllegalArgumentException("User is not registered for this event"));

        // Eliminar registro de asistencia
        attendanceRepository.delete(attendance);

        // Decrementar contador de asistentes (evitar valores negativos)
        event.setCurrentAttendees(Math.max(0, event.getCurrentAttendees() - 1));
        eventsRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceResponse> getAttendees(UUID eventId) {
        Event event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));

        return attendanceRepository.findAllByEvent(event)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}