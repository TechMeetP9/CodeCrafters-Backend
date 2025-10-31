package com.code_crafters.app.dto.response;

import java.util.UUID;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponse {
    private UUID id;
    private String name;
}
