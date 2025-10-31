package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventsService {

    Event createEvent(EventsRequest request);

    Optional<Event> findById(Long id);

    List<Event> findAll();

    Event updateEvent(Long id, EventsRequest request);

    void deleteEvent(Long id);
}
