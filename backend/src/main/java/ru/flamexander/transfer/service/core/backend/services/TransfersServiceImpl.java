package ru.flamexander.transfer.service.core.backend.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.backend.dtos.LimitAmtDto;
import ru.flamexander.transfer.service.core.backend.entities.Account;
import ru.flamexander.transfer.service.core.backend.errors.AppLogicException;
import ru.flamexander.transfer.service.core.backend.integrations.LimitsInfoServiceIntegration;
import ru.flamexander.transfer.service.core.backend.validators.ExecuteTransferValidator;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransfersServiceImpl implements TransfersService {
    private static final Logger logger = LoggerFactory.getLogger(TransfersServiceImpl.class);
    private final AccountsService accountsService;
    private final ExecuteTransferValidator executeTransferValidator;
    private final ClientsInfoService clientsInfoService;
    private final LimitsService limitsService;
 //   private final RestTemplate restTemplate;

    @Transactional
    @Override
    public void execute(Long clientId, ExecuteTransferDtoRequest executeTransferDtoRequest) {
        if (clientsInfoService.isClientBlocker(clientId)) {
            throw new AppLogicException("SENDER_IS_BLOCKED", "Клиент-отправитель id = " + clientId + " не может выполнить отправку перевода, так как заблокирован");
        }
        if (clientsInfoService.isClientBlocker(executeTransferDtoRequest.getReceiverId())) {
            throw new AppLogicException("RECEIVER_IS_BLOCKED", "Невозможно выполнить перевод заблокированному клиенту с id = " + executeTransferDtoRequest.getReceiverId());
        }

        Account senderAccount = accountsService.findByClientIdAndAccountNumber(clientId, executeTransferDtoRequest.getSenderAccountNumber()).orElseThrow(() -> new AppLogicException("TRANSFER_SOURCE_ACCOUNT_NOT_FOUND", "Перевод невозможен поскольку не существует счет отправителя"));
        Account receiverAccount = accountsService.findByClientIdAndAccountNumber(executeTransferDtoRequest.getReceiverId(), executeTransferDtoRequest.getReceiverAccountNumber()).orElseThrow(() -> new AppLogicException("TRANSFER_TARGET_ACCOUNT_NOT_FOUND", "Перевод невозможен поскольку не существует счет получателяч"));

  //     LimitInfoResponseDto limitInfoSender  = restTemplate.getForObject("http://localhost:8191/api/v1/limits/" + clientId, LimitInfoResponseDto.class);
         BigDecimal limitAmt  = limitsService.getLimitInfo(clientId).getLimit();
        logger.info("execute limitAmt = {}", limitAmt);
      //  LimitInfoResponseDto limitInfoReciver = restTemplate.getForObject("http://localhost:8191/api/v1/limits/" + executeTransferDtoRequest.getReceiverId(), LimitInfoResponseDto.class);
        if (executeTransferDtoRequest.getTransferSum().compareTo(limitAmt) > 0) throw new AppLogicException("DAILY_LIMIT_EXCEEDED", "Сумма перевода " + executeTransferDtoRequest.getTransferSum() + " превышает остаток лимита = " + limitAmt);


        senderAccount.setBalance(senderAccount.getBalance().subtract(executeTransferDtoRequest.getTransferSum()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(executeTransferDtoRequest.getTransferSum()));


    }
}
