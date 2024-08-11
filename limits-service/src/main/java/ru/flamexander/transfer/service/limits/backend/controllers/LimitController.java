package ru.flamexander.transfer.service.limits.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.transfer.service.limits.backend.dtos.LimitInfoResponse;
import ru.flamexander.transfer.service.limits.backend.entities.Limit;
import ru.flamexander.transfer.service.limits.backend.services.LimitService;

@RestController
@RequestMapping("/api/v1/limits")
@RequiredArgsConstructor
public class LimitController {
    private static final Logger logger = LoggerFactory.getLogger(LimitController.class.getName());

    private final LimitService limitService;
    @GetMapping("/{id}")
    public LimitInfoResponse getLimitInfo(@PathVariable Long id){
        logger.info("1getClientInfo id = {}", id);
        Limit limit = limitService.getLimitInfoByClientId(id);
        logger.info("2getClientInfo id = {}    balance = {}", id,limit.getBalance());
        return new LimitInfoResponse(limit.getClientId(),limit.getBalance());
    }
/*    @GetMapping("/{id}")
  //  public String getLimitInfo(@PathVariable Long id) {
    public LimitInfoResponse getLimitInfo(@PathVariable Long id) {
        logger.info("1 getClientInfo id = {}", id);
        Limit limit = limitService.getLimitInfoByClientId(id);
        logger.info("2 getClientInfo id = {}   client = {}    balance = {}", id,limit.getClientId(),limit.getBalance());
        LimitInfoResponse limitInfoResponse = new LimitInfoResponse(limit.getClientId(),limit.getBalance());
        logger.info("3 getClientInfo id = {}   client = {}    balance = {}", id,limitInfoResponse.getClientId(),limitInfoResponse.getLimit());
     //   return "Return!!### - " + id;
     //   return limitInfoResponse;
        return new LimitInfoResponse(limit.getClientId(),limit.getBalance());
    } */
}
