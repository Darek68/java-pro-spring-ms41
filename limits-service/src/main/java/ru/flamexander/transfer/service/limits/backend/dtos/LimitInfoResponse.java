package ru.flamexander.transfer.service.limits.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LimitInfoResponse {
    private Long clientId;
    private BigDecimal limit;
}
