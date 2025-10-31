package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.LocationRequest;
import com.code_crafters.app.dto.response.LocationResponse;
import com.code_crafters.app.entity.Location;
import com.code_crafters.app.mapper.LocationMapper;
import com.code_crafters.app.repository.LocationRepository;
import com.code_crafters.app.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public LocationResponse create(LocationRequest request) {
        locationRepository.findByName(request.getName()).ifPresent(l -> {
            throw new IllegalArgumentException("Location already exists: " + l.getName());
        });

        Location location = locationMapper.toEntity(request);
        Location saved = locationRepository.save(location);
        return locationMapper.toDto(saved);
    }

    @Override
    public List<LocationResponse> getAll() {
        return locationRepository.findAll()
                .stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new IllegalArgumentException("Location not found with ID: " + id);
        }
        locationRepository.deleteById(id);
    }
}
