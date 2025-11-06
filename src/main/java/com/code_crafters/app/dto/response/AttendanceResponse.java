package com.code_crafters.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {
    private UUID id;
    private UUID userId;
    private String username;
    private String userFullName;
    private UUID eventId;
    private String eventTitle;
    private LocalDateTime joinedAt;
    private LocalDateTime createdAt;
}