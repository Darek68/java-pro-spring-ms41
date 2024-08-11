package ru.flamexander.transfer.service.limits.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LimitInfoRequest {
    private Long clientId;
}
