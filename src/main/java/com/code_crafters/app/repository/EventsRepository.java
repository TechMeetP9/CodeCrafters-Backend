package com.code_crafters.app.repository;

import com.code_crafters.app.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EventsRepository extends JpaRepository<Event, UUID> {
}
