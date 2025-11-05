package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.dto.response.EventResponse;
import com.code_crafters.app.mapper.EventsMapper;
import com.code_crafters.app.service.interfaces.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import com.code_crafters.app.entity.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventsService eventsService;
    private final EventsMapper eventsMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable UUID id) {
        return eventsService.findById(id)
                .<ResponseEntity<?>>map(event -> ResponseEntity.ok(eventsMapper.toDto(event)))
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("message", "Event not found")));
    }
@GetMapping
public ResponseEntity<Map<String, Object>> getAllEvents(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "12") int size) {
    
    Page<Event> eventsPage = eventsService.findAllPaginated(page, size);
    
    List<EventResponse> events = eventsPage.getContent()
            .stream()
            .map(eventsMapper::toDto)
            .collect(Collectors.toList());
    
    Map<String, Object> response = new HashMap<>();
    response.put("events", events);
    response.put("currentPage", eventsPage.getNumber() + 1); // Frontend usa páginas desde 1
    response.put("totalPages", eventsPage.getTotalPages());
    response.put("totalItems", eventsPage.getTotalElements());
    
    return ResponseEntity.ok(response);
}

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventsRequest request) {
        try {
            EventResponse created = eventsService.createEvent(request);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error creating event"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable UUID id, @RequestBody EventsRequest request) {
        try {
            EventResponse updated = eventsService.updateEvent(id, request);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error updating event"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable UUID id) {
        try {
            eventsService.deleteEvent(id);
            return ResponseEntity.ok(Map.of("message", "Event deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error deleting event"));
        }
    }
}
