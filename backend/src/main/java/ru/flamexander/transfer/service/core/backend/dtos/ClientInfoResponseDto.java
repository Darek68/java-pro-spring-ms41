package ru.flamexander.transfer.service.core.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientInfoResponseDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String status;
}
