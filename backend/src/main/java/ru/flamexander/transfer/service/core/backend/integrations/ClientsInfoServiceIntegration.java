package ru.flamexander.transfer.service.core.backend.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.flamexander.transfer.service.core.backend.dtos.ClientInfoResponseDto;
import ru.flamexander.transfer.service.core.backend.errors.AppLogicException;

@Component
@RequiredArgsConstructor
public class ClientsInfoServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integrations.clients-info-service.url}")
    private String url;

    public ClientInfoResponseDto getClientInfo(Long id) {
        try {
            ClientInfoResponseDto receiverInfo = restTemplate.getForObject(String.format("%s/%d", url, id), ClientInfoResponseDto.class);
            return receiverInfo;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new AppLogicException("RECEIVER_NOT_EXIST", "Получатель с id = " + id + " не существует");
            }
            throw e;
        }
    }
}
