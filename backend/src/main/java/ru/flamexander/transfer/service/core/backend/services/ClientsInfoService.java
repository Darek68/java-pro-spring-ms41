package ru.flamexander.transfer.service.core.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.flamexander.transfer.service.core.backend.dtos.ClientInfoResponseDto;
import ru.flamexander.transfer.service.core.backend.integrations.ClientsInfoServiceIntegration;

@Service
@RequiredArgsConstructor
public class ClientsInfoService {
    private final ClientsInfoServiceIntegration clientsInfoServiceIntegration;

    public ClientInfoResponseDto getClientInfo(Long id) {
        return clientsInfoServiceIntegration.getClientInfo(id);
    }

    public boolean isClientBlocker(Long id) {
        ClientInfoResponseDto clientInfo = getClientInfo(id);
        return !clientInfo.getStatus().equals("ACTIVE");
    }
}
