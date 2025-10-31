package com.code_crafters.app.dto.request;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventsRequest {
    private String title;
    private String description;
    private UUID locationId;
    private LocalDateTime dateTime;
    private Integer capacity;
    private UUID categoryId; 
    private UUID userId; 
}
