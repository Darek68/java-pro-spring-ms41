package ru.flamexander.transfer.service.limits.backend.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.flamexander.transfer.service.limits.backend.controllers.LimitController;
import ru.flamexander.transfer.service.limits.backend.entities.Limit;
import ru.flamexander.transfer.service.limits.backend.errors.ResourceNotFoundException;
import ru.flamexander.transfer.service.limits.backend.repository.LimitRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitService {
    private static final Logger logger = LoggerFactory.getLogger(LimitService.class.getName());

    private final LimitRepository limitRepository;

    @Value("${limits-service.limit.init-balance}")
    private BigDecimal initBalance;

    public Limit getLimitInfoByClientId(Long clientId){
        logger.info("1 getLimitInfoByClientId id = {}", clientId);
        if (!limitRepository.existsById(clientId)){
            Limit limit = Limit.builder()
                    .clientId(clientId)
                    .balance(initBalance)  //  .balance(new BigDecimal(10000.00))
                    .build();
            logger.info("2 getLimitInfoByClientId создан лимит clientId = {}    balance = {}", limit.getClientId(),limit.getBalance());
            limitRepository.saveAndFlush(limit);
        }
        return limitRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Клиент с id = " + clientId + " не найден"));
    }

    public Limit setLimitBalanceByClientId(Long clientId, BigDecimal balance){
        logger.info("1 setLimitBalanceByClientId clientId = {}    balance = {}", clientId,balance);
        Limit limit = getLimitInfoByClientId(clientId);
        limit.setBalance(balance);
        logger.info("2 setLimitBalanceByClientId clientId = {}    balance = {}", limit.getClientId(),limit.getBalance());
        return limitRepository.save(limit);
    }
}
