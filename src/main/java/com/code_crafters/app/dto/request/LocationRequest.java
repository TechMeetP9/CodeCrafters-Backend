package com.code_crafters.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
}