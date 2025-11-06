package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.EventRequest;
import com.code_crafters.app.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventService {
    Page<EventResponse> getAllEvents(Pageable pageable);
    EventResponse getEventById(UUID id);
    EventResponse createEvent(EventRequest request);
    EventResponse updateEvent(UUID id, EventRequest request);
    void deleteEvent(UUID id);
    Page<EventResponse> getEventsByCreator(Pageable pageable);
    List<EventResponse> getEventsByCreatorList();
    Page<EventResponse> filterEvents(String categoryName, String username, String title, 
                                    LocalDate eventDate, Pageable pageable);
}