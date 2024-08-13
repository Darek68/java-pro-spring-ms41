package ru.flamexander.transfer.service.core.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LimitAmtDto {
    private Long clientId;
    private BigDecimal limit;
}
