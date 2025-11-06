package com.code_crafters.app.repository;

import com.code_crafters.app.entity.Event;
import com.code_crafters.app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    

    Page<Event> findByCreator(User creator, Pageable pageable);
    List<Event> findByCreator(User creator);
    

    Page<Event> findByCategory_Name(String categoryName, Pageable pageable);
    

    Page<Event> findByCreator_Username(String username, Pageable pageable);
    

    Page<Event> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    

    Page<Event> findByEventDate(LocalDate eventDate, Pageable pageable);
    

    Page<Event> findByEventDateGreaterThanEqual(LocalDate eventDate, Pageable pageable);
    

    @Query("SELECT e FROM Event e WHERE " +
           "(:categoryName IS NULL OR e.category.name = :categoryName) AND " +
           "(:username IS NULL OR e.creator.username = :username) AND " +
           "(:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:eventDate IS NULL OR e.eventDate = :eventDate)")
    Page<Event> findByFilters(
        @Param("categoryName") String categoryName,
        @Param("username") String username,
        @Param("title") String title,
        @Param("eventDate") LocalDate eventDate,
        Pageable pageable
    );
}