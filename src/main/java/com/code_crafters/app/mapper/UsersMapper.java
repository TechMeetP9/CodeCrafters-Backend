package com.code_crafters.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.code_crafters.app.entity.Users;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.request.UpdateUserRequest;
import com.code_crafters.app.dto.response.JwtResponse;
import com.code_crafters.app.dto.response.UsersResponse;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    Users toEntity(RegisterRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDto(UpdateUserRequest dto, @MappingTarget Users user);

    UsersResponse toDto(Users user);

    JwtResponse toJwtResponse(Users user);
}
