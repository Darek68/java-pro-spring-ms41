package ru.flamexander.transfer.service.limits.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.transfer.service.limits.backend.dtos.LimitBalanceRequest;
import ru.flamexander.transfer.service.limits.backend.dtos.LimitInfoResponse;
import ru.flamexander.transfer.service.limits.backend.entities.Limit;
import ru.flamexander.transfer.service.limits.backend.services.LimitService;

import java.util.function.Function;

@RestController
@RequestMapping("/api/v1/limits")
@RequiredArgsConstructor
public class LimitController {
    private static final Logger logger = LoggerFactory.getLogger(LimitController.class.getName());

    private final LimitService limitService;

    private Function<Limit, LimitInfoResponse> entityToDto = limit -> new LimitInfoResponse(limit.getClientId(),limit.getBalance());

    @GetMapping("/{id}")
    public LimitInfoResponse getLimitInfo(@PathVariable Long id){
        logger.info("1getClientInfo id = {}", id);
        return entityToDto.apply(limitService.getLimitInfoByClientId(id));
    }

    @PostMapping("set")
    public LimitInfoResponse setLimitBalance(@RequestBody LimitBalanceRequest limitBalanceRequest){
        logger.info("1 setLimitBalance clientId = {}    balance = {}", limitBalanceRequest.getClientId(),limitBalanceRequest.getLimit());
        return entityToDto.apply(limitService.setLimitBalanceByClientId(limitBalanceRequest.getClientId(),limitBalanceRequest.getLimit()));
    }
}
