package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.entity.Events;

import java.util.List;
import java.util.Optional;

public interface EventsService {

    Events createEvent(EventsRequest request);

    Optional<Events> findById(Long id);

    List<Events> findAll();

    Events updateEvent(Long id, EventsRequest request);

    void deleteEvent(Long id);
}
