package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.entity.Category;
import com.code_crafters.app.entity.Event;
import com.code_crafters.app.entity.Users;
import com.code_crafters.app.mapper.EventsMapper;
import com.code_crafters.app.repository.CategoryRepository;
import com.code_crafters.app.repository.EventsRepository;
import com.code_crafters.app.repository.UsersRepository;
import com.code_crafters.app.service.interfaces.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

        private final EventsRepository eventsRepository;
        private final UsersRepository usersRepository;
        private final CategoryRepository categoryRepository;
        private final EventsMapper eventsMapper;

        @Override
        public Event createEvent(EventsRequest request) {
                Users user = usersRepository.findById(request.getUserId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "User not found with ID: " + request.getUserId()));

                Category category = categoryRepository.findById(request.getCategoryId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Category not found with ID: " + request.getCategoryId()));

                Event event = eventsMapper.toEntity(request);
                event.setCreator(user);
                event.setCategory(category);

                return eventsRepository.save(event);
        }

        @Override
        public Optional<Event> findById(Long id) {
                return eventsRepository.findById(id);
        }

        @Override
        public List<Event> findAll() {
                return eventsRepository.findAll();
        }

        @Override
        public Event updateEvent(Long id, EventsRequest request) {
                Event existingEvent = eventsRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + id));

                eventsMapper.updateEntityFromDto(request, existingEvent);

                if (request.getCategoryId() != null) {
                        Category category = categoryRepository.findById(request.getCategoryId())
                                        .orElseThrow(() -> new IllegalArgumentException(
                                                        "Category not found with ID: " + request.getCategoryId()));
                        existingEvent.setCategory(category);
                }

                return eventsRepository.save(existingEvent);
        }

        @Override
        public void deleteEvent(Long id) {
                Event event = eventsRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + id));
                eventsRepository.delete(event);
        }
}
