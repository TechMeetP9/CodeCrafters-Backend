package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.LocationRequest;
import com.code_crafters.app.dto.response.LocationResponse;
import com.code_crafters.app.service.interfaces.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    
    private LocationService locationService;
    
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    
    @GetMapping
    public ResponseEntity<List<LocationResponse>> getAllLocations() {
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getLocationById(@PathVariable UUID id) {
        return new ResponseEntity<>(locationService.getLocationById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody LocationRequest request) {
        try {
            LocationResponse newLocation = locationService.createLocation(request);
            return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}