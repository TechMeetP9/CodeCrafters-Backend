package com.code_crafters.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.request.UpdateUserRequest;
import com.code_crafters.app.dto.response.JwtResponse;
import com.code_crafters.app.dto.response.UsersResponse;

@Mapper(componentModel = "spring")
public interface UsersMapper {


    User toEntity(RegisterRequest dto);

    void updateEntityFromDto(UpdateUserRequest dto, @MappingTarget User user);

    UsersResponse toDto(User user);

    JwtResponse toJwtResponse(User user);
}
