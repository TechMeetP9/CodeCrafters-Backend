package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.response.AttendanceResponse;
import com.code_crafters.app.dto.response.UserResponse;
import com.code_crafters.app.entity.Attendance;
import com.code_crafters.app.entity.Event;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.AttendanceMapper;
import com.code_crafters.app.mapper.UserMapper;
import com.code_crafters.app.repository.AttendanceRepository;
import com.code_crafters.app.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.code_crafters.app.service.interfaces.AttendanceService;
import com.code_crafters.app.service.interfaces.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    
    private AttendanceRepository attendanceRepository;
    private EventRepository eventRepository;
    private AttendanceMapper attendanceMapper;
    private UserMapper userMapper;
    private UserService userService;
    
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                EventRepository eventRepository,
                                AttendanceMapper attendanceMapper,
                                UserMapper userMapper,
                                UserService userService) {
        this.attendanceRepository = attendanceRepository;
        this.eventRepository = eventRepository;
        this.attendanceMapper = attendanceMapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }
    
    @Override
    @Transactional
    public AttendanceResponse joinEvent(UUID eventId) {
        User currentUser = userService.getCurrentUser();
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        

        if (event.getCreator().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puedes apuntarte a tu propio evento");
        }

        if (attendanceRepository.existsByUserAndEvent(currentUser, event)) {
            throw new RuntimeException("You can't sign up for your own event");
        }
        

        if (event.getCurrentAttendees() >= event.getCapacity()) {
            throw new RuntimeException("The event has rached its maximum capacity");
        }
        

        Attendance attendance = Attendance.builder()
            .user(currentUser)
            .event(event)
            .build();
        
        attendance = attendanceRepository.save(attendance);
        
     
        event.setCurrentAttendees(event.getCurrentAttendees() + 1);
        eventRepository.save(event);
        
        return attendanceMapper.toResponse(attendance);
    }
    
    @Override
    @Transactional
    public void leaveEvent(UUID eventId) {
        User currentUser = userService.getCurrentUser();
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        

        Attendance attendance = attendanceRepository.findByUserAndEvent(currentUser, event)
            .orElseThrow(() -> new RuntimeException("You are not registered for this event"));
        

        attendanceRepository.delete(attendance);
        

        event.setCurrentAttendees(Math.max(0, event.getCurrentAttendees() - 1));
        eventRepository.save(event);
    }
    
    @Override
    public List<UserResponse> getEventAttendees(UUID eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        
        return attendanceRepository.findByEvent(event).stream()
            .map(attendance -> userMapper.toResponse(attendance.getUser()))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<AttendanceResponse> getUserAttendances() {
        User currentUser = userService.getCurrentUser();
        return attendanceRepository.findByUser(currentUser).stream()
            .map(attendanceMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    public boolean isUserAttending(UUID eventId) {
        User currentUser = userService.getCurrentUser();
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        
        return attendanceRepository.existsByUserAndEvent(currentUser, event);
    }
}
                            