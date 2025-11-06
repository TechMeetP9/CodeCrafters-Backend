package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.LocationRequest;
import com.code_crafters.app.dto.response.LocationResponse;
import java.util.List;
import java.util.UUID;

public interface LocationService {
    List<LocationResponse> getAllLocations();
    LocationResponse getLocationById(UUID id);
    LocationResponse createLocation(LocationRequest request);
}