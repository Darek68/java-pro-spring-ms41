package ru.flamexander.transfer.service.core.backend.services;

import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;

public interface TransfersService {
    void execute(Long clientId, ExecuteTransferDtoRequest executeTransferDtoRequest);
}
