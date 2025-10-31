package com.code_crafters.app.mapper;

import org.mapstruct.Mapper;

import com.code_crafters.app.dto.request.LocationRequest;
import com.code_crafters.app.dto.response.LocationResponse;

@Mapper(componentModel = "Spring")
public class LocationMapper {

    Location toEntity(LocationRequest);

    LocationResponse toDto(Location entity);

}
