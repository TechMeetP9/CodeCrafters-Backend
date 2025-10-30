package com.code_crafters.app.service.implement;

import com.code_crafters.app.dto.request.EventsRequest;
import com.code_crafters.app.entity.Category;
import com.code_crafters.app.entity.Events;
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
        public Events createEvent(EventsRequest request) {
                var user = usersRepository.findById(request.getUserId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "User not found with ID: " + request.getUserId()));

                Category category = categoryRepository.findById(request.getCategoryId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Category not found with ID: " + request.getCategoryId()));

                Events event = eventsMapper.toEntity(request);
                event.setCreator(user);
                event.setCategory(category);

                return eventsRepository.save(event);
        }

        @Override
        public Optional<Events> findById(Long id) {
                return eventsRepository.findById(id);
        }

        @Override
        public List<Events> findAll() {
                return eventsRepository.findAll();
        }

        @Override
        public Events updateEvent(Long id, EventsRequest request) {
                Events existingEvent = eventsRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + id));

                var user = usersRepository.findById(request.getUserId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "User not found with ID: " + request.getUserId()));

                Category category = categoryRepository.findById(request.getCategoryId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Category not found with ID: " + request.getCategoryId()));

                eventsMapper.updateEntityFromDto(request, existingEvent);
                existingEvent.setCreator(user);
                existingEvent.setCategory(category);

                return eventsRepository.save(existingEvent);
        }

        @Override
        public void deleteEvent(Long id) {
                Events event = eventsRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + id));

                eventsRepository.delete(event);
        }
}
