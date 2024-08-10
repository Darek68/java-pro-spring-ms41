package ru.flamexander.transfer.service.clients.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientInfoResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String status;
}
