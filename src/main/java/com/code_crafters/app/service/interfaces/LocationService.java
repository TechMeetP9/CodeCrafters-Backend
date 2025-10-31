package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.LocationRequest;
import com.code_crafters.app.dto.response.LocationResponse;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    LocationResponse create(LocationRequest request);
    List<LocationResponse> getAll();
    void delete(UUID id);
}
