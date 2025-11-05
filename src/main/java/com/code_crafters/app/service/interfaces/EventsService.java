package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.dto.response.EventResponse;
import com.code_crafters.app.entity.Event;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventsService {
    EventResponse createEvent(EventsRequest request);
    Optional<Event> findById(UUID id);
    List<Event> findAll();
    Page<Event> findAllPaginated(int page, int size);
    EventResponse updateEvent(UUID id, EventsRequest request);
    void deleteEvent(UUID id);
}
