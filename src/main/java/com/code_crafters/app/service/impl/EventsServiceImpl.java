package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.dto.response.EventResponse;
import com.code_crafters.app.entity.Category;
import com.code_crafters.app.entity.Event;
import com.code_crafters.app.entity.Location;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.EventsMapper;
import com.code_crafters.app.repository.CategoryRepository;
import com.code_crafters.app.repository.EventsRepository;
import com.code_crafters.app.repository.LocationRepository;
import com.code_crafters.app.repository.UsersRepository;
import com.code_crafters.app.service.interfaces.EventsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;
    private final UsersRepository usersRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final EventsMapper eventsMapper;

    @Override
    public EventResponse createEvent(EventsRequest request) {
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + request.getUserId()));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + request.getCategoryId()));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID: " + request.getLocationId()));

        Event event = eventsMapper.toEntity(request);
        event.setCreator(user);
        event.setCategory(category);
        event.setLocation(location);

        Event savedEvent = eventsRepository.save(event);
        return eventsMapper.toDto(savedEvent);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return eventsRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return eventsRepository.findAll();
    }

    @Override
    public EventResponse updateEvent(UUID id, EventsRequest request) {
        Event existingEvent = eventsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + id));

        eventsMapper.updateEntityFromDto(request, existingEvent);

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + request.getCategoryId()));
            existingEvent.setCategory(category);
        }

        if (request.getLocationId() != null) {
            Location location = locationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new EntityNotFoundException("Location not found with ID: " + request.getLocationId()));
            existingEvent.setLocation(location);
        }

        Event updated = eventsRepository.save(existingEvent);
        return eventsMapper.toDto(updated);
    }

    @Override
    public void deleteEvent(UUID id) {
        Event event = eventsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + id));
        eventsRepository.delete(event);
    }
}
