package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.EventRequest;
import com.code_crafters.app.dto.response.EventResponse;
import com.code_crafters.app.entity.Category;
import com.code_crafters.app.entity.Event;
import com.code_crafters.app.entity.Location;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.EventMapper;
import com.code_crafters.app.repository.CategoryRepository;
import com.code_crafters.app.repository.EventRepository;
import com.code_crafters.app.repository.LocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.code_crafters.app.service.interfaces.EventService;
import com.code_crafters.app.service.interfaces.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    
    private EventRepository eventRepository;
    private CategoryRepository categoryRepository;
    private LocationRepository locationRepository;
    private EventMapper eventMapper;
    private UserService userService;
    
    public EventServiceImpl(EventRepository eventRepository,
                           CategoryRepository categoryRepository,
                           LocationRepository locationRepository,
                           EventMapper eventMapper,
                           UserService userService) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.eventMapper = eventMapper;
        this.userService = userService;
    }
    
    @Override
    public Page<EventResponse> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable)
            .map(eventMapper::toResponse);
    }
    
    @Override
    public EventResponse getEventById(UUID id) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Eventnot found with id: " + id));
        return eventMapper.toResponse(event);
    }
    
    @Override
    public EventResponse createEvent(EventRequest request) {

        Event event = eventMapper.toEntity(request);
        
        
        User currentUser = userService.getCurrentUser();
        event.setCreator(currentUser);
        
       
        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        event.setCategory(category);
        
 
        Location location = locationRepository.findById(request.getLocationId())
            .orElseThrow(() -> new RuntimeException("Location not found"));
        event.setLocation(location);
        

        if (event.getCapacity() == null || event.getCapacity() <= 0) {
            throw new RuntimeException("Capacity must be greater than zero");
        }
        

        event.setCurrentAttendees(0);
        
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toResponse(savedEvent);
    }
    
    @Override
    public EventResponse updateEvent(UUID id, EventRequest request) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        
  
        User currentUser = userService.getCurrentUser();
        if (!event.getCreator().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You do not have permission to edit this event.");
        }
        
     
        if (request.getCapacity() != null && request.getCapacity() < event.getCurrentAttendees()) {
            throw new RuntimeException("the capacity cannot be less than the current number of attendees.");
        }
        
 
        eventMapper.updateFromRequest(request, event);
        
   
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
            event.setCategory(category);
        }
        

        if (request.getLocationId() != null) {
            Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));
            event.setLocation(location);
        }
        
        Event updatedEvent = eventRepository.save(event);
        return eventMapper.toResponse(updatedEvent);
    }
    
    @Override
    public void deleteEvent(UUID id) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        

        User currentUser = userService.getCurrentUser();
        if (!event.getCreator().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You do not have permission to delete this event.");
        }
        
        eventRepository.delete(event);
    }
    
    @Override
    public Page<EventResponse> getEventsByCreator(Pageable pageable) {
        User currentUser = userService.getCurrentUser();
        return eventRepository.findByCreator(currentUser, pageable)
            .map(eventMapper::toResponse);
    }
    
    @Override
    public List<EventResponse> getEventsByCreatorList() {
        User currentUser = userService.getCurrentUser();
        return eventRepository.findByCreator(currentUser).stream()
            .map(eventMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    public Page<EventResponse> filterEvents(String categoryName, String username, String title, 
                                           LocalDate eventDate, Pageable pageable) {
        return eventRepository.findByFilters(categoryName, username, title, eventDate, pageable)
            .map(eventMapper::toResponse);
    }
}