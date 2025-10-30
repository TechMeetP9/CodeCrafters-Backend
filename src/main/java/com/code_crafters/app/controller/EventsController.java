package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.dto.response.EventsResponse;
import com.code_crafters.app.entity.Events;
import com.code_crafters.app.mapper.EventsMapper;
import com.code_crafters.app.service.interfaces.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private EventsMapper eventsMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        return eventsService.findById(id)
                .map(event -> ResponseEntity.ok(eventsMapper.toDto(event)))
                .orElse(ResponseEntity.status(404).body(Map.of("message", "Event not found")));
    }

    @GetMapping
    public ResponseEntity<List<EventsResponse>> getAllEvents() {
        List<EventsResponse> events = eventsService.findAll()
                .stream()
                .map(eventsMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventsRequest request) {
        try {
            Events created = eventsService.createEvent(request);
            return ResponseEntity.ok(eventsMapper.toDto(created));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error creating event"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody EventsRequest request) {
        try {
            Events updated = eventsService.updateEvent(id, request);
            return ResponseEntity.ok(eventsMapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error updating event"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
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
