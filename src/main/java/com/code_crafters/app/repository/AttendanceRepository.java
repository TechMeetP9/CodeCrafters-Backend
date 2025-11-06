package com.code_crafters.app.repository;

import com.code_crafters.app.entity.Attendance;
import com.code_crafters.app.entity.Event;
import com.code_crafters.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    

    boolean existsByUserAndEvent(User user, Event event);

    Optional<Attendance> findByUserAndEvent(User user, Event event);

    List<Attendance> findByEvent(Event event);

    List<Attendance> findByUser(User user);
    
    long countByEvent(Event event);
}