package com.code_crafters.app.dto.request;

import lombok.Data.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventsRequest {
    private String title;
    private String description;
    private Long userId; 
}
