package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.LocationRequest;
import com.code_crafters.app.dto.response.LocationResponse;
import com.code_crafters.app.entity.Location;
import com.code_crafters.app.mapper.LocationMapper;
import com.code_crafters.app.repository.LocationRepository;
import com.code_crafters.app.service.interfaces.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {
    
    private LocationRepository locationRepository;
    private LocationMapper locationMapper;
    
    public LocationServiceImpl(LocationRepository locationRepository,
                              LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }
    
    @Override
    public List<LocationResponse> getAllLocations() {
        return locationRepository.findAll().stream()
            .map(locationMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    public LocationResponse getLocationById(UUID id) {
        Location location = locationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        return locationMapper.toResponse(location);
    }
    
    @Override
    public LocationResponse createLocation(LocationRequest request) {
        Location location = locationMapper.toEntity(request);
        Location savedLocation = locationRepository.save(location);
        return locationMapper.toResponse(savedLocation);
    }
}