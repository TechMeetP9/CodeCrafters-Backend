package com.code_crafters.app.repository;

import com.code_crafters.app.entity.Attendance;
import com.code_crafters.app.entity.Event;
import com.code_crafters.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {

    /**
     * Busca un registro de asistencia específico de un usuario a un evento
     */
    Optional<Attendance> findByUserAndEvent(User user, Event event);

    /**
     * Obtiene todos los asistentes de un evento
     */
    List<Attendance> findAllByEvent(Event event);

    /**
     * Obtiene todos los eventos a los que asiste un usuario
     */
    List<Attendance> findAllByUser(User user);

    /**
     * Cuenta el número de asistentes de un evento
     */
    long countByEvent(Event event);

    /**
     * Verifica si un usuario está registrado en un evento
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Attendance a WHERE a.user.id = :userId AND a.event.id = :eventId")
    boolean existsByUserIdAndEventId(@Param("userId") UUID userId, @Param("eventId") UUID eventId);

    /**
     * Elimina todas las asistencias de un evento (útil al eliminar un evento)
     */
    void deleteAllByEvent(Event event);
}