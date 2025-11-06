package com.code_crafters.app.mapper;

import com.code_crafters.app.dto.request.LocationRequest;
import com.code_crafters.app.dto.response.LocationResponse;
import com.code_crafters.app.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    

    LocationResponse toResponse(Location location);
    

    Location toEntity(LocationRequest request);
}