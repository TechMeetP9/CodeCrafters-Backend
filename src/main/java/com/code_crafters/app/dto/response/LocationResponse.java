package com.code_crafters.app.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgumentsConstructor
@Builder
public class LocationResponse {
    private Long id;
    private String name;
    
}
