package ru.flamexander.transfer.service.core.backend.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.flamexander.transfer.service.core.backend.dtos.ClientInfoResponseDto;
import ru.flamexander.transfer.service.core.backend.dtos.LimitAmtDto;
import ru.flamexander.transfer.service.core.backend.integrations.ClientsInfoServiceIntegration;
import ru.flamexander.transfer.service.core.backend.integrations.LimitsInfoServiceIntegration;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LimitsService {
    private static final Logger logger = LoggerFactory.getLogger(LimitsService.class);
    private final ClientsInfoServiceIntegration clientsInfoServiceIntegration;
    private final LimitsInfoServiceIntegration limitsInfoServiceIntegration;

 //   public ClientInfoResponseDto getClientInfo(Long id) { return clientsInfoServiceIntegration.getClientInfo(id); }
    public LimitAmtDto getLimitInfo(Long id) { return limitsInfoServiceIntegration.getLimitInfo(id);}

    public LimitAmtDto setLimitAmt(Long id, BigDecimal amt){return limitsInfoServiceIntegration.setLimit(new LimitAmtDto(id,amt));}

}
