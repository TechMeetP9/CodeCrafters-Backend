package com.code_crafters.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.code_crafters.app.entity.Users;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.response.JwtResponse;
import com.code_crafters.app.dto.response.UsersResponse;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    Users toEntity(RegisterRequest dto);

    UsersResponse toDto(Users user);

    JwtResponse toJwtResponse(Users user);

}
