package com.code_crafters.app.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventsRequest {
    private String title;
    private String description;
    private Long userId; 
}
