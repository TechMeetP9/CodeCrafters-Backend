package com.code_crafters.app.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {
    private UUID id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private Integer capacity;
    private UUID categoryId; 
    private String categoryName; 
    private UUID userId; 
}