package com.code_crafters.app.dto.response;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponse {
    private UUID id;
    private UUID userId;
    private UUID eventId;
    private String userName;
    private String eventName;
}
