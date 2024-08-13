package ru.flamexander.transfer.service.core.backend.integrations;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.flamexander.transfer.service.core.backend.dtos.LimitAmtDto;
import ru.flamexander.transfer.service.core.backend.errors.AppLogicException;
import ru.flamexander.transfer.service.core.backend.services.LimitsService;

import java.net.ConnectException;

@Component
@RequiredArgsConstructor
public class LimitsInfoServiceIntegration {
    private static final Logger logger = LoggerFactory.getLogger(LimitsInfoServiceIntegration.class);
    private final RestTemplate restTemplate;

    @Value("${integrations.limits-info-service.url}")
    private String url;

    public LimitAmtDto getLimitInfo(Long id) {
        try {
            logger.info("getLimitInfo url = {}    custam.. {}", String.format("%s/%d", url, id), 777);
            LimitAmtDto receiverInfo = restTemplate.getForObject(String.format("%s/%d", url, id), LimitAmtDto.class); // http://localhost:8191/api/v1/limits/1
            logger.info("getLimitInfo receiverInfo = {}    custam.. {}", receiverInfo, 777);
            //   LimitAmtDto receiverInfo = restTemplate.getForObject(String.format("%s/%d", url, id), LimitAmtDto.class); // http://localhost:8191/api/v1/limits/1
            return receiverInfo;
        } catch (ResourceAccessException e) {
            throw new AppLogicException("LIMITSERVICE_NOT_FOUND", "Сервис лимитов временно не доступен");
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new AppLogicException("RECEIVER_NOT_EXIST", "Лимит клиента с id = " + id + " не найден");
            }
            throw e;
        }
    }
    public LimitAmtDto setLimit(LimitAmtDto limitAmtDto){
        return restTemplate.postForEntity(url + "/set",limitAmtDto,LimitAmtDto.class).getBody();
    }

}
