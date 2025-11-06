package com.code_crafters.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String title;
    private String description;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String duration;
    private Integer capacity;
    private String imageUrl;
    private UUID categoryId;
    private UUID locationId;
}