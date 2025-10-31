package com.code_crafters.app.dto.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {
    private UUID userId;
    private UUID eventId;
}

